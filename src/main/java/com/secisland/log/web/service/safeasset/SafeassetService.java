/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.log.web.service.safeasset;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springside.modules.persistence.SearchFilter;

import com.secisland.core.util.BeanUtils;
import com.secisland.core.web.SearchCondition;
import com.secisland.core.web.SimpleSearchService;
import com.secisland.log.web.entity.BaseSafeAsset;
import com.secisland.log.web.repository.BaseSafeAssetDao;

@Component
public class SafeassetService {

	@Autowired
	private BaseSafeAssetDao assetDao;
	
	public Page<BaseSafeAsset> getAssetPage(Map<String, Object> searchParams, int pageNumber, int pageSize,
			String sortType) {
		SimpleSearchService<BaseSafeAsset> searchService = new SimpleSearchService<BaseSafeAsset>(BaseSafeAsset.class,assetDao);
		List<SearchFilter> filters = SearchFilter.parseList(searchParams);
		SearchCondition searchCondition = new SearchCondition(pageNumber,pageSize,sortType,filters);
		
		searchService.setDao(assetDao);
		Page<BaseSafeAsset> returnPage = searchService.getPageList(searchCondition);
		return returnPage;
	}

	public BaseSafeAsset getAssetDetail(Long id) {
		if ( id == null ){
			return null;
		}
		return assetDao.findOne(id);
	}

	public void addAsset(BaseSafeAsset safeAsset) {
		safeAsset.setCreatedBy(1L);
		safeAsset.setCreatedDate(new Date());
		safeAsset.setActiveFlag("Y");
		assetDao.save(safeAsset);
	}

	public void update(BaseSafeAsset safeAsset) {
		BaseSafeAsset asset = getAssetDetail(safeAsset.getId());
		BeanUtils.copyPriperties(safeAsset, asset);
		asset.setLastUpdBy(1L);
		asset.setLastUpdDate(new Date());
		asset.setActiveFlag("Y");
		assetDao.save(asset);
	}

	public void delete(Long id) {
		assetDao.delete(id);
	}
}
