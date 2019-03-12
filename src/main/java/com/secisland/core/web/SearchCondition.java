/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.core.web;

import java.util.List;

import org.springside.modules.persistence.SearchFilter;

public class SearchCondition {

	private int pageNumber;
	
	private int pageSize;
	
	private String sortFieldName;
	
	private List<SearchFilter> conditionList;

	public SearchCondition(){
	}
	
	public SearchCondition(int pageNumber, int pageSize, String sortFieldName,
			List<SearchFilter> conditionList) {
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.sortFieldName = sortFieldName;
		this.conditionList = conditionList;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSortFieldName() {
		return sortFieldName;
	}

	public void setSortFieldName(String sortFieldName) {
		this.sortFieldName = sortFieldName;
	}

	public List<SearchFilter> getConditionList() {
		return conditionList;
	}

	public void setConditionList(List<SearchFilter> conditionList) {
		this.conditionList = conditionList;
	}
}