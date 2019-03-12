/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.log.web.dto;

public class ChangePasswordParam {
	private Long id;
	private String oldPassword;
	private String newPassword;
	private String confirmPassWord;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmPassWord() {
		return confirmPassWord;
	}
	public void setConfirmPassWord(String confirmPassWord) {
		this.confirmPassWord = confirmPassWord;
	}
	public boolean checkpassword()
	{
		if(newPassword != null && confirmPassWord != null){
			return newPassword.endsWith(confirmPassWord);
		}
		return false;
	}
}
