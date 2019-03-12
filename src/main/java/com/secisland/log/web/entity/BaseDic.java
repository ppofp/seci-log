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

@Entity
@Table(name="base_dic")
public class BaseDic extends IdEntity {
	private String dicCode;
	private String dicName;
	private String remark;

    public BaseDic() {
    }


    @Column(name="dic_code")
	public String getDicCode() {
		return dicCode;
	}



	public void setDicCode(String dicCode) {
		this.dicCode = dicCode;
	}


	@Column(name="dic_name")
	public String getDicName() {
		return dicName;
	}



	public void setDicName(String dicName) {
		this.dicName = dicName;
	}



	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}