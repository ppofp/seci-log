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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="sm_organization")
@JsonIgnoreProperties(value={"smUsers"})
public class SmOrganization extends IdEntity {
	private String activeFlag;
	private Long createdBy;
	private Date createdDate;
	private int groupLevel;
	private String groupName;
	private String idPath;
	private Long lastUpdBy;
	private Date lastUpdDate;
	private String namePath;
	private Long parentOrganizationId;
	private String remark;
	private List<SmUser> smUsers;

    public SmOrganization() {
    }

	@Column(name="active_flag")
	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
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


	@Column(name="group_level")
	public int getGroupLevel() {
		return this.groupLevel;
	}

	public void setGroupLevel(int groupLevel) {
		this.groupLevel = groupLevel;
	}


	@Column(name="group_name")
	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}


	@Column(name="id_path")
	public String getIdPath() {
		return this.idPath;
	}

	public void setIdPath(String idPath) {
		this.idPath = idPath;
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


	@Column(name="name_path")
	public String getNamePath() {
		return this.namePath;
	}

	public void setNamePath(String namePath) {
		this.namePath = namePath;
	}


	@Column(name="parent_organization_id")
	public Long getParentOrganizationId() {
		return this.parentOrganizationId;
	}

	public void setParentOrganizationId(Long parentOrganizationId) {
		this.parentOrganizationId = parentOrganizationId;
	}


	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	//bi-directional many-to-one association to SmUser
	@OneToMany(mappedBy="smOrganization")
	public List<SmUser> getSmUsers() {
		return this.smUsers;
	}

	public void setSmUsers(List<SmUser> smUsers) {
		this.smUsers = smUsers;
	}
	
	@Transient
	public String[] getLogTableName() {
		String[] tableName = { "sm_organization", "组织机构" };
		return tableName;
	}

	@Transient
	public List<String[]> getLogColumnNames() {
		List<String[]> tableColumns = new ArrayList<String[]>();
		String[] column1 = { "groupName", "组织名" };
		tableColumns.add(column1);
		return tableColumns;
	}
}