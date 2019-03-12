/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.log.web.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateConverter implements Converter<String, Date> {

	private String dateFmt1;
	private String dateFmt2;

    public StringToDateConverter(String dateFmt1, String dateFmt2) {
        this.dateFmt1 = dateFmt1;
        this.dateFmt2 = dateFmt2;
    }

    @Override
	public Date convert(String source) {
        String dateFmt = dateFmt1;
        DateFormat df;
        if(!StringUtils.hasLength(source)) {
			return null;
		}
        if(source.length()<12){
            dateFmt = dateFmt2;
        }
        df = new SimpleDateFormat(dateFmt);


		try {
			return df.parse(source);
		} catch (ParseException e) {
			throw new IllegalArgumentException(String.format("类型转换失败，需要格式%s，但格式是[%s]", dateFmt, source));
		}
	}
}
