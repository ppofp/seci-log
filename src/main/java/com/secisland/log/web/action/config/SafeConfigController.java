/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.log.web.action.config;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.secisland.log.web.entity.BaseDic;
import com.secisland.log.web.entity.BaseDicDetail;
import com.secisland.log.web.service.ServiceException;
import com.secisland.log.web.service.authority.AuthorityService;
import com.secisland.log.web.service.config.SafeConfigService;

@Controller
@RequestMapping(value = "/config/safeconfig")
public class SafeConfigController {

	@Autowired
	private AuthorityService authService;
	@Autowired
	private SafeConfigService safeConfig;
	
	@RequestMapping(method = RequestMethod.GET)
    public String getConfig( Model model, ServletRequest request) {
        model.addAttribute("selectmenu", authService.getMenuSelectCode(request));
        model.addAttribute("menutitle", authService.getMenuTitle(request,"安全配置"));
        List<BaseDic> dicList = safeConfig.getDicList();
        List<BaseDicDetail> detailList = null;
        if ( dicList!=null && dicList.size() > 1 ){
        	detailList = safeConfig.getDicDetailList(dicList.get(0).getId());
        	model.addAttribute("dicid",1);
        }
        
        model.addAttribute("dicList",dicList);
        model.addAttribute("detailList",detailList);
        
		return "config/safeconfig";
	}
	
	@RequestMapping(value = "{dicid}")
	public String getConfigById(@PathVariable("dicid") Long dicid,Model model, ServletRequest request) {
        model.addAttribute("selectmenu", authService.getMenuSelectCode(request));
        model.addAttribute("menutitle", authService.getMenuTitle(request,"安全配置"));
        List<BaseDic> dicList = safeConfig.getDicList();
        List<BaseDicDetail> detailList = null;
        if ( dicList!=null && dicList.size() > 1 ){
        	detailList = safeConfig.getDicDetailList(dicid);
        }
        model.addAttribute("dicid",dicid);
        model.addAttribute("dicList",dicList);
        model.addAttribute("detailList",detailList);
        
		return "config/safeconfig";
	}
	
	@RequestMapping(value = "detail/add/{dicid}")
	public String adddetail(@PathVariable("dicid") Long dicid,Model model, ServletRequest request) {
        BaseDicDetail detail = new BaseDicDetail();
        detail.setBaseDicId(dicid);
        
        model.addAttribute("selectmenu", authService.getMenuSelectCode(request));
        model.addAttribute("menutitle", authService.getMenuTitle(request,"安全配置增加"));
        model.addAttribute("object",detail);
        model.addAttribute("dicMsg",getConfigRemark(dicid));
        
		return "config/safeconfigform";
	}
	
	@RequestMapping(value = "detail/change/{detailid}")
	public String chgdetail(@PathVariable("detailid") Long detailid,Model model, ServletRequest request) {
        BaseDicDetail detail = safeConfig.getDetail(detailid);
        
        model.addAttribute("selectmenu", authService.getMenuSelectCode(request));
        model.addAttribute("menutitle", authService.getMenuTitle(request,"安全配置修改"));
        model.addAttribute("object",detail);
        model.addAttribute("dicMsg",getConfigRemark(detail.getBaseDicId()));
        
		return "config/safeconfigform";
	}
	
	@RequestMapping(value = "detail/add-update",method = RequestMethod.POST)
	public String create(@Valid BaseDicDetail detail, BindingResult result,
			Model model, RedirectAttributes attributes){
    	if(result.hasErrors()) {
            List<FieldError> errorList = result.getFieldErrors();
            StringBuffer retError = new StringBuffer("");
            for ( FieldError error : errorList){
            	retError.append(error.getDefaultMessage()+"<br>");
            }
            attributes.addFlashAttribute("detail", detail);
			attributes.addFlashAttribute("error", retError.toString() );
            return "redirect:/config/safeconfig/"+detail.getBaseDicId();
        }  
    	if ( detail.getId() == null ){
    		safeConfig.createDicDetail(detail);
            attributes.addFlashAttribute("code",100);
    		attributes.addFlashAttribute("message", "创建配置" + detail.getName() + "成功");
    	} else {
    		safeConfig.updateDicDetail(detail);
            attributes.addFlashAttribute("code",100);
    		attributes.addFlashAttribute("message", "更新配置" + detail.getName() + "成功");
    	}

		return "redirect:/config/safeconfig/"+detail.getBaseDicId();
	}
	
	@RequestMapping(value = "detail/delete/{detailid}")
	public String deldetail(@PathVariable("detailid") Long detailid,Model model, RedirectAttributes attributes) {
		BaseDicDetail detail = safeConfig.getDetail(detailid);
		Long dicid = detail.getBaseDicId();
		try {
			safeConfig.deleteDetail(detailid);
		} catch ( ServiceException e ){
			attributes.addFlashAttribute("code",0);
			attributes.addFlashAttribute("message", "删除失败，"+e.getMessage());
			return "redirect:/account/user";
		}
		attributes.addFlashAttribute("code",100);
		attributes.addFlashAttribute("message", "删除配置" + detail.getName() + "成功");
		return "redirect:/config/safeconfig/"+dicid;
	}
	
	private String getConfigRemark(Long id){
		String retMsg = "";
		if ( id==1L ){ //非上班时间 
			retMsg = "非上班时间配置：配置时间段，是到0点的秒数，比如0点-1点，值为0-3600";
		} else if ( id==2L ){ //工作ip
			retMsg = "工作IP配置：配置工作IP，写法为ip/24,单个ip可以直接写ip,或者IP/32";
		}
		return retMsg;
	}
}
