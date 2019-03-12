/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.log.web.service.account;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.secisland.log.web.entity.SmUser;
import com.secisland.log.web.repository.SmOrganizeDao;

@Component
public class UploadFileService {
	
	@Autowired
	private AccountService accountService;
	@Autowired
    private SmOrganizeDao smOrganizeDao;

	public String process(File localFile){
		if(!localFile.exists()) {
			return "上传文件不存在！";
		}

		String fileName = localFile.getName();
		if (!fileName.substring(fileName.lastIndexOf(".")).equals(".xls")){
			return "上传的文件不是excel格式的，请检查！";
		}
		if (!fileName.startsWith("UserUpload")){
			return "上传的文件必须是从UserUpload开头的文件！";
		}
		
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(localFile.getPath()));
			HSSFSheet sheet = workbook.getSheet("user");
			int iCount = 1;
			HSSFRow row = null;
			row = sheet.getRow(iCount++);
			while( row!=null&&row.getCell(0)!=null){
				String userCode = row.getCell(0).getStringCellValue();
				if ( userCode == null ){
					row = sheet.getRow(iCount++);
					continue;
				}
				boolean isNew = false;
				SmUser user = accountService.findUserByLoginName(userCode);
				if ( user == null ){
					isNew = true;
					user = new SmUser();
				}
				user.setUserCode(row.getCell(0).getStringCellValue());
				user.setUserName(row.getCell(1).getStringCellValue());
				String orgName = row.getCell(2).getStringCellValue();
				if ( orgName != null ){
					user.setSmOrganization(smOrganizeDao.findByGroupName(orgName));
				}
				
				String gender = (row.getCell(3)==null)?null:row.getCell(3).getStringCellValue();
				if (gender != null && gender.equals("男")){
					user.setGender("man");
				} else if(gender != null && gender.equals("女")){
					user.setGender("woman");
				}
				Double mobile = (row.getCell(4)==null)?null:row.getCell(4).getNumericCellValue();
				user.setMobile((new BigDecimal(mobile)).toString());
				user.setPhone((row.getCell(5)==null)?null:row.getCell(5).getStringCellValue());
				user.setEmail((row.getCell(6)==null)?null:row.getCell(6).getStringCellValue());
				user.setAddress((row.getCell(7)==null)?null:row.getCell(7).getStringCellValue());
				if ( isNew ){
					user.setPlainPassword("secisland");
					accountService.registerUser(user);
				} else {
					accountService.updateUser(user);
				}
				row = sheet.getRow(iCount++);
			} 
			return "成功保存"+(iCount-2)+"个用户";
		} catch (FileNotFoundException e) {
			return e.getMessage();
		} catch (IOException e) {
			return e.getMessage();
		}
	}

	public String processAuth(File localFile) {
		if (!localFile.exists()) {
			return "上传文件不存在！";
		}
		String fileName = localFile.getName();
		if (!fileName.substring(fileName.lastIndexOf(".")).equals(".xls")) {
			return "上传的文件不是excel格式的，请检查！";
		}
		if (!fileName.startsWith("UserAuthUpload")) {
			return "上传的文件必须是从UserAuthUpload开头的文件！";
		}
		return null;
	}
}
