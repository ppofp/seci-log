/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.log.web.action.safemonitor;

import java.util.ArrayList;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;

import com.secisland.log.web.entity.Alert;
import com.secisland.log.web.service.authority.AuthorityService;
import com.secisland.log.web.service.safemonitor.AlertService;
import com.secisland.log.web.service.safemonitor.TicketService;

@Controller
@RequestMapping(value = "/safemonitor/alert")
public class AlertController {
	private static final String PAGE_SIZE = "15";
	
	@Autowired
	private AuthorityService authService;
	@Autowired
	private AlertService alertService;
	@Autowired
	private TicketService ticketService;
	
	@RequestMapping(method = RequestMethod.GET)
    public String getAlert(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
            @RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
            @RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
            ServletRequest request) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        
        Page<Alert> objects = alertService.getAlertPage(searchParams, pageNumber, pageSize, sortType);

        model.addAttribute("selectmenu", authService.getMenuSelectCode(request));
        model.addAttribute("menutitle", authService.getMenuTitle(request,"安全告警"));
        
		model.addAttribute("objects", objects);
        model.addAttribute("sortType", sortType);
        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		return "safemonitor/alert";
	}
	
	@RequestMapping(value = "event/{id}")
	public String alertevent(@PathVariable("id") Long id,Model model, ServletRequest request) {
		Alert objects = alertService.getAlert(id);

        model.addAttribute("selectmenu", authService.getMenuSelectCode(request));
        model.addAttribute("menutitle", authService.getMenuTitle(request,"告警日志"));
        
		model.addAttribute("objects", objects);
		return "safemonitor/alertevent";
	}
	
	@RequestMapping(value = "alertConfirm")
	public String alertConfirm(@RequestParam(value = "alertList") List<Long> alertList,Model model, ServletRequest request) {
        model.addAttribute("selectmenu", authService.getMenuSelectCode(request));
        model.addAttribute("menutitle", authService.getMenuTitle(request,"告警确认"));
        model.addAttribute("alerts", alertList);

		return "safemonitor/alertConfirm";
	}
	
    @RequestMapping(value = "saveAlert",method = RequestMethod.POST)
    public String create(String processType,String memo,Long[] alerts,
                         Model model, RedirectAttributes attributes) {
    	if ( alerts == null || alerts.length==0){
    		attributes.addFlashAttribute("message", "告警处理失败，请选择最少一条告警！");
    	}
    	
    	List<Alert> alertList = new ArrayList<Alert>();
    	for ( Long id : alerts ){
    		Alert alert = alertService.getAlert(id);
    		alert.setMemo(memo);
    		alert.setProcessFlag("Y");
    		alert.setProcessType(processType);
    		alertService.update(alert);
    		alertList.add(alert);
    	}
    	if ( processType.equals("ticket")){
    		ticketService.create(alertList,memo);
    	}
    	attributes.addFlashAttribute("code",100);
    	attributes.addFlashAttribute("message", "告警处理成功！");
        return "redirect:/safemonitor/alert";
    }
}
