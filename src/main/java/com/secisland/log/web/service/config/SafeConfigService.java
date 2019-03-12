/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.log.web.service.config;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.secisland.core.util.BeanUtils;
import com.secisland.core.util.LoginUserInfo;
import com.secisland.log.web.entity.BaseDic;
import com.secisland.log.web.entity.BaseDicDetail;
import com.secisland.log.web.repository.BaseDicDao;
import com.secisland.log.web.repository.BaseDicDetailDao;

@Component
public class SafeConfigService {

	@Autowired
	private BaseDicDao dicDao;
	@Autowired
	private BaseDicDetailDao dicDetailDao;
	
	public List<BaseDic> getDicList(){
		return dicDao.findAll();
	}
	
	public List<BaseDicDetail> getDicDetailList(Long dicId){
		if ( dicId == null ){
			return null;
		}
		return dicDetailDao.findDetailByDicId(dicId);
	}

	public void updateDicDetail(BaseDicDetail detail) {
		BaseDicDetail detailTemp = getDetail(detail.getId());
		BeanUtils.copyPriperties(detail, detailTemp );
		detailTemp.setLastUpdBy(LoginUserInfo.getLoginUserId());
		detailTemp.setLastUpdDate(new Date());
		dicDetailDao.save(detailTemp);
	}

	public void createDicDetail(BaseDicDetail detail) {
		detail.setCreatedBy(LoginUserInfo.getLoginUserId());
		detail.setCreatedDate(new Date());
		dicDetailDao.save(detail);
	}

	public BaseDicDetail getDetail(Long detailid) {
		if( detailid == null ){
			return null;
		}
		
		return dicDetailDao.findOne(detailid);
	}

	public void deleteDetail(Long detailid) {
		dicDetailDao.delete(detailid);
	}
}
