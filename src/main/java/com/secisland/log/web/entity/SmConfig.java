/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.log.web.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="sm_config")
public class SmConfig extends IdEntity {
	private String activeFlag;
	private String configCode;
	private String configValue;
	private Long createdBy;
	private Date createdDate;
	private Long lastUpdBy;
	private Date lastUpdDate;
	private String remark;

    public SmConfig() {
    }

	@Column(name="active_flag")
	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}


	@Column(name="config_code")
	public String getConfigCode() {
		return this.configCode;
	}

	public void setConfigCode(String configCode) {
		this.configCode = configCode;
	}


	@Column(name="config_value")
	public String getConfigValue() {
		return this.configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}


	@Column(name="created_by")
	public Long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="created_date")
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	@Column(name="last_upd_by")
	public Long getLastUpdBy() {
		return this.lastUpdBy;
	}

	public void setLastUpdBy(Long lastUpdBy) {
		this.lastUpdBy = lastUpdBy;
	}


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="last_upd_date")
	public Date getLastUpdDate() {
		return this.lastUpdDate;
	}

	public void setLastUpdDate(Date lastUpdDate) {
		this.lastUpdDate = lastUpdDate;
	}


	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}