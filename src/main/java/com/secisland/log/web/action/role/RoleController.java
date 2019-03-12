/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.log.web.action.role;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.secisland.log.web.entity.SmRole;
import com.secisland.log.web.service.authority.AuthorityService;
import com.secisland.log.web.service.role.RoleService;

@Controller
@RequestMapping(value = "/role")
public class RoleController {
	@Autowired
	private AuthorityService authService;
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(method = RequestMethod.GET)
    public String list(Model model,ServletRequest request) {
		List<SmRole> objects = roleService.getRoleList();
        model.addAttribute("selectmenu", authService.getMenuSelectCode(request));
        model.addAttribute("menutitle", authService.getMenuTitle(request,"角色列表"));
        model.addAttribute("objects", objects);
		return "role/roleList";
	}
	
	@RequestMapping(value = "add",method = RequestMethod.GET)
    public String add(Model model,ServletRequest request) {
		model.addAttribute("selectmenu", authService.getMenuSelectCode(request));
		model.addAttribute("menutitle", authService.getMenuTitle(request,"用户编辑"));
	
		return "role/roleForm";
	}
	
	@RequestMapping(value = "updata/{id}",method = RequestMethod.GET)
    public String updata(@PathVariable("id") Long id,Model model,ServletRequest request) {
		model.addAttribute("selectmenu", authService.getMenuSelectCode(request));
		model.addAttribute("menutitle", authService.getMenuTitle(request,"用户编辑"));
		model.addAttribute("role", roleService.getRoleById(id));

		return "role/roleForm";
	}
	
	@RequestMapping(value = "add-update",method = RequestMethod.POST)
	public String addUpdata(@Valid SmRole role,BindingResult result,
			Model model, RedirectAttributes attributes){
		
		if(role.getId() == null){
			roleService.addRole(role);
		}else{
			roleService.updataRole(role);
		}
		return "redirect:/role/";
	}
	
	@RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
	 public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		SmRole role = roleService.getRoleById(id);
		if(role != null && role.getId() != null){
			try {
				roleService.deleteRole(id);
				redirectAttributes.addFlashAttribute("code",100);
				redirectAttributes.addFlashAttribute("message", "删除角色" + role.getRoleName() + "成功");

			} catch (Exception e) {
	            redirectAttributes.addFlashAttribute("code",0);
				redirectAttributes.addFlashAttribute("message", "删除失败，" +e.getMessage());
				return "redirect:/role/";
			}
		}else{
			 redirectAttributes.addFlashAttribute("code",0);
			 redirectAttributes.addFlashAttribute("message", "删除角色失败。");
		}
		
		return "redirect:/role/";
	}
	@RequestMapping(value = "rolemenu/{id}",method = RequestMethod.GET)
	public String roleMenu(@PathVariable("id") Long id,Model model,ServletRequest request, RedirectAttributes redirectAttributes){
		String menuList = roleService.getMenuList();
		String roleMenu = roleService.getRoleMenuJson(id);
		
		model.addAttribute("selectmenu", authService.getMenuSelectCode(request));
		model.addAttribute("menutitle", authService.getMenuTitle(request,"角色授权"));
		model.addAttribute("role", roleService.getRoleById(id));
		
		model.addAttribute("menuList",menuList);
		model.addAttribute("roleMenu",roleMenu);

		return "role/roleMenu";
	}
	
	@RequestMapping(value = "rolemenu/add/{id}",method = RequestMethod.GET)
	public String addUpdataRoleMenu(@PathVariable("id") Long id, String menuids,
			Model model, RedirectAttributes attributes){
		List<Long> menus = new ArrayList<Long>();
		String[] temp = menuids.split(",");
		if(temp != null && temp.length > 0){
			for(int i = 0;i < temp.length; i++){
				
				menus.add(Long.parseLong(temp[i]));
			}
		}
		roleService.changeRoleMenu(id, menus);
		return "redirect:/role/";
	}
}
