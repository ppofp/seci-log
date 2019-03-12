/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.core;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class ConfigFileInfo {
	private static Properties  quartzConfig;
	private static ConfigFileInfo instance = null;
	
	public static ConfigFileInfo getInstance(){
		if(instance==null){
			instance = new ConfigFileInfo();
		}
		return instance;
	} 
	
	private ConfigFileInfo() {
		try {
			Resource resource = new ClassPathResource("/application.properties");
			quartzConfig = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Properties getConfigFile() {
		getInstance();
		return quartzConfig;
	}
}
