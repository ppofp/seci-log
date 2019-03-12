/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.log.web.action.organize;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;

import com.secisland.log.web.entity.SmOrganization;
import com.secisland.log.web.service.authority.AuthorityService;
import com.secisland.log.web.service.organize.SmOrganizeService;

@Controller
@RequestMapping(value = "/organize")
public class SmOrganizeController {
    private static final String PAGE_SIZE = "15";
    @Autowired
    private SmOrganizeService smOrganizeService;

    @Autowired
    private AuthorityService authService;


    @RequestMapping(method = RequestMethod.GET)
    public String list(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
                       @RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
                       @RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
                       ServletRequest request){
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        model.addAttribute("selectmenu", getSelectMenu(request));
        model.addAttribute("menutitle", authService.getMenuTitle(request,"组织列表"));

        Page<SmOrganization> objects = smOrganizeService.getOrganizePage(searchParams, pageNumber, pageSize, sortType);
        model.addAttribute("organizes", objects);
        model.addAttribute("sortType", sortType);
        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));

        return "organize/list";
    }
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model,ServletRequest request){
        model.addAttribute("selectmenu", getSelectMenu(request));
        model.addAttribute("menutitle", authService.getMenuTitle(request,"添加组织"));

        return  "organize/organizeForm";
    }
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, Model model,ServletRequest request) {
        SmOrganization organization = smOrganizeService.getSmOrganizeById(id);
        model.addAttribute("selectmenu", getSelectMenu(request));
        model.addAttribute("menutitle", authService.getMenuTitle(request,"修改组织"));
        model.addAttribute("organize", organization);
        return  "organize/organizeForm";
    }

    @RequestMapping(value = "add-update",method = RequestMethod.POST)
    public String create(@Valid SmOrganization organize,BindingResult result,
                         Model model, RedirectAttributes attributes) {
        if(result.hasErrors()){
            List<FieldError> errorList = result.getFieldErrors();
            StringBuffer retError = new StringBuffer("");
            for ( FieldError error : errorList){
                retError.append(error.getDefaultMessage()+"<br>");
            }
            attributes.addFlashAttribute("user", organize);
            attributes.addFlashAttribute("error", retError.toString() );
            return "redirect:/organize/add";
        }
        if(organize.getId() == null){
            smOrganizeService.addOrganize(organize);
            attributes.addFlashAttribute("code",100);
            attributes.addFlashAttribute("message", "创建组织成功" + organize.getGroupName() + "成功");
        }else {
            smOrganizeService.update(organize);
            attributes.addFlashAttribute("code",100);
            attributes.addFlashAttribute("message", "修改组织成功" + organize.getGroupName() + "成功");
        }
        return "redirect:/organize/";
    }

    @RequestMapping(value = "delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        SmOrganization organize = smOrganizeService.getSmOrganizeById(id);
        try{
            smOrganizeService.deleteSmOrganize(id);
            redirectAttributes.addFlashAttribute("code",100);
            redirectAttributes.addFlashAttribute("message", organize.getGroupName() + "组织删除成功 !");
        }catch (Exception ex){
            redirectAttributes.addFlashAttribute("code",0);
            redirectAttributes.addFlashAttribute("message", organize.getGroupName() + "组织下存在子组织或人员，不允许删除!");
        }
        return "redirect:/organize/";
    }

    private String getSelectMenu(ServletRequest request){
        String selectmenu = authService.getMenuSelectCode(request);
        if(selectmenu.equals("/")){
            selectmenu = "home";
        }
        return  selectmenu;
    }
}
