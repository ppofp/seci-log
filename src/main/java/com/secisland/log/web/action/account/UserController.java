/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.log.web.action.account;

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

import com.secisland.log.web.dto.ChangePasswordParam;
import com.secisland.log.web.entity.SmRole;
import com.secisland.log.web.entity.SmUser;
import com.secisland.log.web.service.ServiceException;
import com.secisland.log.web.service.account.AccountService;
import com.secisland.log.web.service.authority.AuthorityService;
import com.secisland.log.web.service.role.RoleService;

@Controller
@RequestMapping(value = "/account/user")
public class UserController {
	private static final String PAGE_SIZE = "15";
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private AuthorityService authService;
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(method = RequestMethod.GET)
    public String list(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
            @RequestParam(value = "page.size",defaultValue = PAGE_SIZE) int pageSize,
            @RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
            ServletRequest request) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        Page<SmUser> objects = accountService.getUserPage(searchParams, pageNumber, pageSize, sortType);

        model.addAttribute("selectmenu", authService.getMenuSelectCode(request));
        model.addAttribute("menutitle", authService.getMenuTitle(request,"用户列表"));
        
		model.addAttribute("objects", objects);
        model.addAttribute("sortType", sortType);
        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		return "account/userList";
	}
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String create(Model model,ServletRequest request) {
		model.addAttribute("selectmenu", authService.getMenuSelectCode(request));
		model.addAttribute("menutitle", authService.getMenuTitle(request,"用户编辑"));
		model.addAttribute("role",roleService.getRoleListInUser());
		model.addAttribute("userRole","[]");
		return "account/userForm";
	}
	@RequestMapping(value = "add-update",method = RequestMethod.POST)
	public String create(@Valid SmUser user, String userRole,  BindingResult result,
			Model model, RedirectAttributes attributes){
    	if(result.hasErrors()) {
            List<FieldError> errorList = result.getFieldErrors();
            StringBuffer retError = new StringBuffer("");
            for ( FieldError error : errorList){
            	retError.append(error.getDefaultMessage()+"<br>");
            }
            attributes.addFlashAttribute("user", user);
			attributes.addFlashAttribute("error", retError.toString() );
            return "redirect:/account/user/add";
        }  
    	if ( user.getId() == null ){
    		accountService.registerUser(user);
    		user = accountService.findUserByLoginName(user.getUserCode());
    		accountService.addUserRole(user, userRole);
            attributes.addFlashAttribute("code",100);
    		attributes.addFlashAttribute("message", "创建用户" + user.getUserName() + "成功");
    	} else {
    		if(user.getId() != 1){
    			accountService.updateUser(user);
    		}
    		accountService.addUserRole(user, userRole);
            attributes.addFlashAttribute("code",100);
    		attributes.addFlashAttribute("message", "更新用户" + user.getUserName() + "成功");
    	}

		return "redirect:/account/user";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model,ServletRequest request) {
		SmUser user = accountService.getUser(id);
		
		model.addAttribute("selectmenu", authService.getMenuSelectCode(request));
		model.addAttribute("menutitle", authService.getMenuTitle(request,"用户编辑"));
		model.addAttribute("user", user);
		model.addAttribute("role",roleService.getRoleListInUser());
		model.addAttribute("userRole",userRole2Json(user));
		
		return "account/userForm";
	}
	@RequestMapping(value = "changepwd/{username}", method = RequestMethod.GET)
	public String changepwd(@PathVariable("username")String userName,Model model,ServletRequest request) {
		model.addAttribute("selectmenu", authService.getMenuSelectCode(request));
		model.addAttribute("menutitle", authService.getMenuTitle(request,"修改密码"));
		SmUser user = accountService.findUserByLoginName(userName);
		model.addAttribute("user", user);
		return "account/changepwd";
	}

	@RequestMapping(value = "dochangepwd/{id}", method = RequestMethod.GET)
	public String dochangepwd(@Valid ChangePasswordParam param,@PathVariable("id")Long id,Model model,ServletRequest request, RedirectAttributes attributes)
	{
		SmUser user = accountService.getUser(id);
		StringBuffer resultSBuffer = new StringBuffer();
		resultSBuffer.append("{");
		if(param.checkpassword()){
			if(user.getPassword().equals(accountService.getEntryptPassword(param.getOldPassword(), user.getSalt()))){
				user.setPassword(accountService.getEntryptPassword(param.getNewPassword(), user.getSalt()));
				try {
					accountService.updateUser(user);
					resultSBuffer.append("\"code\":\"100\",\"message\":\"修改密码成功！\"");

				} catch (Exception e) {
					resultSBuffer.append("\"code\":0,\"message\":\"修改密码失败!\"");
				}
			}else{
				resultSBuffer.append("\"code\":0,\"message\":\"原密码输入有误!\"");
			}
							
		}else{
			resultSBuffer.append("\"code\":0,\"message\":\"两次输入密码不同!\"");
		}
		resultSBuffer.append("}");
		attributes.addFlashAttribute("result", resultSBuffer.toString());
		model.addAttribute("user", user);
		return "redirect:/account/user/changepwd/" + user.getUserName();
	}
	
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		SmUser user = accountService.getUser(id);
		try {
			accountService.deleteUser(id);
		} catch ( ServiceException e ){
            redirectAttributes.addFlashAttribute("code",0);
			redirectAttributes.addFlashAttribute("message", "删除失败，"+e.getMessage());
			return "redirect:/account/user";
		}
		redirectAttributes.addFlashAttribute("code",100);
		redirectAttributes.addFlashAttribute("message", "删除用户" + user.getUserName() + "成功");
		return "redirect:/account/user";
	}

	@RequestMapping(value = "checkLoginName")
	@ResponseBody
	public String checkLoginName(@RequestParam("userCode") String loginName) {
		if (accountService.findUserByLoginName(loginName) == null) {
			return "true";
		} else {
			return "false";
		}
	}
	
	private String userRole2Json(SmUser user) {
		StringBuffer userRole = new StringBuffer();
		userRole.append("[");
		if(user != null && user.getSmRoles() != null && user.getSmRoles().size() > 0){
			List<SmRole> roles = user.getSmRoles();
			int count = roles.size();
			for(int i = 0; i < count; i++){
				if(i != 0){
					userRole.append(",");
				}
				userRole.append("{'id':" + roles.get(i).getId() +",'name':'" + roles.get(i).getRoleName() + "'}");
			}
		}
		userRole.append("]");
		
		return userRole.toString();
	}
}
