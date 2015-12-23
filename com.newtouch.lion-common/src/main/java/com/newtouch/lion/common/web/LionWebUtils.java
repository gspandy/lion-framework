/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: LionWebUtils.java 9552 2015年3月4日 下午5:53:49 WangLijun$
*/
package com.newtouch.lion.common.web; 

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * Title: Lion Web 工具类
 * </p>
 * <p>
 * Description:  Lion Web 工具类
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
public class LionWebUtils {
	/**请求字符串**/
	public static final String AJAX_XHR="XMLHttpRequest";
	/**Ajax请求字符串 x-requested-with*/
	public static final String AJAX_HEADER_XRW="x-requested-with";
	
	/****
	 * 判断是否Ajax请求
	 * @param request 请求对象
	 * @return 是则返回true 否则返回false
	 */
	public static boolean isAjax(ServletRequest request){
		HttpServletRequest servletRequest=(HttpServletRequest) request;
		if(AJAX_XHR.equalsIgnoreCase(servletRequest.getHeader(AJAX_HEADER_XRW))){
			return true;
		}
		return false;
		
	} 
}

	