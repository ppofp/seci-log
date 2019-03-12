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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="sm_role")
public class SmRole extends IdEntity {
	private String activeFlag;
	private Long createdBy;
	private Date createdDate;
	private Long lastUpdBy;
	private Date lastUpdDate;
	private String remark;
	private String roleCode;
	private String roleName;
	private List<SmMenu> smMenus;
	private List<SmUser> smUsers;

    public SmRole() {
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


	@Column(name="role_code")
	public String getRoleCode() {
		return this.roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}


	@Column(name="role_name")
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

    @ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="sm_role_menu"
		, joinColumns={
			@JoinColumn(name="sm_role_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="sm_menu_id")
			}
		)
	public List<SmMenu> getSmMenus() {
		return this.smMenus;
	}

	public void setSmMenus(List<SmMenu> smMenus) {
		this.smMenus = smMenus;
	}
	

	//bi-directional many-to-many association to SmUser
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REMOVE}, mappedBy="smRoles",fetch=FetchType.EAGER)
	public List<SmUser> getSmUsers() {
		return this.smUsers;
	}

	public void setSmUsers(List<SmUser> smUsers) {
		this.smUsers = smUsers;
	}
	@Transient
	public String[] getLogTableName() {
		String[] tableName = { "sm_role", "角色管理" };
		return tableName;
	}

	@Transient
	public List<String[]> getLogColumnNames() {
		List<String[]> tableColumns = new ArrayList<String[]>();
		String[] column1 = { "roleName", "角色名称" };
		tableColumns.add(column1);
		return tableColumns;
	}
}