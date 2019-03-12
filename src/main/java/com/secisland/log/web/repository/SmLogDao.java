/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.log.web.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.secisland.log.web.entity.SmLog;

public interface SmLogDao extends GenericRepository<SmLog, Long>, JpaSpecificationExecutor<SmLog> {
	

}
