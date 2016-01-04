/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: ForceLogoutFilter.java 9552 2015年2月27日 下午2:54:10 WangLijun$
 */
package com.newtouch.lion.web.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.newtouch.lion.web.shiro.service.SessionService;
import org.apache.shiro.session.Session;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.newtouch.lion.common.SpringContextUtil;
import com.newtouch.lion.common.user.UserInfo;
import com.newtouch.lion.web.shiro.cache.SessionCacheManager;
import com.newtouch.lion.web.shiro.constant.Constants;
import com.newtouch.lion.web.shiro.session.LoginSecurityUtil;

/**
 * <p>
 * Title: Shiro强制用户退出过滤器
 * </p>
 * <p>
 * Description: Shiro强制用户退出过滤器
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
public class ForceLogoutFilter extends AccessControlFilter {
	
	/**日志*/
	public static final Logger logger=LoggerFactory.getLogger(ForceLogoutFilter.class);
	
	
	/**后用户将强制退出的URL*/
	private String forceLogoutUrl;
	/** Shiro Session缓存管理*/
	private SessionCacheManager sessionCacheManager;
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.shiro.web.filter.AccessControlFilter#isAccessAllowed(javax
	 * .servlet.ServletRequest, javax.servlet.ServletResponse, java.lang.Object)
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		Session session = getSubject(request, response).getSession(false);
		if (session == null) {
			return true;
		}
		return (session.getAttribute(SessionService.SESSION_FORCE_LOGOUT_KEY) == null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.shiro.web.filter.AccessControlFilter#onAccessDenied(javax.
	 * servlet.ServletRequest, javax.servlet.ServletResponse)
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request,ServletResponse response) throws Exception {
		UserInfo user = LoginSecurityUtil.getUser();
		//清除缓存
		this.sessionCacheManager.removeSessionController(Constants.CACHE_SESSION_NAME,user.getUsername());
		//强制退出
		getSubject(request, response).logout();
		//UserRealm  userRealm=(UserRealm) SpringContextUtil.getBean(Constants.USER_REALM_BEAN,UserRealm.class);
		//清除缓存用户验证和授权信息的缓存
		//if(userRealm!=null){
		//	userRealm.clearAllCached();
	//	}

		StringBuilder sb=new StringBuilder();
		sb.append(this.forceLogoutUrl);
		sb.append(this.forceLogoutUrl.contains("?")?"&":"?");
		sb.append(Constants.FORCE_LOGOUT);
		sb.append("=");
		sb.append(Constants.LOGIN_FORCE_ERROR);		
		WebUtils.issueRedirect(request, response, sb.toString());
		return false;
	}

	/**
	 * @return 后用户将强制退出的URL
	 */
	public String getForceLogoutUrl() {
		return forceLogoutUrl;
	}

	/**
	 * @param forceLogoutUrl 后用户将强制退出的URL
	 */
	public void setForceLogoutUrl(String forceLogoutUrl) {
		this.forceLogoutUrl = forceLogoutUrl;
	}

	/**
	 * @return the sessionCacheManager Shiro Session缓存管理
	 */
	public SessionCacheManager getSessionCacheManager() {
		return sessionCacheManager;
	}

	/**
	 * @param sessionCacheManager Shiro Session缓存管理
	 */
	public void setSessionCacheManager(SessionCacheManager sessionCacheManager) {
		this.sessionCacheManager = sessionCacheManager;
	}
	
	
}
