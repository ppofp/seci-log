/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.log.web.service.account;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springside.modules.utils.Encodes;

import com.google.common.collect.ImmutableList;
import com.secisland.core.exception.NewAccountException;
import com.secisland.log.web.entity.SmUser;

public class ShiroDbRealm extends AuthorizingRealm {

	protected AccountService accountService;

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		SmUser user = accountService.findUserByLoginName(token.getUsername());
		if ( user==null){
			throw new UnknownAccountException ();
		}
		String state = user.getState();//lock, active, apply,delete
		if ( state.equals("apply")){
			throw new NewAccountException();
		} else if( state.equals("lock")){
			throw new LockedAccountException();
		} else if( state.equals("active")){
			byte[] salt = Encodes.decodeHex(user.getSalt());
			AuthenticationInfo info = new SimpleAuthenticationInfo(new ShiroUser(user.getId(), user.getUserCode(), user.getUserName()),
					user.getPassword(), ByteSource.Util.bytes(salt), getName());
			return info;
		} else if( state.equals("delete")){
			throw new UnknownAccountException();
		} 
		return null;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		SmUser user = accountService.findUserByLoginName(shiroUser.loginName);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if(user!=null)
		    info.addRoles(ImmutableList.copyOf(StringUtils.split("admin", ",")));//(user.getRoleList());
		return info;
	}

	@PostConstruct
	public void initCredentialsMatcher() {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(AccountService.HASH_ALGORITHM);
		matcher.setHashIterations(AccountService.HASH_INTERATIONS);

		setCredentialsMatcher(matcher);
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
}
