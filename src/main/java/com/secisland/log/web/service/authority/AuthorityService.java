/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.log.web.service.authority;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.secisland.core.util.LoginUserInfo;
import com.secisland.log.web.entity.SmMenu;
import com.secisland.log.web.repository.SmMenuDao;

@Component
@Transactional
public class AuthorityService {

	@Autowired
	private SmMenuDao menuDao;
	
	private static List<SmMenu> menuList = null;
	
	public List<SmMenu> getAllMenu() {
		return (List<SmMenu>) menuDao.findMenuByUserId(LoginUserInfo.getLoginUserId());
	}
	
	public String getMenuString(String path){
		menuList = getAllMenu();
		
		boolean isChild = false;
		StringBuffer strBuffer = new StringBuffer("");
		for (SmMenu menu:menuList){
			if (menu.getActiveFlag().equals("N")){
				continue;
			}
			if (menu.getSmMenu()==null){//根节点
				if(menu.getMenuCode().equals("home")){
					strBuffer.append("<li><a id=\""+menu.getMenuCode()+"\" href=\""+path+"/#\" class=\"side-head\">"+
			                "<i class=\"glyphicon glyphicon-home\"></i>"+menu.getMenuName()+
			                "<i class=\"glyphicon glyphicon-chevron-down pull-right\"></i></a></li>");
				}else{
					if ( isChild ){
						strBuffer.append("</ul></li>");
						isChild = false;
					}
					strBuffer.append("<li><a id=\""+menu.getMenuCode()+"\" href=\"#\" class=\"side-head\">"+
			                "<i class=\"glyphicon glyphicon-home\"></i>"+menu.getMenuName()+
			                "<i class=\"glyphicon glyphicon-chevron-down pull-right\"></i></a><ul>");
					isChild = true;
				}
			} else {
				strBuffer.append("<li><a id=\"menu-"+menu.getMenuCode()+"\" href=\""+path+menu.getMenuLink()+"\" title=\""+
						menu.getMenuName()+"\">"+ menu.getMenuName()+"</a></li>");
			}
		}
		strBuffer.append("</ul></li>");
		return strBuffer.toString();
	}
	
	public String getMenuSelectCode(ServletRequest request){
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        String url = httpRequest.getRequestURI().toString();
        if ( url.indexOf(httpRequest.getContextPath())>=0&&(url.length()>httpRequest.getContextPath().length())){
        	int length = httpRequest.getContextPath().length();
        	url = url.substring(length, url.length());
        }
		if ( menuList == null ){
			menuList = getAllMenu();
		}
        for (SmMenu menu:menuList){
        	if (menu.getMenuLink().equals("/")){ //首页直接跳出循环
                continue;
    		} 
        	if ( menu.getMenuLink().length()>0 && url.indexOf(menu.getMenuLink()) >= 0){
        		return "menu-"+menu.getMenuCode();
        	}
        }
        return url;
	}
	
	public String getMenuTitle(ServletRequest request,String last){
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        String url = httpRequest.getRequestURI().toString();
        if ( url.indexOf(httpRequest.getContextPath())>=0&&(url.length()>httpRequest.getContextPath().length())){
        	int length = httpRequest.getContextPath().length();
        	url = url.substring(length, url.length());
        }
		if ( menuList == null ){
			menuList = getAllMenu();
		}
		
		List<SmMenu> menuNameList = new ArrayList<SmMenu>();
		Long parentId = 0L;
		StringBuffer strBuf = new StringBuffer("<ul class=\"center-path\">");
		strBuf.append("<li><i class=\"glyphicon glyphicon-folder-close\"></i></li>");
        for (SmMenu menu:menuList){
        	if ( menu.getMenuLink().length()>0 && url.indexOf(menu.getMenuLink()) >= 0){
        		if (url.equals("/")){ //首页直接跳出循环
        			menuNameList.add(menu);
        			break;
        		}
        		if ( menu.getSmMenu() != null ){
        			menuNameList.add(menu);
        			parentId = menu.getSmMenu().getId();
        			break;
        		}
        	}
        }
        while ( parentId > 0 ){
            for (SmMenu menuTemp:menuList){
    			if (menuTemp.getId() == parentId ){ //父节点
    				menuNameList.add(menuTemp);
    				if ( menuTemp.getSmMenu() == null ){
    					parentId= 0L;
    					break;
    				}
    				parentId = menuTemp.getSmMenu().getId();
    			}
    		}
        }
        Object menuArray[] = menuNameList.toArray();
        for (int i=menuNameList.size()-1; i>=0;i--){
        	SmMenu menuTemp = (SmMenu)menuArray[i];
        	String menuUrl = menuTemp.getMenuLink();
        	if ( menuUrl == null || menuUrl.length() == 0 ){
        		menuUrl = new String("#");
        	} else {
        		menuUrl = httpRequest.getContextPath()+menuUrl;
        	}
        	strBuf.append("<li><a href=\""+menuUrl+"\">"+menuTemp.getMenuName()+"</a></li><li>");
        	strBuf.append("<i class=\"glyphicon glyphicon-chevron-right\"></i></li>");
        }
        if ( last != null && last.length()>0 ){
        	strBuf.append("<li>"+last+"</li>");
        }
        strBuf.append("</ul>");
        return strBuf.toString();
	}
}
