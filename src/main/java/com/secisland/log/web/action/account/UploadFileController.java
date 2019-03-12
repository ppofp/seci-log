/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.log.web.action.account;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.secisland.log.web.service.account.UploadFileService;
import com.secisland.log.web.service.authority.AuthorityService;
import com.secisland.log.web.util.UnifyFileName;

@Controller
@RequestMapping(value = "/account/user")
public class UploadFileController {

	@Value("#{configProperties['upload.path']}")
	private String path;
	
    @Autowired
    ServletContext context;
    @Autowired
    UploadFileService execute;
	@Autowired
	private AuthorityService authService;
	
	@RequestMapping(value = "upload", method = RequestMethod.GET)
	public String upload(Model model,ServletRequest request) {
		model.addAttribute("selectmenu", authService.getMenuSelectCode(request));
		model.addAttribute("menutitle", authService.getMenuTitle(request,"用户导入"));
		return "account/upload";
	}
	
    @RequestMapping(value = "upload",method = RequestMethod.POST)
    public String uploadFile(HttpServletRequest request,Model model) throws IOException {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        String fullPath = "";
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
            	String retMsg = "";
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null ) {
                	if ( file.getSize() > 2097152 ){
                		retMsg = "不能上传大于2M的文件！";
                		return "account/upload";
                	}
                    String fileName = UnifyFileName.getUnifyFileName(file.getOriginalFilename());
                    fullPath = path + "/temp"+ "/"+ fileName; 
                    String savePath = context.getRealPath(fullPath);
                    File localFile = new File(savePath);
                    if(!localFile.getParentFile().exists()) {
                        localFile.getParentFile().mkdirs();
                    }
                    file.transferTo(localFile);
                    retMsg = execute.process(localFile);
                    model.addAttribute("message", retMsg);
                    model.addAttribute("selectmenu", authService.getMenuSelectCode(request));
                }
            }
        }
        return "account/upload";
    }
    
    @RequestMapping(value = "download")  
    public void download(HttpServletRequest request,  HttpServletResponse response) throws Exception {  
        response.setContentType("text/html;charset=UTF-8");   
        BufferedInputStream in = null;  
        BufferedOutputStream out = null;  
        request.setCharacterEncoding("UTF-8");  
        String rootpath = request.getSession().getServletContext().getRealPath( "/");  
        String fileName = "UserUpload.xls";  
        try {  
            File f = new File(rootpath + "/" + fileName);  
            response.setContentType("application/x-excel");  
            response.setCharacterEncoding("UTF-8");  
              response.setHeader("Content-Disposition", "attachment; filename="+fileName);  
            response.setHeader("Content-Length",String.valueOf(f.length()));  
            in = new BufferedInputStream(new FileInputStream(f));  
            out = new BufferedOutputStream(response.getOutputStream());  
            byte[] data = new byte[1024];  
            int len = 0;  
            while (-1 != (len=in.read(data, 0, data.length))) {  
                out.write(data, 0, len);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (in != null) {  
                in.close();  
            }  
            if (out != null) {  
                out.close();  
            }  
        }  
    }  
    
    @RequestMapping(value = "download/auth")  
    public void downloadAuth(HttpServletRequest request,  HttpServletResponse response) throws Exception {  
        response.setContentType("text/html;charset=UTF-8");   
        BufferedInputStream in = null;  
        BufferedOutputStream out = null;  
        request.setCharacterEncoding("UTF-8");  
        String rootpath = request.getSession().getServletContext().getRealPath( "/");  
        String fileName = "UserAuthUpload.xls";  
        try {  
            File f = new File(rootpath + "/" + fileName);  
            response.setContentType("application/x-excel");  
            response.setCharacterEncoding("UTF-8");  
              response.setHeader("Content-Disposition", "attachment; filename="+fileName);  
            response.setHeader("Content-Length",String.valueOf(f.length()));  
            in = new BufferedInputStream(new FileInputStream(f));  
            out = new BufferedOutputStream(response.getOutputStream());  
            byte[] data = new byte[1024];  
            int len = 0;  
            while (-1 != (len=in.read(data, 0, data.length))) {  
                out.write(data, 0, len);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (in != null) {  
                in.close();  
            }  
            if (out != null) {  
                out.close();  
            }  
        }  
    }
}
