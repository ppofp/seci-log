/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.log.web.service.organize;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import com.secisland.core.util.LoginUserInfo;
import com.secisland.core.web.SearchCondition;
import com.secisland.core.web.SimpleSearchService;
import com.secisland.log.web.entity.SmOrganization;
import com.secisland.log.web.repository.SmOrganizeDao;

@Component
@Transactional
public class SmOrganizeService {
    @Autowired
    private SmOrganizeDao smOrganizeDao;

    private SimpleSearchService<SmOrganization> searchService = new SimpleSearchService<SmOrganization>(SmOrganization.class,smOrganizeDao);

    public void addOrganize(SmOrganization organization){
        SmOrganization parentOrganize = new SmOrganization();
        if(organization.getParentOrganizationId() != null){
            parentOrganize = getSmOrganizeById(organization.getParentOrganizationId());
        }

        organization.setActiveFlag("Y");
        organization.setCreatedDate(new Date());
        organization.setLastUpdDate(new Date());
        organization.setCreatedBy(LoginUserInfo.getLoginUserId());
        organization.setLastUpdBy(LoginUserInfo.getLoginUserId());
        organization.setNamePath(parentOrganize.getNamePath() + "/" + organization.getGroupName());
        organization.setIdPath(new String(""));
        organization.setGroupLevel(0);

        smOrganizeDao.insert(organization);
    }

    public void update(SmOrganization organization){
        organization.setLastUpdBy(LoginUserInfo.getLoginUserId());
        organization.setLastUpdDate(new Date());

        smOrganizeDao.update(organization);

    }

    public SmOrganization getSmOrganizeById(Long id){
        return  smOrganizeDao.findOne(id);
    }

    public List<SmOrganization> getAllSmOrganize(){
        return  (List<SmOrganization>) smOrganizeDao.findAll();
    }

    public void deleteSmOrganize(Long id ){
        smOrganizeDao.delete(id);
    }

    public SmOrganization findOrganizeByName(String name){
        return smOrganizeDao.findByGroupName(name);
    }

    public Page<SmOrganization> getOrganizePage(Map<String, Object> searchParams, int pageNumber, int pageSize, String sortType){
        List<SearchFilter> filters = SearchFilter.parseList(searchParams);
        SearchCondition searchCondition = new SearchCondition(pageNumber,pageSize,sortType,filters);
        searchService.setDao(smOrganizeDao);

        Page<SmOrganization> returnPage = searchService.getPageList(searchCondition);
        return  returnPage;
    }
}
