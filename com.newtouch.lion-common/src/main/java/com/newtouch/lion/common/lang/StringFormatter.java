/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: StringFormatter.java 9552 2015年3月13日 上午10:08:55 WangLijun$
*/
package com.newtouch.lion.common.lang; 

import java.text.MessageFormat;

/**
 * <p>
 * Title: 字符串格式化
 * </p>
 * <p>
 * Description:  字符串格式化
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class StringFormatter {
	/***
	 * 格式化URL
	 * @param pattern 格式
	 * @param arg 参数
	 * @return 格式化后字符串
	 */
	public static  String  format(String pattern,String arg){
		return MessageFormat.format(pattern,arg);
	}
	
}

	