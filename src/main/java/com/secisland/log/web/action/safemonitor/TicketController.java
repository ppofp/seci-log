/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.log.web.action.safemonitor;

import java.util.ArrayList;
import java.util.Date;
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

import com.secisland.core.util.LoginUserInfo;
import com.secisland.log.web.entity.Ticket;
import com.secisland.log.web.service.authority.AuthorityService;
import com.secisland.log.web.service.safemonitor.TicketService;

@Controller
@RequestMapping(value = "/safemonitor/ticket")
public class TicketController {
	private static final String PAGE_SIZE = "15";
	
	@Autowired
	private AuthorityService authService;
	@Autowired
	private TicketService ticketService;
	
	@RequestMapping(method = RequestMethod.GET)
    public String getTicket(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
            @RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
            @RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
            ServletRequest request) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        
        Page<Ticket> objects = ticketService.getTicketPage(searchParams, pageNumber, pageSize, sortType);

        model.addAttribute("selectmenu", authService.getMenuSelectCode(request));
        model.addAttribute("menutitle", authService.getMenuTitle(request,"工单管理"));
        
		model.addAttribute("objects", objects);
        model.addAttribute("sortType", sortType);
        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		return "safemonitor/ticket";
	}
	
	@RequestMapping(value = "alert/{id}")
	public String alertevent(@PathVariable("id") Long id,Model model, ServletRequest request) {
		Ticket objects = ticketService.getTicket(id);

        model.addAttribute("selectmenu", authService.getMenuSelectCode(request));
        model.addAttribute("menutitle", authService.getMenuTitle(request,"工单管理"));
        
		model.addAttribute("objects", objects);
		return "safemonitor/ticketalert";
	}
	
	@RequestMapping(value = "ticketConfirm")
	public String alertConfirm(@RequestParam(value = "ticketList") List<Long> ticketList,Model model, ServletRequest request) {
        model.addAttribute("selectmenu", authService.getMenuSelectCode(request));
        model.addAttribute("menutitle", authService.getMenuTitle(request,"工单确认"));
        model.addAttribute("tickets", ticketList);

		return "safemonitor/ticketConfirm";
	}
	
    @RequestMapping(value = "saveTicket",method = RequestMethod.POST)
    public String create(String processType,String memo,Long[] tickets,
                         Model model, RedirectAttributes attributes) {
    	if ( tickets == null || tickets.length==0){
    		attributes.addFlashAttribute("message", "工单处理失败，请选择最少一条工单！");
    	}
    	
    	List<Ticket> alertList = new ArrayList<Ticket>();
    	for ( Long id : tickets ){
    		Ticket ticket = ticketService.getTicket(id);
    		ticket.setProcessType(processType);
    		ticket.setProcessDate(new Date());
    		ticket.setProcessRemark(memo);
    		ticket.setProcessUser(LoginUserInfo.getLoginUserCode());
    		ticketService.update(ticket);
    		alertList.add(ticket);
    	}
    	attributes.addFlashAttribute("code",100);
    	attributes.addFlashAttribute("message", "告警处理成功！");
        return "redirect:/safemonitor/ticket";
    }
}
