/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: Constants.java 9552 2015年2月27日 下午8:14:25 WangLijun$
*/
package com.newtouch.lion.web.shiro.constant; 
/**
 * <p>
 * Title: Shiro扩展的常量
 * </p>
 * <p>
 * Description: 
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
public class Constants {
	/**登录错误代码*/
	public static final String LOGIN_ERROR_MSG="login_error";
	/***
	 * Spring UserRealm的实例名称
	 */
	public static final String USER_REALM_BEAN="userRealm";
	/***
	 * 登录错误参数
	 */
	public static final String FORCE_LOGOUT="forcelogout";
	/***
	 * 用户已登录的错误-2
	 */
	public static final String LOGIN_MAXS_ERROR="2";
	/**用户被强制退出*/
	public static final String LOGIN_FORCE_ERROR="1";
	/**控制并发缓存名称*/
	public static final String CACHE_SESSION_NAME="shiroSessionController";
	/**密码重试次数缓存*/
	public static final String PASSWORD_RERTY_CACHE="passwordRetryCache";
	/**授权访问-authc*/
	public static final String AUTHC="authc";
	/**角色-roles*/
	public static final String ROLES="roles";
	/**权限管理-perms*/
	public static final String PERMS="perms";
	/**默认premission字符串-perms[\"{0}\"] */
	public static final String PERMS_STRING="perms[\"{0}\"]";
	/**角色结构格式-role[{0}] */
	public static final String ROLE_STRING = "roles[{0}]";
	/**Ajax  未登录的情况*/
	public static final String AJAX_UNLOGIN="user-unlogin";
	/**Ajax AJAX_UNLOGIN_STATUS  未登录状态*/
	public static final int AJAX_UNLOGIN_STATUS=998;
	
	/**Ajax  未登录的情况*/
	public static final String AJAX_UNAUTH="user-unauth";
	/**Ajax AJAX_UNLOGIN_STATUS  未登录状态*/
	public static final int AJAX_UNAUTH_STATUS=999;
	/***
	 * Redis Session 默认的前缀
	 */
	public static final String REDIS_SHIRO_SESSION = "shiro-session:";
	/**有效时间：18000 ms*/
	public static final int  SESSION_VAL_TIME_SPAN = 18000;
	
	
}

	