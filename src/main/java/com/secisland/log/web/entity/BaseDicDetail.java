/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.log.web.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="base_dic_detail")
public class BaseDicDetail extends IdEntity {
	private Long baseDicId;
	private String code;
	private Long createdBy;
	private Date createdDate;
	private String isInternal;
	private Long lastUpdBy;
	private Date lastUpdDate;
	private String name;
	private String remark;
	private String value;

    public BaseDicDetail() {
    }

	@Column(name="base_dic_id")
	public Long getBaseDicId() {
		return this.baseDicId;
	}

	public void setBaseDicId(Long baseDicId) {
		this.baseDicId = baseDicId;
	}


	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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


	@Column(name="is_internal")
	public String getIsInternal() {
		return this.isInternal;
	}

	public void setIsInternal(String isInternal) {
		this.isInternal = isInternal;
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


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	@Transient
	public String[] getLogTableName() {
		String[] tableName = { "base_dic_detail", "安全配置" };
		return tableName;
	}
	@Transient
	public List<String[]> getLogColumnNames() {
		List<String[]> tableColumns = new ArrayList<String[]>();
		String[] column1 = { "code", "配置值" };
		String[] column2 = { "name", "配置名" };
		tableColumns.add(column1);
		tableColumns.add(column2);
		return tableColumns;
	}
}