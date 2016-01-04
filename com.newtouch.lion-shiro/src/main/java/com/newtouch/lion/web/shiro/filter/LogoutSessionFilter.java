/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: LogoutSessionFilter.java 9552 2015年2月27日 下午9:40:56 WangLijun$
*/
package com.newtouch.lion.web.shiro.filter; 

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authc.LogoutFilter;

import com.newtouch.lion.common.user.UserInfo;
import com.newtouch.lion.web.shiro.cache.SessionCacheManager;
import com.newtouch.lion.web.shiro.constant.Constants;
import com.newtouch.lion.web.shiro.session.LoginSecurityUtil;

/**
 * <p>
 * Title: 扩展Logout退出功能
 * </p>
 * <p>
 * Description: 扩展Logout退出功能，并清除Session并发控制缓存中的数据
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
public class LogoutSessionFilter extends LogoutFilter {
	
	/** Shiro Session缓存管理*/
	private SessionCacheManager sessionCacheManager;	
	
	
	/**
	 * @return   Shiro Session缓存管理
	 */
	public SessionCacheManager getSessionCacheManager() {
		return sessionCacheManager;
	}
	/**
	 * @param sessionCacheManager  Shiro Session缓存管理
	 */
	public void setSessionCacheManager(SessionCacheManager sessionCacheManager) {
		this.sessionCacheManager = sessionCacheManager;
	}



	/***
	 * 默认构造函数
	 */
	public LogoutSessionFilter() {
		 super();
	}
	
	/***
	 * 重写该方法，并除缓存的数据
	 */
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response)
			throws Exception {
		UserInfo user = LoginSecurityUtil.getUser();
		if(user!=null){
			//清除缓存
			this.sessionCacheManager.removeSessionController(Constants.CACHE_SESSION_NAME,user.getUsername());
		}
		return super.preHandle(request, response);
	}
}

	