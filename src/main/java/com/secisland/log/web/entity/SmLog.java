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
@Table(name="sm_log")
public class SmLog extends IdEntity {
	private String content;
	private String objectCode;
	private Long objectId;
	private String objectName;
	private String objectValue;
	private Long operateBy;
	private Date operateDate;
	private String operateIp;
	private String operateCode;
	private String operateName;
	private String operateType;
	private String result;
	
    public SmLog() {
    }

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	@Column(name="object_code")
	public String getObjectCode() {
		return this.objectCode;
	}

	public void setObjectCode(String objectCode) {
		this.objectCode = objectCode;
	}


	@Column(name="object_id")
	public Long getObjectId() {
		return this.objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}


	@Column(name="object_name")
	public String getObjectName() {
		return this.objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}


	@Column(name="object_value")
	public String getObjectValue() {
		return this.objectValue;
	}

	public void setObjectValue(String objectValue) {
		this.objectValue = objectValue;
	}


	@Column(name="operate_by")
	public Long getOperateBy() {
		return this.operateBy;
	}

	public void setOperateBy(Long operateBy) {
		this.operateBy = operateBy;
	}


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="operate_date")
	public Date getOperateDate() {
		return this.operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}


	@Column(name="operate_ip")
	public String getOperateIp() {
		return this.operateIp;
	}

	public void setOperateIp(String operateIp) {
		this.operateIp = operateIp;
	}


	@Column(name="operate_code")
	public String getOperateCode() {
		return this.operateCode;
	}

	public void setOperateCode(String operateCode) {
		this.operateCode = operateCode;
	}
	
	@Column(name="operate_name")
	public String getOperateName() {
		return this.operateName;
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}


	@Column(name="operate_type")
	public String getOperateType() {
		return this.operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}