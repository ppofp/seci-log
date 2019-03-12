/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.log.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="alert_rule")
public  class AlertRule extends IdEntity  {

	@Column(name="created_by")
	private java.lang.Long createdBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private java.util.Date createdDate;
	
	@Column(name="last_upd_by")
	private java.lang.Long lastUpdBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_upd_date")
	private java.util.Date lastUpdDate;
	
	@Column(name="rule_code")
	private java.lang.String ruleCode;
	
	@Column(name="rule_name")
	private java.lang.String ruleName;
	
	@Column(name="rule_condition")
	private java.lang.String ruleCondition;
	
	@Column(name="rule_detail")
	private java.lang.String ruleDetail;
	
	@Column(name="display_detail")
	private java.lang.String displayDetail;
	
	@Column(name="is_inside")
	private String isInside;
	
	@Column(name="active_flag")
	private String activeFlag;
	
	@Column(name="remark")
	private java.lang.String remark;

	public java.lang.Long getCreatedBy () {
		return createdBy;
	}

	public void setCreatedBy (java.lang.Long createdBy) {
		this.createdBy = createdBy;
	}

	public java.lang.String getRuleCondition() {
		return ruleCondition;
	}

	public void setRuleCondition(java.lang.String ruleCondition) {
		this.ruleCondition = ruleCondition;
	}

	public java.util.Date getCreatedDate () {
		return createdDate;
	}

	public void setCreatedDate (java.util.Date createdDate) {
		this.createdDate = createdDate;
	}

	public java.lang.Long getLastUpdBy () {
		return lastUpdBy;
	}

	public void setLastUpdBy (java.lang.Long lastUpdBy) {
		this.lastUpdBy = lastUpdBy;
	}

	public java.util.Date getLastUpdDate () {
		return lastUpdDate;
	}

	public void setLastUpdDate (java.util.Date lastUpdDate) {
		this.lastUpdDate = lastUpdDate;
	}

	public java.lang.String getRuleCode() {
		return ruleCode;
	}

	public void setRuleCode(java.lang.String ruleCode) {
		this.ruleCode = ruleCode;
	}

	public java.lang.String getRuleName () {
		return ruleName;
	}

	public void setRuleName (java.lang.String ruleName) {
		this.ruleName = ruleName;
	}

	public java.lang.String getRuleDetail () {
		return ruleDetail;
	}

	public void setRuleDetail (java.lang.String ruleDetail) {
		this.ruleDetail = ruleDetail;
	}

	public java.lang.String getDisplayDetail () {
		return displayDetail;
	}

	public void setDisplayDetail (java.lang.String displayDetail) {
		this.displayDetail = displayDetail;
	}

	public String getIsInside() {
		return isInside;
	}

	public void setIsInside(String isInside) {
		this.isInside = isInside;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public java.lang.String getRemark () {
		return remark;
	}

	public void setRemark (java.lang.String remark) {
		this.remark = remark;
	}

	public String toString () {
		return super.toString();
	}


}