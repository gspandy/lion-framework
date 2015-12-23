/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: NumberUtils.java 9552 2015年1月27日 下午2:41:42 WangLijun$
 */
package com.newtouch.lion.common.number;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * Title: 数字工具类
 * </p>
 * <p>
 * Description: 数字工具类
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
public final class NumberUtils {
	/**
	 * 是否数字正则表达式
	 * */
	public static final String PATTREN_ISNUMERIC="^(-?\\d+)(\\.\\d+)?$";
	/**判断是否小数正则表达式**/
	public static final String PARTTENN_ISDECIMAL="[-+]{0,1}\\d+\\.\\d*|[-+]{0,1}\\d*\\.\\d+";
	/***
	 * 是否匹配
	 * @param regex 正则表达式
	 * @param str  检查字符串
	 * @return 匹配则返回true,否则返回false
	 */
	private static Boolean isMatch(String regex, String str){  
        if (StringUtils.isEmpty(str)) {  
            return Boolean.FALSE;  
        }  
        Pattern pattern = Pattern.compile(regex);  
        Matcher isNum = pattern.matcher(str);  
        return isNum.matches();  
    }  
	
	/***
	 * 判断字符是否数字类型为：小数、正负数等
	 * @param str
	 * @return Boolean 是数字则返回true,否则返回fase
	 */
	public static Boolean isNumeric(String str) {		 
		return isMatch(PATTREN_ISNUMERIC,str);
	}
	/****
	 * 判断字符串是否是小数
	 * @param str  字符串
	 * @return 
	 */
	public static Boolean isDecimal(String str){  
	   return isMatch(PARTTENN_ISDECIMAL,str);  
	} 
}
