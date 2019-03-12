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
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;

import com.secisland.log.web.entity.Alert;
import com.secisland.log.web.entity.EventLog;
import com.secisland.log.web.service.authority.AuthorityService;
import com.secisland.log.web.service.safemonitor.AlertService;
import com.secisland.log.web.service.safemonitor.EventlogService;

@Controller
@RequestMapping(value = "/safemonitor/eventlog")
public class EventLogController {
	
	private static final String PAGE_SIZE = "15";
	
	@Autowired
	private AuthorityService authService;
	@Autowired 
	private EventlogService logService;
	@Autowired
	private AlertService alertService;
	
	@RequestMapping(method = RequestMethod.GET)
    public String getEventLog(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
            @RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
            @RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
            ServletRequest request) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        
        Page<EventLog> objects = logService.getEventLogPage(searchParams, pageNumber, pageSize, sortType);

        model.addAttribute("selectmenu", authService.getMenuSelectCode(request));
        model.addAttribute("menutitle", authService.getMenuTitle(request,"安全事件"));
        
		model.addAttribute("objects", objects);
        model.addAttribute("sortType", sortType);
        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		return "safemonitor/eventlog";
	}
	
	@RequestMapping(value = "createAlert")
	public String createAlert(@RequestParam(value = "eventlogList") List<Long> eventlogList,Model model, ServletRequest request) {
        model.addAttribute("selectmenu", authService.getMenuSelectCode(request));
        model.addAttribute("menutitle", authService.getMenuTitle(request,"生成告警"));
        model.addAttribute("eventlogs", eventlogList);

		return "safemonitor/createAlert";
	}
	
    @RequestMapping(value = "saveAlert",method = RequestMethod.POST)
    public String create(@Valid Alert alert,BindingResult result,
                         Model model, RedirectAttributes attributes) {
        if(result.hasErrors()){
            List<FieldError> errorList = result.getFieldErrors();
            StringBuffer retError = new StringBuffer("");
            for ( FieldError error : errorList){
                retError.append(error.getDefaultMessage()+"<br>");
            }
            attributes.addFlashAttribute("alert", alert);
            attributes.addFlashAttribute("error", retError.toString() );
            return "redirect:/safemonitor/createAlert";
        }
        if(alert.getId() == null){
        	List<EventLog> eventLogList = alert.getEventLogs();
        	List<EventLog> eventLogList1 = new ArrayList<EventLog>();
        	int eventCount = 0;
        	StringBuffer ips = new StringBuffer("");
    		for ( EventLog eventlog:eventLogList){
    			eventlog = logService.getEventLog(eventlog.getId());
    			eventCount += eventlog.getEventCount();
    			ips.append(eventlog.getHostIp()+",");
    			eventLogList1.add(eventlog);
    		}
    		alert.setObjectAddress(ips.toString());
    		alert.setEventCount(eventCount);
    		alert.setEventLogs(eventLogList1);
        	alertService.create(alert);
            attributes.addFlashAttribute("code",100);
            attributes.addFlashAttribute("message", "创建告警" + alert.getDisplayTitle() + "成功");
        }else {
        	alertService.update(alert);
            attributes.addFlashAttribute("code",100);
            attributes.addFlashAttribute("message", "修改告警" + alert.getDisplayTitle() + "成功");
        }
        return "redirect:/safemonitor/eventlog";
    }
}
