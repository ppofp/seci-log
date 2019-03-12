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
public class Alert extends IdEntity {
	private String alertCode;
	private Date alertDate;
	private Date alertLastDate;
	private Long alertRuleId;
	private String alertSource;
	private String alertType;
	private Long baseCustomUserId;
	private Long baseCustomId;
	private Long baseCustomObjectId;
	private String customName;
	private int degree;
	private String displayDetail;
	private String displayTitle;
	private int eventCount;
	private String memo;
	private String mergeValue;
	private String objectAddress;
	private String processFlag;
	private String processType;
	private String ruleName;
	private Long ticketId;
	private String ticketNo;
	private List<EventLog> eventLogs;
	private List<Ticket> tickets;

    private Integer orderStatus;
    private Date operaDate;
    private Date completionDate;

    @Column(name="alert_code")
	public String getAlertCode() {
		return this.alertCode;
	}

	public void setAlertCode(String alertCode) {
		this.alertCode = alertCode;
	}


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="alert_date")
	public Date getAlertDate() {
		return this.alertDate;
	}

	public void setAlertDate(Date alertDate) {
		this.alertDate = alertDate;
	}


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="alert_last_date")
	public Date getAlertLastDate() {
		return this.alertLastDate;
	}

	public void setAlertLastDate(Date alertLastDate) {
		this.alertLastDate = alertLastDate;
	}


	@Column(name="alert_rule_id")
	public Long getAlertRuleId() {
		return this.alertRuleId;
	}

	public void setAlertRuleId(Long alertRuleId) {
		this.alertRuleId = alertRuleId;
	}


	@Column(name="alert_source")
	public String getAlertSource() {
		return this.alertSource;
	}

	public void setAlertSource(String alertSource) {
		this.alertSource = alertSource;
	}


	@Column(name="alert_type")
	public String getAlertType() {
		return this.alertType;
	}

	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}


	@Column(name="base_custom_user_id")
	public Long getBaseCustomUserId() {
		return this.baseCustomUserId;
	}

	public void setBaseCustomUserId(Long baseCustomUserId) {
		this.baseCustomUserId = baseCustomUserId;
	}
	
	@Column(name="base_custom_id")
	public Long getBaseCustomId() {
		return this.baseCustomId;
	}

	public void setBaseCustomId(Long baseCustomId) {
		this.baseCustomId = baseCustomId;
	}


	@Column(name="base_custom_object_id")
	public Long getBaseCustomObjectId() {
		return this.baseCustomObjectId;
	}

	public void setBaseCustomObjectId(Long baseCustomObjectId) {
		this.baseCustomObjectId = baseCustomObjectId;
	}


	@Column(name="custom_name")
	public String getCustomName() {
		return this.customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}


	public int getDegree() {
		return this.degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}


	@Column(name="display_detail")
	public String getDisplayDetail() {
		return this.displayDetail;
	}

	public void setDisplayDetail(String displayDetail) {
		this.displayDetail = displayDetail;
	}


	@Column(name="display_title")
	public String getDisplayTitle() {
		return this.displayTitle;
	}

	public void setDisplayTitle(String displayTitle) {
		this.displayTitle = displayTitle;
	}


	@Column(name="event_count")
	public int getEventCount() {
		return this.eventCount;
	}

	public void setEventCount(int eventCount) {
		this.eventCount = eventCount;
	}


	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}


	@Column(name="merge_value")
	public String getMergeValue() {
		return this.mergeValue;
	}

	public void setMergeValue(String mergeValue) {
		this.mergeValue = mergeValue;
	}


	@Column(name="object_address")
	public String getObjectAddress() {
		return this.objectAddress;
	}

	public void setObjectAddress(String objectAddress) {
		this.objectAddress = objectAddress;
	}


	@Column(name="process_flag")
	public String getProcessFlag() {
		return this.processFlag;
	}

	public void setProcessFlag(String processFlag) {
		this.processFlag = processFlag;
	}


	@Column(name="process_type")
	public String getProcessType() {
		return this.processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}


	@Column(name="rule_name")
	public String getRuleName() {
		return this.ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}


	@Column(name="ticket_id")
	public Long getTicketId() {
		return this.ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}


	@Column(name="ticket_no")
	public String getTicketNo() {
		return this.ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	//bi-directional many-to-many association to EventLog
    @ManyToMany( cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(
		name="alert_event_log"
		, joinColumns={
			@JoinColumn(name="alert_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="event_id")
			}
		)
	public List<EventLog> getEventLogs() {
		return this.eventLogs;
	}

	public void setEventLogs(List<EventLog> eventLogs) {
		this.eventLogs = eventLogs;
	}
	
	@ManyToMany(mappedBy="alerts")
	public List<Ticket> getTickets() {
		return this.tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

    @Column(name = "order_status")
    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Column(name = "opera_date")
    public Date getOperaDate() {
        return operaDate;
    }

    public void setOperaDate(Date operaDate) {
        this.operaDate = operaDate;
    }

    @Column(name = "completion_date")
    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }
}