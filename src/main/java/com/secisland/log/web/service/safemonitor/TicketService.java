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
import com.secisland.core.util.LoginUserInfo;
import com.secisland.core.web.SearchCondition;
import com.secisland.core.web.SimpleSearchService;
import com.secisland.log.web.entity.Alert;
import com.secisland.log.web.entity.Ticket;
import com.secisland.log.web.repository.TicketDao;

@Component
public class TicketService {
	@Autowired
	private TicketDao ticketDao;
	
	private SimpleSearchService<Ticket> searchService = null;
	
	public Page<Ticket> getTicketPage(Map<String, Object> searchParams, int pageNumber, int pageSize,
			String sortType) {
		if ( searchService == null ){
			searchService = new SimpleSearchService<Ticket>(Ticket.class,ticketDao);
		}
		List<SearchFilter> filters = SearchFilter.parseList(searchParams);
		SearchCondition searchCondition = new SearchCondition(pageNumber,pageSize,sortType,filters);
		
		searchService.setDao(ticketDao);
		Page<Ticket> returnPage = searchService.getPageList(searchCondition);
		return returnPage;
	}

	public Ticket getTicket(Long id) {
		return ticketDao.findOne(id);
	}
	
	public List<Ticket> getTopTicket(int top){
		if ( searchService == null ){
			searchService = new SimpleSearchService<Ticket>(Ticket.class,ticketDao);
		}
		return searchService.getList(top);
	}

	public void create(List<Alert> alertList,String memo) {
		if ( alertList == null ){
			return ;
		}
		Ticket ticket = new Ticket();
		ticket.setCreateDate(new Date());
		ticket.setCreateUser(LoginUserInfo.getLoginUserCode());
		ticket.setTicketName(memo);
		ticket.setMessage(memo);
		ticket.setActiveFlag("Y");
		ticket.setAlerts(alertList);
		ticketDao.save(ticket);
	}

	public void update(Ticket ticket) {
		Ticket temp = getTicketDetail(ticket.getId());
		BeanUtils.copyPriperties(ticket, temp);
		ticketDao.save(temp);
	}

	private Ticket getTicketDetail(Long id) {
		if ( id != null ){
			return ticketDao.getOne(id);
		}
		return null;
	}
}
