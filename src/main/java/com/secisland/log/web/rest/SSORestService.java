/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.log.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springside.modules.web.MediaTypes;

import com.secisland.log.web.entity.SmOrganization;
import com.secisland.log.web.repository.SmOrganizeDao;

@RestController
@RequestMapping(value = "/api/sso")
public class SSORestService {
	@Autowired
	SmOrganizeDao orgDao;
    
	@RequestMapping(value = "org", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
	public List<SmOrganization> getOrg() {
		List<SmOrganization> orgList = (List<SmOrganization>) orgDao.findAll();
		
		return orgList;
	}	
}
