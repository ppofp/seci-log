/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.log.web.repository;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.RepositoryMetadata;

public class DefaultRepositoryFactory extends JpaRepositoryFactory {

	private final EntityManager entityManager;
    
	public DefaultRepositoryFactory(EntityManager entityManager) {
		super(entityManager);

		this.entityManager = entityManager;
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected <T, ID extends Serializable> JpaRepository<?, ?> getTargetRepository(RepositoryMetadata metadata, EntityManager entityManager) {
		JpaEntityInformation<?, Serializable> entityInformation = getEntityInformation(metadata.getDomainType());
		return new GenericRepositoryImpl(entityInformation, entityManager);
	}
  
    @Override
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
 
    	return GenericRepositoryImpl.class;
    }

	public EntityManager getEntityManager() {
		return entityManager;
	}
}