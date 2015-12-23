/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: IPAddressUtil.java 9552 2014-4-6 上午10:10:42 WangLijun$
 */
package com.newtouch.lion.common.ip;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * Title: IP地址工具类
 * </p>
 * <p>
 * Description: IP地址工具类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: NewTouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class IPAddressUtil {

	private static final String IP_UNKONWN = "unknown";

	public static String getIPAddress(HttpServletRequest request) {
		String ipAddress = request.getHeader("x-forwarded-for");
		if (StringUtils.isEmpty(ipAddress)
				|| IP_UNKONWN.equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isEmpty(ipAddress)
				|| IP_UNKONWN.equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isEmpty(ipAddress)
				|| IP_UNKONWN.equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("HTTP_CLIENT_IP");
		}
		if (StringUtils.isEmpty(ipAddress)
				|| IP_UNKONWN.equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (StringUtils.isEmpty(ipAddress)
				|| IP_UNKONWN.equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
		}
		return ipAddress;
	}
}
