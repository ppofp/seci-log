/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.log.web.service.safemonitor;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springside.modules.persistence.SearchFilter;

import com.secisland.core.web.SearchCondition;
import com.secisland.core.web.SimpleSearchService;
import com.secisland.log.web.entity.EventLog;
import com.secisland.log.web.repository.EventLogDao;

@Component
public class EventlogService {

	@Autowired
	private EventLogDao logDao;
	private SimpleSearchService<EventLog> searchService = null;
	
	public Page<EventLog> getEventLogPage(Map<String, Object> searchParams, int pageNumber, int pageSize,
			String sortType) {
		if ( searchService == null ){
			searchService = new SimpleSearchService<EventLog>(EventLog.class,logDao);
		}
		List<SearchFilter> filters = SearchFilter.parseList(searchParams);
		SearchCondition searchCondition = new SearchCondition(pageNumber,pageSize,sortType,filters);
		
		searchService.setDao(logDao);
		Page<EventLog> returnPage = searchService.getPageList(searchCondition);
		return returnPage;
	}
	
	public List<EventLog> getTopEventLog(int top){
		if ( searchService == null ){
			searchService = new SimpleSearchService<EventLog>(EventLog.class,logDao);
		}
		return searchService.getList(top);
	}
	
	public EventLog getEventLog( Long id ){
		if (id==null){
			return null;
		}
		return logDao.findOne(id);
	}
}
