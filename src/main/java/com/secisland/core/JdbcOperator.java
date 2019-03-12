/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.core;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class JdbcOperator {


	public JdbcTemplate jdbcTemplate;
	
	private static class JdbcOperatorFactory {  
        private static JdbcOperator instance = new JdbcOperator();  
    }  
  
    public static JdbcOperator getInstance() {  
        return JdbcOperatorFactory.instance;  
    }  

	private JdbcOperator() {
		this.jdbcTemplate = SpringContextHolder.getBean(JdbcTemplate.class);

	}
	
	public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... args) throws DataAccessException {
		return jdbcTemplate.query(sql, rowMapper, args);
	}
	
	public List<Map<String, Object>> queryForMapList(String sql, Object... args) throws DataAccessException {
		return jdbcTemplate.queryForList(sql, args);
	}
}

