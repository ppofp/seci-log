/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.log.web.service.account;

import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.secisland.core.util.LoginUserInfo;
import com.secisland.log.web.entity.SmLog;
import com.secisland.log.web.repository.SmLogDao;
import com.secisland.log.web.service.authority.AuthorityService;

@Component
public class CaptchaFormAuthenticationFilter extends FormAuthenticationFilter {
 
	@Autowired
	SmLogDao logDao;
	@Autowired
	private AuthorityService authService;
	
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject,
            ServletRequest request, ServletResponse response) throws Exception {
    	SmLog log = new SmLog();
    	log.setOperateBy(LoginUserInfo.getLoginUserId());
    	log.setOperateCode(LoginUserInfo.getLoginUserCode());
    	log.setOperateName(LoginUserInfo.getLoginUserName());
    	log.setOperateDate(new Date());
    	log.setOperateIp(request.getRemoteAddr());
    	log.setOperateType("login");
    	log.setObjectCode("auth");
    	log.setObjectName("用户登录");
    	log.setResult("success");
    	log.setContent(LoginUserInfo.getLoginUserCode()+"登录成功！");
    	logDao.save(log);
    	
    	HttpServletRequest httpRequest = (HttpServletRequest)request;
    	httpRequest.getSession().setAttribute("allmenu", authService.getMenuString(httpRequest.getContextPath()));
    	httpRequest.getSession().setAttribute("ipaddress", request.getRemoteAddr());
        issueSuccessRedirect(request, response);
        //we handled the success redirect directly, prevent the chain from continuing:
        return false;
    }
}