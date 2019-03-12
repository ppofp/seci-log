/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.log.web.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Ticket extends IdEntity {
	private String activeFlag;
	private Date createDate;
	private String createMail;
	private String createPhone;
	private String createUser;
	private String level;
	private String message;
	private Date processDate;
	private String processRemark;
	private String processUser;
	private String reportFiles;
	private String ticketName;
	private String processType;
	private List<Alert> alerts;

	@Column(name="active_flag")
	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="create_date")
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	@Column(name="create_mail")
	public String getCreateMail() {
		return this.createMail;
	}

	public void setCreateMail(String createMail) {
		this.createMail = createMail;
	}


	@Column(name="create_phone")
	public String getCreatePhone() {
		return this.createPhone;
	}

	public void setCreatePhone(String createPhone) {
		this.createPhone = createPhone;
	}


	@Column(name="create_user")
	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}


	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}


	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="process_date")
	public Date getProcessDate() {
		return this.processDate;
	}

	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}


	@Column(name="process_remark")
	public String getProcessRemark() {
		return this.processRemark;
	}

	public void setProcessRemark(String processRemark) {
		this.processRemark = processRemark;
	}


	@Column(name="process_user")
	public String getProcessUser() {
		return this.processUser;
	}

	public void setProcessUser(String processUser) {
		this.processUser = processUser;
	}


	@Column(name="report_files")
	public String getReportFiles() {
		return this.reportFiles;
	}

	public void setReportFiles(String reportFiles) {
		this.reportFiles = reportFiles;
	}


	@Column(name="ticket_name")
	public String getTicketName() {
		return this.ticketName;
	}

	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}


	//bi-directional many-to-many association to EventLog
    @ManyToMany( cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(
		name="alert_ticket"
		, joinColumns={
			@JoinColumn(name="ticket_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="alert_id")
			}
		)
	public List<Alert> getAlerts() {
		return this.alerts;
	}

	public void setAlerts(List<Alert> alerts) {
		this.alerts = alerts;
	}
	
	@Column(name="process_type")
	public String getProcessType() {
		return processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}
}