/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.log.web.action.log;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springside.modules.web.Servlets;

import com.secisland.log.web.entity.SmLog;
import com.secisland.log.web.service.authority.AuthorityService;
import com.secisland.log.web.service.log.LogService;

@Controller
@RequestMapping(value = "/log")
public class LogController {
	private static final String PAGE_SIZE = "15";
	
	@Autowired
	private LogService logService;
	@Autowired
	private AuthorityService authService;
	
	@RequestMapping(value = "systemlog",method = RequestMethod.GET)
    public String getSystemLog(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
            @RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
            @RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
            ServletRequest request) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        
        Page<SmLog> objects = logService.getSystemLogPage(searchParams, pageNumber, pageSize, sortType);

        model.addAttribute("selectmenu", authService.getMenuSelectCode(request));
        model.addAttribute("menutitle", authService.getMenuTitle(request,"系统日志"));
        
		model.addAttribute("objects", objects);
        model.addAttribute("sortType", sortType);
        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		return "log/systemlog";
	}
}
