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

import com.secisland.log.web.entity.SmMenu;

public interface SmMenuDao extends GenericRepository<SmMenu, Long>, JpaSpecificationExecutor<SmMenu> {
	
	@Query(value ="select * from sm_menu where id in (select sm_menu_id from sm_role_menu where sm_role_id in("+
			"select sm_role_id from sm_user_role where sm_user_id=?1)) order by taxis_no",nativeQuery = true)
	List<SmMenu> findMenuByUserId(Long mainUserId);
}
