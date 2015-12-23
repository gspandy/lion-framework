/*
 * Copyright (c)  2012, lion
 * All rights reserved. 
 *
 * $id: Constants.java 9552 2012-12-16 下午9:23:31 WangLijun$
 */
package com.newtouch.lion.common.constant;

/**
 * <p>
 * Title: 基础常量类
 * </p>
 * <p>
 * Description: 基础常量类，用于定义基础框架常量
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: lion
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public final class Constants {

	/**
	 * 路径分割符
	 */
	public static final String PATH_SPLITTER = "/";
	/** 应用名称 */
	public static final String APPLICATION_NAME = "eCommerce";
	/** 应用版本 */
	public static final String APPLICATION_VERSION = "1.0";
	/** 操作系统名称-属性 */
	public static final String OS_NAME = "os.name";
	/** 操作系统版本-属性 */
	public static final String OS_VERSION = "os.version";
	/** 操作系统补丁-属性 */
	public static final String OS_ARCH = "os.arch";
	/** JDK厂商-属性 */
	public static final String JDK_VENDOR = "java.vendor";
	/** JDK版本-属性 */
	public static final String JDK_VERSION = "java.version";
	/** JDK主目录-属性 */
	public static final String JDK_HOME = "java.home";
	/** 用户的账户名称 */
	public static final String USER_NAME = "user.name";
	/** 系统编码格式 */
	public static final String FILE_ENCODING = "file.encoding";
	/** WEB容器名称 */
	public static final String WEB_CONTAINER_NAME = "web.container.name";
	/** WEB容器启动时间 */
	public static final String WEB_CONTAINER_STARTDATE = "web.container.startdate";
	/** 后台管理默认密码 */
	public static final String DEFLAUT_PASSWORD = "111aaa";
	/**后台管理用户默认密码KEY*/
	public static final String DEFLAUT_PASSWORD_KEY="PwdInitialization";
	/**密码错误最大重试次数，默认为：5*/
	public static final Integer PASSWORD_RETYR_MAXCOUNT=5;
	/**冒号:*/
	public static final String  COLON=":";
}
