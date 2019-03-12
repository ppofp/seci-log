/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.log.web.service.log;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springside.modules.persistence.SearchFilter;

import com.secisland.core.web.SearchCondition;
import com.secisland.core.web.SimpleSearchService;
import com.secisland.log.web.entity.SmLog;
import com.secisland.log.web.repository.SmLogDao;

@Component
public class LogService {

	@Autowired
	private SmLogDao smLogDao;
	
	public Page<SmLog> getSystemLogPage(Map<String, Object> searchParams, int pageNumber, int pageSize, String sortType) {
		SimpleSearchService<SmLog> searchService = new SimpleSearchService<SmLog>(SmLog.class,smLogDao);
		List<SearchFilter> filters = SearchFilter.parseList(searchParams);
		SearchCondition searchCondition = new SearchCondition(pageNumber,pageSize,sortType,filters);
		
		searchService.setDao(smLogDao);
		Page<SmLog> returnPage = searchService.getPageList(searchCondition);
		return returnPage;
	}

	public List<SmLog> getSystemLogList(Map<String, Object> searchParams) {
		SimpleSearchService<SmLog> searchService = new SimpleSearchService<SmLog>(SmLog.class,smLogDao);
		List<SearchFilter> filters = SearchFilter.parseList(searchParams);
		Sort sort = new Sort(Sort.Direction.DESC, "operateDate");
		List<SmLog> logList = searchService.getList(filters,sort);
		return logList;
	}
}
