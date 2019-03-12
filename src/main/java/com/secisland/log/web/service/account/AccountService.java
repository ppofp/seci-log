/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.log.web.service.account;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;
import org.springside.modules.persistence.SearchFilter.Operator;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Encodes;

import com.secisland.core.util.BeanUtils;
import com.secisland.core.util.LoginUserInfo;
import com.secisland.core.web.SearchCondition;
import com.secisland.core.web.SimpleSearchService;
import com.secisland.log.web.entity.SmOrganization;
import com.secisland.log.web.entity.SmRole;
import com.secisland.log.web.entity.SmUser;
import com.secisland.log.web.repository.SmRoleDao;
import com.secisland.log.web.repository.SmUserDao;
import com.secisland.log.web.service.ServiceException;

@Component
@Transactional
public class AccountService{
	private static Logger logger = LoggerFactory.getLogger(AccountService.class);
	
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;

	@Autowired
	private SmUserDao smUserDao;

	@Autowired
	private SmRoleDao roleDao;
	
	private SimpleSearchService<SmUser> searchService = new SimpleSearchService<SmUser>(SmUser.class,smUserDao);

	public AccountService() {
	}

	public List<SmUser> getAllUser() {
		Sort sort = new Sort(new Order(Direction.ASC,"userCode"));
		return (List<SmUser>) smUserDao.findAll(sort);
	}

	public SmUser getUser(Long id) {
		return smUserDao.findOne(id);
	}

	public SmUser findUserByLoginName(String loginName) {
		return  smUserDao.findByUserCode(loginName);
	}

	public void registerUser(SmUser user) {
		if (StringUtils.isBlank(user.getPlainPassword())) {
			user.setPlainPassword("!abc123");
		}
		entryptPassword(user);
		if ( user.getSmOrganization()==null || user.getSmOrganization().getId() == null ){
			user.setSmOrganization(null);
		}
		user.setCreatedBy(LoginUserInfo.getLoginUserId());
		user.setCreatedDate(new Date());
		user.setState("active");
		user.setActiveFlag("Y");

		smUserDao.save(user);
	}

	public void updateUser(SmUser user) {
		SmUser userTemp = getUser(user.getId());
		BeanUtils.copyPriperties(user, userTemp );
		if (StringUtils.isNotBlank(user.getPlainPassword())) {
			entryptPassword(userTemp);
		}
		userTemp.setLastUpdBy(LoginUserInfo.getLoginUserId());
		userTemp.setLastUpdDate(new Date());
		smUserDao.save(userTemp);
	}

	public void deleteUser(Long id) {
		if (isSupervisor(id)) {
			logger.warn("操作员{}尝试删除超级管理员用户", LoginUserInfo.getLoginUserCode());
			throw new ServiceException("不能删除超级管理员用户");
		}
		smUserDao.delete(id);
	}

	private boolean isSupervisor(Long id) {
		return id == 1;
	}

	private void entryptPassword(SmUser user) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		user.setSalt(Encodes.encodeHex(salt));

		byte[] hashPassword = Digests.sha1(user.getPlainPassword().getBytes(), salt, HASH_INTERATIONS);
		user.setPassword(Encodes.encodeHex(hashPassword));
	}
	
	public String getEntryptPassword(String password, String salt) {
		byte[] tempSalt = Encodes.decodeHex(salt);
		byte[] hashPassword = Digests.sha1(password.getBytes(), tempSalt, HASH_INTERATIONS);
		
		return Encodes.encodeHex(hashPassword);
	}
	
	public Page<SmUser> getUserPage(Map<String, Object> searchParams, int pageNumber, int pageSize, String sortType) {
		List<SearchFilter> filters = SearchFilter.parseList(searchParams);
		SearchFilter searchFilter = new SearchFilter();
		searchFilter.setFieldName("activeFlag");
		searchFilter.setOperator(Operator.EQ);
		searchFilter.setValue("Y");
		filters.add(searchFilter);
		SearchCondition searchCondition = new SearchCondition(pageNumber,pageSize,sortType,filters);
		
		searchService.setDao(smUserDao);
		Page<SmUser> returnPage = searchService.getPageList(searchCondition);
		return returnPage;
	}

    public List<SmUser> getUsersByOrganize(SmOrganization organzie){
        if(organzie != null){
            return  smUserDao.findBySmOrganization(organzie);
        }else {
            return null;
        }
    }
    
    public void addUserRole(SmUser user, String roleids) {
		if(user != null){
			user = smUserDao.findOne(user.getId());
			if(roleids != null && roleids.length() > 0){
				List<Long> roleIdList = string2LongList(roleids);
				List<SmRole> roleList = roleDao.findAll(roleIdList);
				user.setSmRoles(roleList);
			}else{
				List<SmRole> roleList = new ArrayList<SmRole>();
				user.setSmRoles(roleList);
			}

			smUserDao.save(user);
		}
	}
    

    public List<Long> string2LongList(String ids) {
    	List<Long> idList = new ArrayList<Long>();
    	
    	if(ids != null && ids.length() > 0){
    		String[] idStrings = ids.split(",");
			for(int i = 0; i < idStrings.length; i++){
				if(idStrings[i] != null && idStrings[i].length() > 0){
					idList.add(Long.parseLong(idStrings[i]));
				}
			}
    	}
    	
    	return idList;
	}
}
