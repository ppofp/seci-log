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

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="base_safe_asset")
public class BaseSafeAsset extends IdEntity {
	private String activeFlag;
	private Long createdBy;
	private Date createdDate;
	private String databaseVersion;
	private String ip2;
	private Long lastUpdBy;
	private Date lastUpdDate;
	private String loginuser;
	private String password;
	private Integer port;
	private String isvm;
	private String ip;
	private String template;
	private String position;
	private String remark;
	private String safeAssetCode;
	private String safeAssetName;
	private String safeAssetType;
	private String factory;
	private Long smProvinceId;
	private Long userid;
	private String userName;
	private String model;
	private String version;
	private String vmid;
	private String addedSgId;
	
	@Column(name="factory")
	public String getFactory() {
		return factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
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


	@Column(name="database_version")
	public String getDatabaseVersion() {
		return this.databaseVersion;
	}

	public void setDatabaseVersion(String databaseVersion) {
		this.databaseVersion = databaseVersion;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIp2() {
		return this.ip2;
	}

	public void setIp2(String ip2) {
		this.ip2 = ip2;
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


	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}


	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	@Column(name="safe_asset_code")
	public String getSafeAssetCode() {
		return this.safeAssetCode;
	}

	public void setSafeAssetCode(String safeAssetCode) {
		this.safeAssetCode = safeAssetCode;
	}


	@Column(name="safe_asset_name")
	public String getSafeAssetName() {
		return this.safeAssetName;
	}

	public void setSafeAssetName(String safeAssetName) {
		this.safeAssetName = safeAssetName;
	}

	@Column(name="safe_asset_type")
	public String getSafeAssetType() {
		return safeAssetType;
	}

	public void setSafeAssetType(String safeAssetType) {
		this.safeAssetType = safeAssetType;
	}

	@Column(name="sm_province_id")
	public Long getSmProvinceId() {
		return this.smProvinceId;
	}

	public void setSmProvinceId(Long smProvinceId) {
		this.smProvinceId = smProvinceId;
	}


	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}


	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}


	public String getVmid() {
		return this.vmid;
	}

	public void setVmid(String vmid) {
		this.vmid = vmid;
	}

	@Column(name="user_name")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	public String getTemplate() {
		return this.template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getLoginuser() {
		return this.loginuser;
	}

	public void setLoginuser(String loginuser) {
		this.loginuser = loginuser;
	}
	
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Integer getPort() {
		return this.port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}
	
	public String getIsvm() {
		return this.isvm;
	}

	public void setIsvm(String isvm) {
		this.isvm = isvm;
	}

	@Basic
	@Column(name = "added_sg_id")
	public String getAddedSgId() {
		return addedSgId;
	}

	public void setAddedSgId(String addedSgId) {
		this.addedSgId = addedSgId;
	}
	
	@Transient
	public String[] getLogTableName() {
		String[] tableName = { "base_safe_asset", "安全资产" };
		return tableName;
	}

	@Transient
	public List<String[]> getLogColumnNames() {
		List<String[]> tableColumns = new ArrayList<String[]>();
		String[] column1 = { "safeAssetCode", "资产code" };
		String[] column2 = { "safeAssetName", "资产名称" };
		tableColumns.add(column1);
		tableColumns.add(column2);
		return tableColumns;
	}
}