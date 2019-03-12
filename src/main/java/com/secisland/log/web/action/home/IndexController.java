/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.log.web.action.home;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.secisland.log.web.service.authority.AuthorityService;
import com.secisland.log.web.service.echarts.EchartsHome;

@Controller
@RequestMapping(value = "/")
public class IndexController {
	@Autowired
	private AuthorityService authService;
	@Autowired
	private EchartsHome echartsHome;
	
	@RequestMapping(method = RequestMethod.GET)
	public String login(Model model,ServletRequest request) {
		model.addAttribute("selectmenu", getSelectMenu(request));
		model.addAttribute("menutitle", authService.getMenuTitle(request,"仪表盘"));
		return "home/home";
	}

    private String getSelectMenu(ServletRequest request){
        String selectmenu = authService.getMenuSelectCode(request);
        if(selectmenu.equals("/")){
            selectmenu = "home";
        }
        return  selectmenu;
    }
	
	@RequestMapping(value = "/home/getdata", method = RequestMethod.POST)
	@ResponseBody
	public String vulStatData(@RequestParam("startTime") String startTime,@RequestParam("endTime") String endTime){
		StringBuffer result = new StringBuffer();
		result.append("{\"eventStat\":" + echartsHome.getEventStatByDate(startTime, endTime));
		result.append(",\"alertStat\":" + echartsHome.getAlertStatByRulename(startTime, endTime));
		result.append("}");

		return result.toString();
	}
	
	@RequestMapping(value = "report/event/topevent",method = RequestMethod.POST)
	@ResponseBody
	public String getTopEvent() {
		String option=echartsHome.getTopEvent(6);
		return option;
	}
	
	@RequestMapping(value = "report/alert/topalert",method = RequestMethod.POST)
	@ResponseBody
	public String getTopAlert() {
		String option=echartsHome.getTopAlert(6);
		return option;
	}
}
