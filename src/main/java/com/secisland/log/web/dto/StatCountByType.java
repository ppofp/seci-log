/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.log.web.dto;

public class StatCountByType {
	private Integer statcount;
	private String stattype;
	
	public Integer getStatcount() {
		return statcount;
	}
	public void setStatcount(Integer statcount) {
		this.statcount = statcount;
	}
	public String getStattype() {
		return stattype;
	}
	public void setStattype(String stattype) {
		this.stattype = stattype;
	}
}
