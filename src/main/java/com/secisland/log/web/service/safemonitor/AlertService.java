/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.log.web.service.safemonitor;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springside.modules.persistence.SearchFilter;

import com.secisland.core.util.BeanUtils;
import com.secisland.core.web.SearchCondition;
import com.secisland.core.web.SimpleSearchService;
import com.secisland.log.web.entity.Alert;
import com.secisland.log.web.repository.AlertDao;

@Component
public class AlertService {
	@Autowired
	private AlertDao alertDao;
	
	private SimpleSearchService<Alert> searchService = null;
	
	public Page<Alert> getAlertPage(Map<String, Object> searchParams, int pageNumber, int pageSize,
			String sortType) {
		if ( searchService == null ){
			searchService = new SimpleSearchService<Alert>(Alert.class,alertDao);
		}
		List<SearchFilter> filters = SearchFilter.parseList(searchParams);
		SearchCondition searchCondition = new SearchCondition(pageNumber,pageSize,sortType,filters);
		
		searchService.setDao(alertDao);
		Page<Alert> returnPage = searchService.getPageList(searchCondition);
		return returnPage;
	}

	public Alert getAlert(Long id) {
		return alertDao.findOne(id);
	}
	
	public List<Alert> getTopAlert(int top){
		if ( searchService == null ){
			searchService = new SimpleSearchService<Alert>(Alert.class,alertDao);
		}
		return searchService.getList(top);
	}

	public void create(Alert alert) {
		if ( alert == null ){
			return ;
		}
		alert.setAlertDate(new Date());
		alert.setAlertCode("manual create alert");
		alert.setRuleName("手工创建告警");
		alert.setProcessFlag("N");
		alert.setProcessType("new");
		alertDao.save(alert);
	}

	public void update(Alert alert) {
		Alert alertTemp = getAlertDetail(alert.getId());
		BeanUtils.copyPriperties(alert, alertTemp);
		alertDao.save(alertTemp);
	}

	private Alert getAlertDetail(Long id) {
		if ( id != null ){
			return alertDao.getOne(id);
		}
		return null;
	}
}
