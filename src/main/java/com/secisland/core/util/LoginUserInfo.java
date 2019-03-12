/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.core.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.secisland.log.web.service.account.ShiroUser;

public class LoginUserInfo {

    public static Long getLoginUserId() {
    	if (SecurityUtils.getSubject().isAuthenticated()) {//判断系统是否登录
    		Object user = SecurityUtils.getSubject().getPrincipal();
    		if ( user instanceof  ShiroUser){
    			ShiroUser shiroUser = (ShiroUser)user;
                if ( user != null ){
                	return shiroUser.id;	
                }
    		} else {
    			Long userId = (Long)SecurityUtils.getSubject().getPrincipals().asList().get(0);
    			return userId;
    		}
    	} 
    	return null; 
    }

    public static String getLoginUserCode(){
    	if (SecurityUtils.getSubject().isAuthenticated()) {
    		Object user = SecurityUtils.getSubject().getPrincipal();
    		if ( user instanceof  ShiroUser){
    			ShiroUser shiroUser = (ShiroUser)user;
                if ( user != null ){
                	return shiroUser.loginName;	
                }
    		} else {
    			String userName = SecurityUtils.getSubject().getPrincipals().asList().get(1).toString();
    			return userName;
    		}
    	} 
    	return null;
    }
    
    public static String getLoginUserName(){
    	if (SecurityUtils.getSubject().isAuthenticated()) {
    		Object user = SecurityUtils.getSubject().getPrincipal();
    		if ( user instanceof  ShiroUser){
    			ShiroUser shiroUser = (ShiroUser)user;
                if ( user != null ){
                	return shiroUser.name;	
                }
    		} 
    	} 
    	return null;
    }
    
    public static String getIpAddress(){
    	if (SecurityUtils.getSubject().isAuthenticated()) {
        	Subject currentUser = SecurityUtils.getSubject();
        	Session session = currentUser.getSession();
        	if (null != session) {
        		return session.getAttribute("ipaddress").toString();
        	} 
    	} 
    	return null;
    }
}
