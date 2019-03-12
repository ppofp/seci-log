/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.log.web.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.Reflections;

import com.secisland.core.util.BeanUtils;
import com.secisland.core.util.LoginUserInfo;
import com.secisland.log.web.entity.IdEntity;
import com.secisland.log.web.entity.SmLog;

public class GenericRepositoryImpl<T, ID extends Serializable> extends
		SimpleJpaRepository<T, ID> implements GenericRepository<T, ID> {
	
	private EntityManager entityManager;
	public GenericRepositoryImpl(final JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
	}

	private void insertOrDeleteLog( T entity , String logTypeCode, String logTypeName ){
		SmLog smLog = new SmLog();
		if ( entity instanceof IdEntity){
			smLog.setOperateBy(LoginUserInfo.getLoginUserId());
			smLog.setOperateCode(LoginUserInfo.getLoginUserCode());
			smLog.setOperateName(LoginUserInfo.getLoginUserName());
			smLog.setOperateIp(LoginUserInfo.getIpAddress());
			smLog.setOperateDate(new Date());
			smLog.setObjectId(((IdEntity) entity).getId());
			smLog.setResult("success");
			smLog.setOperateType( logTypeCode );
		
			String[] tableName = ((IdEntity)entity).getLogTableName();
			if ( tableName != null && tableName.length == 2 ){
				smLog.setObjectCode(tableName[0]);
				smLog.setObjectName(tableName[1]);
			}
			List<String[]> tableColumns = ((IdEntity)entity).getLogColumnNames();
			if ( tableName != null && tableName.length > 0 ){
				int iCount = 1;
				String strContent = logTypeName + "：\r\n";
				try {
					for ( String[] column : tableColumns ){
						if ( iCount == 1 ){
							smLog.setObjectValue(Reflections.
									getFieldValue(entity, column[0]).toString());
							strContent = strContent + column[1]+":"+
									Reflections.getFieldValue(entity, column[0]).toString();
						} else {
							strContent = strContent + "\r\n" + column[1]+":"+
									Reflections.getFieldValue(entity, column[0]).toString();
						}
						iCount++;
					}
				} catch ( NullPointerException e ) {
					strContent = strContent+ e.getLocalizedMessage() + "column is null!!";
				} catch(Exception e){
					strContent = strContent+". other error!!";
				}
				smLog.setContent(strContent);
			} else {
				smLog.setObjectValue("未知");
			}
			entityManager.persist(smLog);
		}
	}

	private void afterInsert(T entity){
		insertOrDeleteLog( entity ,"insert", "新增记录" );
	}
	@SuppressWarnings("unchecked")
	private T beforeUpdate(T entity){
		SmLog smLog = new SmLog();
		if ( entity instanceof IdEntity){
			Long id = ((IdEntity)entity).getId();
			T oldEntity = findOne((ID)id );
			if ( oldEntity == null ){
				return null;
			}
			smLog.setOperateBy(LoginUserInfo.getLoginUserId());
			smLog.setOperateCode(LoginUserInfo.getLoginUserCode());
			smLog.setOperateName(LoginUserInfo.getLoginUserName());
			smLog.setOperateIp(LoginUserInfo.getIpAddress());
			smLog.setOperateDate(new Date());
			smLog.setObjectId(((IdEntity) entity).getId());
			smLog.setResult("success");
			smLog.setOperateType( "update" );
		
			String[] tableName = ((IdEntity)entity).getLogTableName();
			if ( tableName != null && tableName.length == 2 ){
				smLog.setObjectCode(tableName[0]);
				smLog.setObjectName(tableName[1]);
			}
			List<String[]> tableColumns = ((IdEntity)entity).getLogColumnNames();
			if ( tableName != null && tableName.length > 0 ){
				int iCount = 1;
				String strContent = "更新记录" + "：\r\n";
				String oldValue = "";
				try {
					for ( String[] column : tableColumns ){
						if ( iCount == 1 ){
							smLog.setObjectValue(Reflections.
									getFieldValue(entity, column[0]).toString());
							oldValue =  column[1]+":"+ Reflections.getFieldValue(oldEntity, column[0]).toString();
							strContent = strContent + oldValue + ">>" +
									Reflections.getFieldValue(entity, column[0]).toString();
						} else {
							oldValue =  column[1]+":"+ Reflections.getFieldValue(oldEntity, column[0]).toString();
							strContent = strContent + "\r\n" + oldValue + ">>" +
									Reflections.getFieldValue(entity, column[0]).toString();
						}
						iCount++;
					}
				} catch ( NullPointerException e ) {
					strContent = strContent+ e.getLocalizedMessage() + "column is null!!";
				} catch(Exception e){
					strContent = strContent+". other error!!";
				}
				smLog.setContent(strContent);
			} else {
				smLog.setObjectValue("未知");
			}
			entityManager.persist(smLog);
			BeanUtils.copyPriperties(entity, oldEntity);
			return oldEntity;
		}	
		return null;
	}

	private void beforeDelete( T entity ){
		insertOrDeleteLog( entity ,"delete", "删除记录" );
	}

	@Transactional
	public T insert(T entity){
		T retEntity = super.save(entity);
		afterInsert(entity);
		return retEntity;
	}

	@Transactional
	public T update(T entity) {
		T entity1 = beforeUpdate( entity );
		if (entity1 == null){
			return null;
		}
		return super.save(entity1);
	}
	
	@Transactional
	public void delete(ID id) {
		delete(findOne(id));
	}
	
	@Transactional
	public void delete(T entity) {
		beforeDelete( entity );
		super.delete( entity );
	}
	
	@Transactional
	public void delete(Iterable<? extends T> entities) {
		if (entities == null) {
			return;
		}

		for (T entity : entities) {
			delete(entity);
		}
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
}