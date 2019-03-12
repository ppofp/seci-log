/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.log.web.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="event_log")
public class EventLog extends IdEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long baseCustomId;
	private Long baseCustomUserId;
	private Long quartzCollectLogId;
	private Date collectDate;
	private Date collectEndDate;
	private Date endEventDate;
	private Date eventDate;
	private int eventCount;
	private int level;
	private int sourcePort;
	private int targetPort;
	private String collectHostname;
	private String collectIp;
	private String collectName;
	private String collectType;
	private String content;
	private String detailTableId;
	private String eventDesc;
	private String eventDetailType;
	private String eventName;
	private String eventType;
	private String hostIp;
	private String hostName;
	private String httpMethod;
	private String httpSessionId;
	private String httpUrl;
	private String protocol;
	private String serviceType;
	private String softwareName;
	private String sourceHostname;
	private String sourceIp;
	private String sourceProcess;
	private String sourceUser;
	private String targetHostname;
	private String targetIp;
	private String targetProcess;
	private String targetUser;
	private String userName;
	private String result;
	private List<Alert> alerts;

    public EventLog() {
    }

	@Column(name="base_custom_id")
	public Long getBaseCustomId() {
		return this.baseCustomId;
	}

	public void setBaseCustomId(Long baseCustomId) {
		this.baseCustomId = baseCustomId;
	}


	@Column(name="base_custom_user_id")
	public Long getBaseCustomUserId() {
		return this.baseCustomUserId;
	}

	public void setBaseCustomUserId(Long baseCustomUserId) {
		this.baseCustomUserId = baseCustomUserId;
	}


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="collect_date")
	public Date getCollectDate() {
		return this.collectDate;
	}

	public void setCollectDate(Date collectDate) {
		this.collectDate = collectDate;
	}


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="collect_end_date")
	public Date getCollectEndDate() {
		return this.collectEndDate;
	}

	public void setCollectEndDate(Date collectEndDate) {
		this.collectEndDate = collectEndDate;
	}


	@Column(name="collect_hostname")
	public String getCollectHostname() {
		return this.collectHostname;
	}

	public void setCollectHostname(String collectHostname) {
		this.collectHostname = collectHostname;
	}


	@Column(name="collect_ip")
	public String getCollectIp() {
		return this.collectIp;
	}

	public void setCollectIp(String collectIp) {
		this.collectIp = collectIp;
	}


	@Column(name="collect_name")
	public String getCollectName() {
		return this.collectName;
	}

	public void setCollectName(String collectName) {
		this.collectName = collectName;
	}


	@Column(name="collect_type")
	public String getCollectType() {
		return this.collectType;
	}

	public void setCollectType(String collectType) {
		this.collectType = collectType;
	}


	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	@Column(name="detail_table_id")
	public String getDetailTableId() {
		return this.detailTableId;
	}

	public void setDetailTableId(String detailTableId) {
		this.detailTableId = detailTableId;
	}


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="end_event_date")
	public Date getEndEventDate() {
		return this.endEventDate;
	}

	public void setEndEventDate(Date endEventDate) {
		this.endEventDate = endEventDate;
	}


	@Column(name="event_count")
	public int getEventCount() {
		return this.eventCount;
	}

	public void setEventCount(int eventCount) {
		this.eventCount = eventCount;
	}


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="event_date")
	public Date getEventDate() {
		return this.eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}


	@Column(name="event_desc")
	public String getEventDesc() {
		return this.eventDesc;
	}

	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}


	@Column(name="event_detail_type")
	public String getEventDetailType() {
		return this.eventDetailType;
	}

	public void setEventDetailType(String eventDetailType) {
		this.eventDetailType = eventDetailType;
	}


	@Column(name="event_name")
	public String getEventName() {
		return this.eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}


	@Column(name="event_type")
	public String getEventType() {
		return this.eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}


	@Column(name="host_ip")
	public String getHostIp() {
		return this.hostIp;
	}

	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}


	@Column(name="host_name")
	public String getHostName() {
		return this.hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}


	@Column(name="http_method")
	public String getHttpMethod() {
		return this.httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}


	@Column(name="http_session_id")
	public String getHttpSessionId() {
		return this.httpSessionId;
	}

	public void setHttpSessionId(String httpSessionId) {
		this.httpSessionId = httpSessionId;
	}


	@Column(name="http_url")
	public String getHttpUrl() {
		return this.httpUrl;
	}

	public void setHttpUrl(String httpUrl) {
		this.httpUrl = httpUrl;
	}


	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}


	public String getProtocol() {
		return this.protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}


	@Column(name="quartz_collect_log_id")
	public Long getQuartzCollectLogId() {
		return this.quartzCollectLogId;
	}

	public void setQuartzCollectLogId(Long quartzCollectLogId) {
		this.quartzCollectLogId = quartzCollectLogId;
	}


	@Column(name="service_type")
	public String getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}


	@Column(name="software_name")
	public String getSoftwareName() {
		return this.softwareName;
	}

	public void setSoftwareName(String softwareName) {
		this.softwareName = softwareName;
	}


	@Column(name="source_hostname")
	public String getSourceHostname() {
		return this.sourceHostname;
	}

	public void setSourceHostname(String sourceHostname) {
		this.sourceHostname = sourceHostname;
	}


	@Column(name="source_ip")
	public String getSourceIp() {
		return this.sourceIp;
	}

	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}


	@Column(name="source_port")
	public int getSourcePort() {
		return this.sourcePort;
	}

	public void setSourcePort(int sourcePort) {
		this.sourcePort = sourcePort;
	}


	@Column(name="source_process")
	public String getSourceProcess() {
		return this.sourceProcess;
	}

	public void setSourceProcess(String sourceProcess) {
		this.sourceProcess = sourceProcess;
	}


	@Column(name="source_user")
	public String getSourceUser() {
		return this.sourceUser;
	}

	public void setSourceUser(String sourceUser) {
		this.sourceUser = sourceUser;
	}


	@Column(name="target_hostname")
	public String getTargetHostname() {
		return this.targetHostname;
	}

	public void setTargetHostname(String targetHostname) {
		this.targetHostname = targetHostname;
	}


	@Column(name="target_ip")
	public String getTargetIp() {
		return this.targetIp;
	}

	public void setTargetIp(String targetIp) {
		this.targetIp = targetIp;
	}


	@Column(name="target_port")
	public int getTargetPort() {
		return this.targetPort;
	}

	public void setTargetPort(int targetPort) {
		this.targetPort = targetPort;
	}


	@Column(name="target_process")
	public String getTargetProcess() {
		return this.targetProcess;
	}

	public void setTargetProcess(String targetProcess) {
		this.targetProcess = targetProcess;
	}


	@Column(name="target_user")
	public String getTargetUser() {
		return this.targetUser;
	}

	public void setTargetUser(String targetUser) {
		this.targetUser = targetUser;
	}


	@Column(name="user_name")
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	//bi-directional many-to-many association to Alert
	@ManyToMany(mappedBy="eventLogs")
	public List<Alert> getAlerts() {
		return this.alerts;
	}

	public void setAlerts(List<Alert> alerts) {
		this.alerts = alerts;
	}
}