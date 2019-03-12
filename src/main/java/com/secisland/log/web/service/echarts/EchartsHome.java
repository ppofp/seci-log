/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.log.web.service.echarts;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import com.secisland.core.JdbcOperator;
import com.secisland.log.web.dto.StatCountByDate;
import com.secisland.log.web.dto.StatCountByType;
import com.secisland.log.web.entity.Alert;
import com.secisland.log.web.entity.EventLog;
import com.secisland.log.web.service.safemonitor.AlertService;
import com.secisland.log.web.service.safemonitor.EventlogService;

@Component
public class EchartsHome {
	
	@Autowired
	private AlertService alertService;
	@Autowired
	private EventlogService logService;
	

	public String getEventStatByDate(String startTime, String endTime){
		String sql = "select sum(event_count) statcount,event_date statdate "+
				"from event_log where TO_DAYS(event_date) >= to_days('" + startTime + "') and TO_DAYS(event_date) <= to_days('" + endTime + "') "+
				"group by  DATE_FORMAT( event_date, \"%Y-%m-%d\" )";
		List<StatCountByDate> list = getStatCountByDate(startTime,endTime,sql);
		StringBuffer result = new StringBuffer();
		StringBuffer title = new StringBuffer();
		StringBuffer data = new StringBuffer();
		
		data.append("[");
		title.append("[");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
		if(list != null && list.size() > 0){
			for (int i = 0; i < list.size(); i++) {
				if(i != 0){
					data.append(",");
					title.append(",");
				}
				data.append(list.get(i).getStatcount());
				title.append("\"" + formatter.format(list.get(i).getStatdate()) + "\"");
			}
		}
		data.append("]");
		title.append("]");
	    
	    result.append("{\"title\" : {\"text\": \"日志数量统计\"},");
		result.append("\"tooltip\":{\"trigger\":\"axis\"},\"xAxis\": [{\"type\": \"category\",\"data\":" + title + "}],");
		result.append("\"yAxis\": [{\"type\": \"value\"}],");
		result.append("\"series\": [{\"type\":\"bar\",\"data\":" + data + "}]}");
		
		return result.toString();
	}
	
	public String getAlertStatByRulename(String startTime, String endTime){
		String sql = "select count(*) statcount,rule_name stattype "+
				"from alert where TO_DAYS(alert_date) >= to_days('" + startTime + "') and TO_DAYS(alert_date) <= to_days('" + endTime + "') "+
				"group by rule_name";
		List<StatCountByType> list = getStatCountByType(startTime,endTime,sql);
		StringBuffer result = new StringBuffer();
		StringBuffer title = new StringBuffer();
		StringBuffer data = new StringBuffer();
		
		data.append("[");
		title.append("[");
		if(list != null && list.size() > 0){
			for (int i = 0; i < list.size(); i++) {
				if(i != 0){
					data.append(",");
					title.append(",");
				}
				data.append(list.get(i).getStatcount());
				title.append("\"" + list.get(i).getStattype() + "\"");
			}
		}
		data.append("]");
		title.append("]");
		
		result.append("{\"title\" : {\"text\": \"告警分布\"},");
		result.append("\"tooltip\":{\"trigger\":\"axis\"},\"xAxis\": [{\"type\": \"category\",\"data\":" + title + "}],");
		result.append("\"yAxis\": [{\"type\": \"value\"}],");
		result.append("\"series\": [{\"type\":\"bar\",\"data\":" + data + "}]}");
		
		return result.toString();
	}
	
	private List<StatCountByDate> getStatCountByDate(String startTime,String endTime,String sql) {
		List<StatCountByDate> result = JdbcOperator.getInstance().query(sql,
				new BeanPropertyRowMapper<StatCountByDate>(StatCountByDate.class));
		
		return result;
	}
	
	private List<StatCountByType> getStatCountByType(String startTime,String endTime,String sql) {
		List<StatCountByType> result = JdbcOperator.getInstance().query(sql,
				new BeanPropertyRowMapper<StatCountByType>(StatCountByType.class));
		
		return result;
	}
	
	public String getTopEvent(int top) {
		List<EventLog> eventLogList = logService.getTopEventLog(top);
		StringBuffer result = new StringBuffer();
		result.append("[");
		for(int i = 0; i < eventLogList.size(); i++){
			EventLog eventLog = eventLogList.get(i);
			if(i != 0){
				result.append(",");
			}
			result.append("{\"eventType\":\"" + eventLog.getEventType() + "\",");
			result.append("\"collectDate\":\"" + (new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(eventLog.getCollectDate()) + "\",");
			result.append("\"eventDesc\":\"" + StringEscapeUtils.escapeJson(StringEscapeUtils.escapeHtml4(eventLog.getEventDesc())) +"\"}");
		}
		result.append("]");
		
		return result.toString();
	}
	
	public String getTopAlert(int top) {
		List<Alert> topAlertList = alertService.getTopAlert(top);
		StringBuffer result = new StringBuffer();
		result.append("[");
		for(int i = 0; i < topAlertList.size(); i++){
			Alert alert = topAlertList.get(i);
			if(i != 0){
				result.append(",");
			}
			result.append("{\"ruleName\":\"" + alert.getRuleName() + "\",");
			result.append("\"alertDate\":\"" + (new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(alert.getAlertDate()) + "\",");
			result.append("\"displayTitle\":\"" + alert.getDisplayTitle() +"\"}");
		}
		result.append("]");
		
		return result.toString();
	}
}
