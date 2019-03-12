/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.log.web.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="sm_menu")
public class SmMenu extends IdEntity {
	private String activeFlag;
	private Long createdBy;
	private Date createdDate;
	private Long lastUpdBy;
	private Date lastUpdDate;
	private String menuCode;
	private String menuLink;
	private String menuName;
	private String remark;
	private int taxisNo;
	private SmMenu smMenu;
	private List<SmMenu> smMenus;
	private List<SmRole> smRoles;

    public SmMenu() {
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


	@Column(name="menu_code")
	public String getMenuCode() {
		return this.menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}


	@Column(name="menu_link")
	public String getMenuLink() {
		return this.menuLink;
	}

	public void setMenuLink(String menuLink) {
		this.menuLink = menuLink;
	}


	@Column(name="menu_name")
	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}


	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	@Column(name="taxis_no")
	public int getTaxisNo() {
		return this.taxisNo;
	}

	public void setTaxisNo(int taxisNo) {
		this.taxisNo = taxisNo;
	}


	//bi-directional many-to-one association to SmMenu
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parent_menu_id")
	public SmMenu getSmMenu() {
		return this.smMenu;
	}

	public void setSmMenu(SmMenu smMenu) {
		this.smMenu = smMenu;
	}
	

	//bi-directional many-to-one association to SmMenu
	@OneToMany(mappedBy="smMenu")
	public List<SmMenu> getSmMenus() {
		return this.smMenus;
	}

	public void setSmMenus(List<SmMenu> smMenus) {
		this.smMenus = smMenus;
	}
	
	//bi-directional many-to-many association to SmRole
	@ManyToMany(mappedBy="smMenus",fetch=FetchType.EAGER)
	public List<SmRole> getSmRoles() {
		return this.smRoles;
	}

	public void setSmRoles(List<SmRole> smRoles) {
		this.smRoles = smRoles;
	}
	
}