/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.log.web.action.config;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springside.modules.web.Servlets;

import com.secisland.log.web.entity.AlertRule;
import com.secisland.log.web.service.authority.AuthorityService;
import com.secisland.log.web.service.config.AlertRuleService;

@Controller
@RequestMapping(value = "/config/alertrule")
public class AlertRuleController {
	private static final String PAGE_SIZE = "15";
	
	@Autowired
	private AuthorityService authService;
	@Autowired
	private AlertRuleService alertRuleService;
	
	@RequestMapping(method = RequestMethod.GET)
    public String getAlert(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
            @RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
            @RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
            ServletRequest request) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        
        Page<AlertRule> objects = alertRuleService.getAlertPage(searchParams, pageNumber, pageSize, sortType);

        model.addAttribute("selectmenu", authService.getMenuSelectCode(request));
        model.addAttribute("menutitle", authService.getMenuTitle(request,"告警规则"));
        
		model.addAttribute("objects", objects);
        model.addAttribute("sortType", sortType);
        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		return "config/alertrule";
	}
	
	@RequestMapping(value = "alertrule/{id}")
	public String alertevent(@PathVariable("id") Long id,Model model, ServletRequest request) {
		AlertRule objects = alertRuleService.getAlertRule(id);

        model.addAttribute("selectmenu", authService.getMenuSelectCode(request));
        model.addAttribute("menutitle", authService.getMenuTitle(request,"告警规则"));
        
		model.addAttribute("objects", objects);
		return "config/alertrule";
	}
	
	@RequestMapping(value = "alertConfirm")
	public String alertConfirm(@RequestParam(value = "alertList") List<Long> alertList,Model model, ServletRequest request) {
        model.addAttribute("selectmenu", authService.getMenuSelectCode(request));
        model.addAttribute("menutitle", authService.getMenuTitle(request,"告警确认"));
        model.addAttribute("alerts", alertList);

		return "config/alertConfirm";
	}
}
