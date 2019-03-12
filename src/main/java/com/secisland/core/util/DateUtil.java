/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.core.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {
	private static final String DEFAULT_PATTERN = "yyyyMMddHHmmss";

	public static boolean matchDate(Date oldDate,Date newDate,long spanTime){
		if(oldDate == null || newDate==null )
			return false;
		long oldTime = oldDate.getTime();
		long newTime = newDate.getTime();
		if(newTime < oldTime) 
			return false;
		long diff = newTime - oldTime;
		return diff/1000 <= spanTime;
	}
	
	public static String timestamp2str(Timestamp time, String pattern) {
		if (time == null) {
			throw new IllegalArgumentException("Timestamp is null");
		}
		if (pattern != null && !"".equals(pattern)) {
			if (!"yyyyMMddHHmmss".equals(pattern)
					&& !"yyyy-MM-dd HH:mm:ss".equals(pattern)
					&& !"yyyy-MM-dd".equals(pattern)
					&& !"MM/dd/yyyy".equals(pattern)) {
				throw new IllegalArgumentException("Date format [" + pattern
						+ "] is invalid");
			}
		} else {
			pattern = DEFAULT_PATTERN;
		}
		Calendar cal = Calendar.getInstance(TimeZone.getDefault());
		cal.setTime(time);
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(cal.getTime());
	}
}
