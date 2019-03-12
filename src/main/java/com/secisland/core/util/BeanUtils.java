/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class BeanUtils {
	public static void copyPriperties(Object orig, Object desc) {

		String fileName, str, getName, setName;
		List<Field> fields = new ArrayList<Field>();
		Method getMethod = null;
		Method setMethod = null;
		try {
			Class<? extends Object> c1 = orig.getClass();
			Class<? extends Object> c2 = desc.getClass();

			Field[] fs1 = c1.getDeclaredFields();
			Field[] fs2 = c2.getDeclaredFields();
			for (int i = 0; i < fs2.length; i++) {
				for (int j = 0; j < fs1.length; j++) {
					if (fs1[j].getName().equals(fs2[i].getName())) {
						fields.add(fs1[j]);
						break;
					}
				}
			}
			if (null != fields && fields.size() > 0) {
				for (int i = 0; i < fields.size(); i++) {
					Field f = (Field) fields.get(i);
					fileName = f.getName();
					str = fileName.substring(0, 1).toUpperCase();
					getName = "get" + str + fileName.substring(1);
					setName = "set" + str + fileName.substring(1);
					getMethod = c1.getMethod(getName, new Class[] {});
					setMethod = c2.getMethod(setName, new Class[] { f.getType() });
					Object o = getMethod.invoke(orig, new Object[] {});
					if (null != o) {
						setMethod.invoke(desc, new Object[] { o });
					}
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
