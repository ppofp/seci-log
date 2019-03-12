/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.log.web.util;

import java.util.Calendar;
import java.util.Random;

public class UnifyFileName {

	public static String getUnifyFileName(String fileName) {
		return fileName.substring(0,fileName.lastIndexOf("."))+getCurrentDate() + randomString(5)
				+ fileName.substring(fileName.lastIndexOf("."));
	}

    public static String getUnifyFileName2(String fileName) {
        return fileName.substring(0,fileName.lastIndexOf("."))+"_"+getCurrentDate() + randomString(5)
                + fileName.substring(fileName.lastIndexOf("."));
    }

	public static String getUnifyFileType(String TypeName) {
		return getCurrentDate() + randomString(5) + "." + TypeName;
	}

	public static String getUnifyFileName(String fileName, int randomNum) {

		return getCurrentDate() + randomString(randomNum)
				+ fileName.substring(fileName.lastIndexOf("."));

	}

	private static String randomString(int number) {

		String str = "";
		Random ran = new Random();
		for (int i = 0; i < number; i++) {
			str += ran.nextInt(10);
		}

		return str;
	}

	private static String getCurrentDate() {
		String str = "";
		Calendar c = Calendar.getInstance();
		str = "" + c.get(Calendar.YEAR);

		int[] date = new int[] { c.get(Calendar.MONTH) + 1,
				c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.HOUR_OF_DAY),
				c.get(Calendar.MINUTE), c.get(Calendar.SECOND) };

		for (int i : date) {
			if (i < 10) {
				str += ("0" + i);
			} else {
				str += i;
			}
		}
		return str;
	}
}
