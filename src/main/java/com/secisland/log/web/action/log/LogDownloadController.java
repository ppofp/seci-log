/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.log.web.action.log;

import java.io.BufferedOutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springside.modules.web.Servlets;

import com.secisland.log.web.service.log.LogDownloadService;

@Controller
@RequestMapping(value = "/log/download")
public class LogDownloadController {
	@Autowired
	private LogDownloadService downloadService;
	
    @RequestMapping(value = "systemlog")  
    public void downloadSystemlod(HttpServletRequest request,  HttpServletResponse response) throws Exception {  
        response.setContentType("text/html;charset=UTF-8");   
        BufferedOutputStream out = null;  
        request.setCharacterEncoding("UTF-8");   
        String fileName = "systemlog.xlsx";  
        try {  
        	Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");  
            response.setContentType("application/x-excel");  
            response.setCharacterEncoding("UTF-8");  
            response.setHeader("Content-Disposition", "attachment; filename="+fileName);  
            //response.setHeader("Content-Length",String.valueOf(f.length()));  
            out = new BufferedOutputStream(response.getOutputStream()); 
            downloadService.writeSystemLog(searchParams,out);
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {   
            if (out != null) {
            	out.flush();
                out.close();  
            }  
        }  
    }
}
