/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.log.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.secisland.log.web.entity.BaseDicDetail;

public interface BaseDicDetailDao extends GenericRepository<BaseDicDetail, Long>, JpaSpecificationExecutor<BaseDicDetail>{
	
	@Query(value ="select * from base_dic_detail where base_dic_id=?1",nativeQuery = true)
	List<BaseDicDetail> findDetailByDicId(Long dicId);
}
