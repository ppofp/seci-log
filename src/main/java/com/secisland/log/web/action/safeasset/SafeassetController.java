/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.log.web.action.safeasset;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;

import com.secisland.log.rmi.RmiCommunication;
import com.secisland.log.web.entity.BaseSafeAsset;
import com.secisland.log.web.service.authority.AuthorityService;
import com.secisland.log.web.service.safeasset.SafeassetService;

@Controller
@RequestMapping(value = "/safeasset")
public class SafeassetController {
	private static final String PAGE_SIZE = "15";
	
	@Autowired
	private AuthorityService authService;
	@Autowired
	private SafeassetService safeassetService;
	
	@RequestMapping(value = "asset",method = RequestMethod.GET)
    public String getEventLog(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
            @RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
            @RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
            ServletRequest request) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        
        Page<BaseSafeAsset> objects = safeassetService.getAssetPage(searchParams, pageNumber, pageSize, sortType);

        model.addAttribute("selectmenu", authService.getMenuSelectCode(request));
        model.addAttribute("menutitle", authService.getMenuTitle(request,"安全资产"));
        
		model.addAttribute("objects", objects);
        model.addAttribute("sortType", sortType);
        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		return "safeasset/asset";
	}
	
	@RequestMapping(value = "asset/{id}")
	public String getAssetDetail(@PathVariable("id") Long id,Model model, ServletRequest request) {
		BaseSafeAsset object = safeassetService.getAssetDetail(id);

        model.addAttribute("selectmenu", authService.getMenuSelectCode(request));
        model.addAttribute("menutitle", authService.getMenuTitle(request,"资产修改"));
        
		model.addAttribute("object", object);
		return "safeasset/assetForm";
	}
	
    @RequestMapping(value = "asset/add", method = RequestMethod.GET)
    public String add(Model model,ServletRequest request){
    	model.addAttribute("selectmenu", authService.getMenuSelectCode(request));
        model.addAttribute("menutitle", authService.getMenuTitle(request,"添加资产"));
        
        return  "safeasset/assetForm";
    }
    
    //通过扫描添加
    @RequestMapping(value = "asset/scanadd", method = RequestMethod.GET)
    public String scanAdd(Model model,ServletRequest request){
    	model.addAttribute("selectmenu", authService.getMenuSelectCode(request));
        model.addAttribute("menutitle", authService.getMenuTitle(request,"扫描添加资产"));
        RmiCommunication rmiCommunication = new RmiCommunication();
        boolean isStart = rmiCommunication.isFindHost();
        model.addAttribute("isStart", isStart);
        
        return  "safeasset/scanAssetForm";
    }
    
    //通过扫描添加
    @RequestMapping(value = "asset/scanadd", method = RequestMethod.POST)
    public String scanPostAdd(String ips,ServletRequest  request,RedirectAttributes attributes){
        RmiCommunication rmiCommunication = new RmiCommunication();
        if (!rmiCommunication.isFindHost()){
        	rmiCommunication.findHost(ips);
        }
        attributes.addFlashAttribute("ips", ips);
        attributes.addFlashAttribute("isStart", true);
        
        return "redirect:/safeasset/asset/scanadd";
    }
    
    @RequestMapping(value = "add-update",method = RequestMethod.POST)
    public String create(@Valid BaseSafeAsset asset,BindingResult result,
                         Model model, RedirectAttributes attributes) {
        if(result.hasErrors()){
            List<FieldError> errorList = result.getFieldErrors();
            StringBuffer retError = new StringBuffer("");
            for ( FieldError error : errorList){
                retError.append(error.getDefaultMessage()+"<br>");
            }
            attributes.addFlashAttribute("asset", asset);
            attributes.addFlashAttribute("error", retError.toString() );
            return "redirect:/safeasset/add";
        }
        if(asset.getId() == null){
        	safeassetService.addAsset(asset);
            attributes.addFlashAttribute("code",100);
            attributes.addFlashAttribute("message", "创建资产" + asset.getSafeAssetCode() + "成功");
        }else {
        	safeassetService.update(asset);
            attributes.addFlashAttribute("code",100);
            attributes.addFlashAttribute("message", "修改资产" + asset.getSafeAssetCode() + "成功");
        }
        return "redirect:/safeasset/asset";
    }
    
    @RequestMapping(value = "delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
    	BaseSafeAsset asset = safeassetService.getAssetDetail(id);
        try{
        	safeassetService.delete(id);
            redirectAttributes.addFlashAttribute("code",100);
            redirectAttributes.addFlashAttribute("message", asset.getSafeAssetCode() + "资产删除成功 !");
        }catch (Exception ex){
            redirectAttributes.addFlashAttribute("code",0);
            redirectAttributes.addFlashAttribute("message", asset.getSafeAssetCode() + "资产删除成功!");
        }
        return "redirect:/safeasset/asset";
    }
    
    @ResponseBody
    @RequestMapping(value = "asset/getstatus", method = RequestMethod.GET)
    public String getScanStatus(Model model,ServletRequest request){
    	RmiCommunication rmiCommunication = new RmiCommunication();
        boolean isStart = rmiCommunication.isFindHost();
        return Boolean.toString(isStart);
    }
}
