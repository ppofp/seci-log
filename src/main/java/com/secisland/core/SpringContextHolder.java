/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.core;

import java.io.File;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.WebApplicationContext;

public class SpringContextHolder implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) {
		SpringContextHolder.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		checkApplicationContext();
		return applicationContext;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		checkApplicationContext();
		return (T) applicationContext.getBean(name);
	}

	public static <T> T getBean(Class<T> clazz) {
		checkApplicationContext();
		Map<?, T> beanMaps = applicationContext.getBeansOfType(clazz);
		if (beanMaps != null && !beanMaps.isEmpty())
			return (T) beanMaps.values().iterator().next();
		else
			return null;
	}

	public static boolean containsBean(String beanName) {
		checkApplicationContext();
		return applicationContext.containsBean(beanName);
	}

	public static final String getRealPath(String path) {
		String realpath = ((WebApplicationContext) applicationContext)
				.getServletContext().getRealPath(path);
		if (!realpath.endsWith("\\") && !realpath.endsWith("/")) {
			realpath = realpath + File.separator;
		}

		return realpath;
	}

	public static final String getRealPath() {
		return getRealPath("/");
	}

	private static void checkApplicationContext() {
		if (applicationContext == null)
			throw new IllegalStateException(
					"applicaitonContext未注入,请在applicationContext.xml中定义SpringContextHolder");
	}
}
