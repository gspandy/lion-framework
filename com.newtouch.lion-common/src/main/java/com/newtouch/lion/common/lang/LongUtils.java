/*
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: LongUtils.java 9552 2013-4-9 上午8:48:22 WangLijun$
 */
package com.newtouch.lion.common.lang;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * Title:Long工具类
 * </p>
 * <p>
 * Description:Long工具类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class LongUtils implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 88204265042608732L;

	public static final String SPARATOR_COMMA = ",";

	/**
	 * @param str
	 *            eg:"1,2,3,4,5";
	 * @param separatorChar
	 *            分割字符
	 * @return 返回
	 * */
	public static List<Long> parserStringToLong(String str, String separatorChar) {
		List<Long> ids = new ArrayList<Long>();
		List<String> strs = Arrays
				.asList(StringUtils.split(str, separatorChar));
		for (String temp : strs) {
			ids.add(Long.parseLong(temp));
		}
		return ids;
	}

}
