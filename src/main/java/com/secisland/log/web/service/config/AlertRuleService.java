/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.log.web.service.config;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springside.modules.persistence.SearchFilter;

import com.secisland.core.util.BeanUtils;
import com.secisland.core.web.SearchCondition;
import com.secisland.core.web.SimpleSearchService;
import com.secisland.log.web.entity.AlertRule;
import com.secisland.log.web.repository.AlertRuleDao;

@Component
public class AlertRuleService {
	@Autowired
	private AlertRuleDao alertRuleDao;
	
	private SimpleSearchService<AlertRule> searchService = null;
	
	public Page<AlertRule> getAlertPage(Map<String, Object> searchParams, int pageNumber, int pageSize,
			String sortType) {
		if ( searchService == null ){
			searchService = new SimpleSearchService<AlertRule>(AlertRule.class,alertRuleDao);
		}
		List<SearchFilter> filters = SearchFilter.parseList(searchParams);
		SearchCondition searchCondition = new SearchCondition(pageNumber,pageSize,sortType,filters);
		
		searchService.setDao(alertRuleDao);
		Page<AlertRule> returnPage = searchService.getPageList(searchCondition);
		return returnPage;
	}

	public AlertRule getAlertRule(Long id) {
		return alertRuleDao.findOne(id);
	}

	public void create(AlertRule alertRule) {
		if ( alertRule == null ){
			return ;
		}

		alertRuleDao.save(alertRule);
	}

	public void update(AlertRule alertRule) {
		AlertRule temp = getAlertRuleDetail(alertRule.getId());
		BeanUtils.copyPriperties(alertRule, temp);
		alertRuleDao.save(temp);
	}

	private AlertRule getAlertRuleDetail(Long id) {
		if ( id != null ){
			return alertRuleDao.getOne(id);
		}
		return null;
	}
}
