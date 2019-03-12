/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.log.web.action.account;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.secisland.core.exception.NewAccountException;
import com.secisland.core.util.LoginUserInfo;
import com.secisland.log.web.entity.SmLog;
import com.secisland.log.web.entity.SmUser;
import com.secisland.log.web.repository.SmLogDao;
import com.secisland.log.web.service.account.AccountService;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

	@Autowired
	private SmLogDao logDao;
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String login() {
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated())
            return "redirect:/";
		return "account/login";
	}

	@RequestMapping(value = "/out", method = RequestMethod.GET)
	public String loginout(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()){
        	SmLog log = new SmLog();
        	log.setOperateBy(LoginUserInfo.getLoginUserId());
        	log.setOperateCode(LoginUserInfo.getLoginUserCode());
        	log.setOperateName(LoginUserInfo.getLoginUserName());
        	log.setOperateDate(new Date());
        	log.setOperateIp(request.getRemoteAddr());
        	log.setOperateType("logout");
        	log.setObjectCode("auth");
        	log.setObjectName("管理员登出");
        	log.setResult("success");
        	log.setContent(LoginUserInfo.getLoginUserCode()+"登出成功！");
        	logDao.save(log);
        	subject.logout();
        }
		return "account/login";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String fail(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName, HttpServletRequest req,Model model) {
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);
		String exceptionClassName = (String)req.getAttribute("shiroLoginFailure");  	
		String error = null;
		if (UnknownAccountException.class.getName().equals(exceptionClassName)||
			LockedAccountException.class.getName().equals(exceptionClassName)||
			IncorrectCredentialsException.class.getName().equals(exceptionClassName)||
            AuthenticationException.class.getName().equals(exceptionClassName)) {
			error = "用户名/密码错误";
		} else if (NewAccountException.class.getName().equals(
				exceptionClassName)) {
			error = "用户没有经过邮箱认证，请到邮箱中点击验证链接！";
		} else if (exceptionClassName != null) {
			error = "其他错误：" + exceptionClassName;
		}
		model.addAttribute("error", error);
		SmUser user = accountService.findUserByLoginName(userName);
		SmLog log = new SmLog();
		if (user == null){
			log.setOperateBy(0L);
	    	log.setOperateCode(userName);
	    	log.setOperateName(userName);
		} else {
	    	log.setOperateBy(user.getId());
	    	log.setOperateCode(user.getUserCode());
	    	log.setOperateName(user.getUserName());
		}

    	log.setOperateDate(new Date());
    	log.setOperateIp(req.getRemoteAddr());
    	log.setOperateType("login");
    	log.setObjectCode("auth");
    	log.setObjectName("管理员登录");
    	log.setResult("fail");
    	log.setContent(LoginUserInfo.getLoginUserCode()+"登录失败！");
    	logDao.save(log);
		return "account/login";
	}
}
