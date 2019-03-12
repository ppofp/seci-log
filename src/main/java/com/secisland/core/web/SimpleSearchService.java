/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.core.web;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.util.ReflectionUtils;
import org.springside.modules.persistence.SearchFilter;
import org.springside.modules.utils.Collections3;

import com.google.common.collect.Lists;

public class SimpleSearchService<T> {
	Class <T> entityClass = null;
	Specification<T> spec = null;
	Object dao;
	
	@SuppressWarnings("unchecked")
	public SimpleSearchService() {
		entityClass = (Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public SimpleSearchService(Class<T> entityClass,Object dao) {
		this.entityClass = entityClass;
		this.dao = dao;
	}

	public Page<T> getPageList(SearchCondition searchCondition) {
		PageRequest pageRequest = buildPageRequest(
				searchCondition.getPageNumber(), searchCondition.getPageSize(),
				searchCondition.getSortFieldName());
		if (null!=searchCondition.getConditionList() && searchCondition.getConditionList().size()>0) {
			spec = buildSpecification(searchCondition.getConditionList());
		} else {
			spec = null;
		}

		@SuppressWarnings("unchecked")
		Page<T> retPage = ((JpaSpecificationExecutor<T>)dao).findAll(spec, pageRequest);
		return retPage;
	}

	public Page<T> getPageList(PageRequest pageRequest) {
		@SuppressWarnings("unchecked")
		Page<T> retPage = ((PagingAndSortingRepository<T, ?>)dao).findAll(pageRequest);
		return retPage;
	}

    public Page<T> getPageList(List<SearchFilter> filters,PageRequest pageRequest) {
        if (null!=filters && filters.size()>0) {
        	spec = buildSpecification(filters);
        } else {
			spec = null;
		}

        @SuppressWarnings("unchecked")
		Page<T> retPage = ((JpaSpecificationExecutor<T>)dao).findAll(spec, pageRequest);
        return retPage;
    }
	
	public List<T> getList() {
		List<SearchFilter> searchList = null;
		return getList(searchList,null);
	}
	
	public List<T> getList(Sort sort) {
		List<SearchFilter> searchList = null;
		return getList(searchList,sort);
	}
	
	public List<T> getList(int topNumber) {
		List<T> retList = null;
		PageRequest pageRequest = buildPageRequest(1,topNumber,null);
		@SuppressWarnings("unchecked")
		Page<T> retPage = ((JpaSpecificationExecutor<T>)dao).findAll(spec,pageRequest);
		retList = retPage.getContent(); 
		return retList;
	}
	
	public List<T> getList(List<SearchFilter> searchList) {
		return getList(searchList,null);
	}
	
	public List<T> getList(List<SearchFilter> searchList,Sort sort) {
		if (null!=searchList  && searchList.size()>0) {
			spec = buildSpecification(searchList);
		} else {
			spec = null;
		}

		@SuppressWarnings("unchecked")
		List<T> retList = ((JpaSpecificationExecutor<T>)dao).findAll(spec,sort);
		return retList;
	}

	public List<T> getList(SearchCondition searchCondition,Sort sort) {
		if ( searchCondition!=null && searchCondition.getConditionList()!=null ) {
			spec = buildSpecification(searchCondition.getConditionList());
		} else {
			spec = null;
		}
		if ( sort == null ){
			sort = new Sort(Sort.Direction.DESC, searchCondition.getSortFieldName());
		}
		@SuppressWarnings("unchecked")
		List<T> retList = ((JpaSpecificationExecutor<T>)dao).findAll(spec,sort);
		return retList;
	}
	
	public SearchCondition getSearchCondition(List<Object> searchParams){
		return null;
	}

    @SuppressWarnings("unchecked")
	public T getOne(List<SearchFilter> searchList) {
        if (null!=searchList  && searchList.size()>0) {
        	spec = buildSpecification(searchList);
        } else {
			spec = null;
		}

        return (T)((JpaSpecificationExecutor<T>)dao).findOne(spec);
    }
	
	public PageRequest buildPageRequest(int pageNumber, int pagzSize,
			String sortFieldName) {
		Sort sort = null;
		if (null == sortFieldName||sortFieldName.equals("auto")) {
			sort = new Sort(Direction.DESC, "id");
		}else if(null == ReflectionUtils.findField(entityClass, sortFieldName)){
			sort = new Sort(Direction.DESC, "id");
		}else{
			sort = new Sort(Direction.ASC, sortFieldName);
		}

		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

	public PageRequest buildPageRequest(int pageNumber, int pageSize) {
		return buildPageRequest(pageNumber,pageSize,null);
	}

	public Specification<T> buildSpecification(final Collection<SearchFilter> filters){
		return new Specification<T> () {  
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				if (Collections3.isNotEmpty(filters)) {

					List<Predicate> predicates = getPredicate(filters,root,builder);
					if (!predicates.isEmpty()) {
						return builder.and(predicates.toArray(new Predicate[predicates.size()]));
					}
				}

				return builder.conjunction();
			}
		};
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected List<Predicate> getPredicate(final Collection<SearchFilter> filters,Root<T> root, CriteriaBuilder builder){
		List<Predicate> predicates = Lists.newArrayList();
		for (SearchFilter filter : filters) {
			Field field = ReflectionUtils.findField(entityClass, filter.getFieldName());
			if ( null == field && !filter.getFieldName().contains(".")){
				continue;
			}
			String[] names = StringUtils.split(filter.fieldName, ".");
			Path expression = root.get(names[0]);
			for (int i = 1; i < names.length; i++) {
				expression = expression.get(names[i]);
			}

			switch (filter.operator) {
			case EQ:
				predicates.add(builder.equal(expression, filter.value));
				break;
			case NEQ:
                predicates.add(builder.notEqual(expression, filter.value));
                break;
			case LIKE:
				predicates.add(builder.like(expression, "%" + filter.value + "%"));
				break;
			case GT:
				predicates.add(builder.greaterThan(expression, (Comparable) filter.value));
				break;
			case LT:
				predicates.add(builder.lessThan(expression, (Comparable) filter.value));
				break;
			case GTE:
				predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) filter.value));
				break;
			case LTE:
				predicates.add(builder.lessThanOrEqualTo(expression, (Comparable) filter.value));
				break;
			case IN:
				String value = (String) filter.value;
				predicates.add(expression.in((Object[])value.split(",")));
				break;
			}
		}
		return predicates;
	}

	public Specification<T> getSpec() {
		return spec;
	}

	public void setSpec(Specification<T> spec) {
		this.spec = spec;
	}

	public Object getDao() {
		return dao;
	}

	public void setDao(Object dao) {
		this.dao = dao;
	}
	
}
