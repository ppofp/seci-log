/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.log.web.service.role;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.secisland.core.util.LoginUserInfo;
import com.secisland.log.web.entity.SmMenu;
import com.secisland.log.web.entity.SmRole;
import com.secisland.log.web.repository.SmMenuDao;
import com.secisland.log.web.repository.SmRoleDao;

@Component
@Transactional
public class RoleService {
	
	@Autowired
	SmRoleDao roleDao;
	
	@Autowired
	private SmMenuDao menuDao;

	public List<SmRole> getRoleList() {
		List<SmRole> roleList = roleDao.findAll();
		//List<SmRole> roleList = new ArrayList<SmRole>();
		return roleList;
	}
	
	public void addRole(SmRole role){
		SmRole newRole = new SmRole();

		newRole.setActiveFlag("Y");
		newRole.setCreatedBy(LoginUserInfo.getLoginUserId());
		newRole.setCreatedDate(new Date());
		newRole.setRoleCode(role.getRoleCode());
		newRole.setRoleName(role.getRoleName());

		roleDao.insert(newRole);
		
	}
	
	public void updataRole(SmRole role){
		role.setLastUpdBy(LoginUserInfo.getLoginUserId());
		role.setLastUpdDate(new Date());
		
		roleDao.update(role);		
	}
	
	public void deleteRole(Long id) {
		roleDao.delete(id);
	}
	
	public SmRole getRoleById(Long id) {
		return roleDao.findOne(id);
	}
	
	public List<SmMenu> getAllMenu() {
		List<SmMenu> menuAllList = menuDao.findAll();
		List<SmMenu> menuList = new ArrayList<SmMenu>();
		if(menuAllList != null && menuAllList.size() > 0){
			for(SmMenu menu:menuAllList){
				if(menu.getSmMenu() == null){
					menuList.add(menu);
				}
			}
		}
		return menuList;
	}
	
	public String getMenuList() {
		List<SmMenu> menuList = getAllMenu();
		StringBuffer roleMenuList = new StringBuffer();
		
		if(menuList != null && menuList.size() > 0){
			for(SmMenu menu:menuList){
				roleMenuList.append("<div class=\"row menu-list\" style=\"padding-bottom:20px; border-bottom:1px solid #ccc;\">");
				if(menu != null){
					roleMenuList.append("<div class=\"col-xs-12\"><div class=\"checkbox\"><label id=\"menu_" + menu.getId() + "\" data-menuid=\"" + menu.getId() +"\"><input type=\"checkbox\"><strong>" + menu.getMenuName()+"</strong></label></div></div>");
					List<SmMenu> menus = menu.getSmMenus();
					
					if(menus != null && menus.size() > 0){
						for(SmMenu childMenu:menus){
							roleMenuList.append("<div class=\"col-xs-3\"><div class=\"checkbox\"><label id=\"menu_" + childMenu.getId() + "\" data-menuid=\"" + childMenu.getId() +"\"><input type=\"checkbox\">" + childMenu.getMenuName()+"</label></div></div>");
						}
					}
				}
				roleMenuList.append("</div>");
			}
		}
		return roleMenuList.toString();		
	}
	
	public String getRoleMenuJson(long roleId) {
		SmRole role = roleDao.findOne(roleId);		
		StringBuffer allMenuInfo = new StringBuffer();
		allMenuInfo.append("[");
		if(role != null && role.getSmMenus() != null && role.getSmMenus().size() > 0){
			List<SmMenu> menuList = role.getSmMenus();
			int menuCount = menuList.size();
			
			for(int i = 0; i < menuCount; i++){
				if(i != 0){
					allMenuInfo.append(",");
				}
				allMenuInfo.append("{'id':" + menuList.get(i).getId() + ",'name':'" + menuList.get(i).getMenuName()+ "'}");
			}
		}
		allMenuInfo.append("]");
		return allMenuInfo.toString();
	}
	
	public void changeRoleMenu(long roleId, List<Long> menuIds){
		List<SmMenu> menuList = menuDao.findAll(menuIds);
		SmRole role = roleDao.findOne(roleId);
		role.setSmMenus(menuList);
		roleDao.save(role);
	}
	
	public String getRoleListInUser() {
		List<SmRole> roleList = roleDao.findAll();
		StringBuffer roleStringBuffer = new StringBuffer();
		
		roleStringBuffer.append("<div class=\"control-group col-xs-12\" style=\"height:auto;\"><label class=\"control-label\">角色分配</label><input type='hidden' name=\"userRole\" id=\"userRole\" value=\"\"/></div>");
		if(roleList != null && roleList.size() > 0){
			for(int i = 0; i < roleList.size(); i++){
				roleStringBuffer.append("<div class=\"col-xs-3\"><div class=\"checkbox\"><label data-roleid=\""+ roleList.get(i).getId() +"\" id=\"role_"+ roleList.get(i).getId() +"\"><input type=\"checkbox\">"+ roleList.get(i).getRoleName() +"</label></div></div>");
			}
		}
		return roleStringBuffer.toString();
	}
}