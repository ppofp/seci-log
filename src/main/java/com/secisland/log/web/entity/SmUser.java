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
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="sm_user")
public class SmUser extends IdEntity {
	private String activeFlag;
	private String address;
	private String adminFlag;
	private Long createdBy;
	private Date createdDate;
	private String email;
	private String gender;
	private Date lastLoginDate;
	private Long lastUpdBy;
	private Date lastUpdDate;
	private Date lockedDate;
	private Integer loginFailureCount;
	private String mobile;
	private String plainPassword;
	private String password;
	private String passwordRecoverKey;
	private String phone;
	private String remark;
	private String safeAnswer;
	private String safeQuestion;
	private String salt;
	private String state;
	private String userCode;
	private String userName;
	private SmOrganization smOrganization;
	private List<SmRole> smRoles;

    public SmUser() {
    }

	@Column(name="active_flag")
	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}


	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	@Column(name="admin_flag")
	public String getAdminFlag() {
		return this.adminFlag;
	}

	public void setAdminFlag(String adminFlag) {
		this.adminFlag = adminFlag;
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

	@Email(message = "邮箱格式不正确！")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="last_login_date")
	public Date getLastLoginDate() {
		return this.lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
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


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="locked_date")
	public Date getLockedDate() {
		return this.lockedDate;
	}

	public void setLockedDate(Date lockedDate) {
		this.lockedDate = lockedDate;
	}


	@Column(name="login_failure_count")
	public Integer getLoginFailureCount() {
		return this.loginFailureCount;
	}

	public void setLoginFailureCount(Integer loginFailureCount) {
		this.loginFailureCount = loginFailureCount;
	}


	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Transient
	@JsonIgnore
	public String getPlainPassword() {
		return plainPassword;
	}

	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}
	
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	@Column(name="password_recover_key")
	public String getPasswordRecoverKey() {
		return this.passwordRecoverKey;
	}

	public void setPasswordRecoverKey(String passwordRecoverKey) {
		this.passwordRecoverKey = passwordRecoverKey;
	}


	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	@Column(name="safe_answer")
	public String getSafeAnswer() {
		return this.safeAnswer;
	}

	public void setSafeAnswer(String safeAnswer) {
		this.safeAnswer = safeAnswer;
	}


	@Column(name="safe_question")
	public String getSafeQuestion() {
		return this.safeQuestion;
	}

	public void setSafeQuestion(String safeQuestion) {
		this.safeQuestion = safeQuestion;
	}


	public String getSalt() {
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}


	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@NotBlank(message = "登录名不能为空")
	@Column(name="user_code")
	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	@NotBlank(message = "用户名不能为空")
	@Column(name="user_name")
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	//bi-directional many-to-one association to SmOrganization
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sm_organization_id")
	public SmOrganization getSmOrganization() {
		return this.smOrganization;
	}

	public void setSmOrganization(SmOrganization smOrganization) {
		this.smOrganization = smOrganization;
	}
	

	//bi-directional many-to-many association to SmRole
    @ManyToMany
	@JoinTable(
		name="sm_user_role"
		, joinColumns={
			@JoinColumn(name="sm_user_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="sm_role_id")
			}
		)
	public List<SmRole> getSmRoles() {
		return this.smRoles;
	}

	public void setSmRoles(List<SmRole> smRoles) {
		this.smRoles = smRoles;
	}
	@Transient
	public String[] getLogTableName() {
		String[] tableName = { "sm_user", "用户管理" };
		return tableName;
	}
	@Transient
	public List<String[]> getLogColumnNames() {
		List<String[]> tableColumns = new ArrayList<String[]>();
		String[] column1 = { "userName", "用户名" };
		tableColumns.add(column1);
		return tableColumns;
	}
}