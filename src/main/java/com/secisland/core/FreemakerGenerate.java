/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.core;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import net.sf.ehcache.CacheException;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemakerGenerate {
	static Configuration cfg;
	static StringTemplateLoader stringLoader;
	static {
		start();
	}

	public static void start() throws CacheException {
		if (cfg != null) {
			return;
		} else {
			cfg = new Configuration();
		}
		if (stringLoader != null) {
			return;
		} else {
			stringLoader = new StringTemplateLoader();
		}
	}

	public static String generateString(String freemaker,Map<String, String> root) throws IOException,TemplateException {
		stringLoader.putTemplate("myTemplate", freemaker);
		cfg.setTemplateLoader(stringLoader);
		Template temp = cfg.getTemplate("myTemplate", "utf-8");
		Writer out = new StringWriter(freemaker.length()+100);
		temp.process(root, out);
		out.flush();
		return out.toString();
	}
}
