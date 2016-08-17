/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: DefaultDistributeCookieStore.java 9552 2015年6月15日 下午7:22:19 WangLijun$
 */
package com.newtouch.lion.dsession.store.impl;

import com.newtouch.lion.dsession.config.DistributedCookieAttributeConfig;
import com.newtouch.lion.dsession.context.DistributedSessionContext;
import com.newtouch.lion.dsession.store.DistributedCookieStore;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Title:
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
public class DefaultDistributeCookieStoreImpl implements DistributedCookieStore {

	/** 日志 */
	private static final Logger logger = LoggerFactory
			.getLogger(DefaultDistributeCookieStoreImpl.class);

	@Autowired
	private DistributedCookieAttributeConfig distributedCookieAttributeConfig;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.newtouch.lion.dsession.store.DistributedCookieStore#invalidate(com
	 * .newtouch.lion.session.context.RequestContext)
	 */
	@Override
	public void invalidate(DistributedSessionContext sessionContext) {
		Cookie[] cookies = sessionContext.getRequest().getCookies();
		// 判断是否为空
		if (cookies == null || cookies.length == 0) {
			return;
		}

		for (Cookie cookie : cookies) {
			String cookieName = cookie.getName();
			String decodeCookieName = cookieName;
			// 编码
			if (distributedCookieAttributeConfig.getEncoder() != null) {
				decodeCookieName = distributedCookieAttributeConfig
						.getEncoder().encodeName(decodeCookieName);
			}

			if (distributedCookieAttributeConfig.isMatch(decodeCookieName)) {
				cookie.setDomain(distributedCookieAttributeConfig.getDomain());
				cookie.setPath(distributedCookieAttributeConfig.getPath());
				cookie.setSecure(distributedCookieAttributeConfig.isSecure());
				cookie.setMaxAge(0);
				sessionContext.getResponse().addCookie(cookie);
			}

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.newtouch.lion.dsession.store.DistributedCookieStore#getAttribute(
	 * com.newtouch.lion.session.context.RequestContext, java.lang.String)
	 */
	@Override
	public Object getAttribute(DistributedSessionContext sessionContext, String key) {
		if (StringUtils.hasLength(key)) {
			return ObjectUtils.NULL;
		}

		Cookie[] cookies = sessionContext.getRequest().getCookies();
		// 判断是否为空
		if (cookies == null || cookies.length == 0) {
			return ObjectUtils.NULL;
		}
		for (Cookie cookie : cookies) {
			String cookieName = cookie.getName();
			String decodeCookieName = cookieName;
			// 编码
			if (distributedCookieAttributeConfig.getEncoder() != null) {
				decodeCookieName = distributedCookieAttributeConfig
						.getEncoder().encodeName(decodeCookieName);
			}

			if (key.equals(decodeCookieName)
					&& distributedCookieAttributeConfig.getEncoder() != null) {
				String value = cookie.getValue();
				return distributedCookieAttributeConfig.getEncoder()
						.decodeValue(value);
			}

		}

		return ObjectUtils.NULL;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.newtouch.lion.dsession.store.DistributedCookieStore#setAttribute(
	 * com.newtouch.lion.session.context.RequestContext, java.lang.String,
	 * java.lang.Object)
	 */
	@Override
	public void setAttribute(DistributedSessionContext sessionContext, String key,
							 Object value) {
		if (this.distributedCookieAttributeConfig.getEncoder() != null) {
			key = this.distributedCookieAttributeConfig.getEncoder()
					.encodeName(key);
			value = this.distributedCookieAttributeConfig.getEncoder()
					.encodeValue(value);
		}

		Cookie cookie = new Cookie(key, value.toString());
		cookie.setDomain(this.distributedCookieAttributeConfig.getDomain());
		cookie.setMaxAge(this.distributedCookieAttributeConfig.getMaxAge());
		cookie.setPath(this.distributedCookieAttributeConfig.getPath());
		cookie.setSecure(this.distributedCookieAttributeConfig.isSecure());
		logger.debug("set cookie: {}-{}", key, value);
		sessionContext.getResponse().addCookie(cookie);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.newtouch.lion.dsession.store.DistributedCookieStore#removeAttribute
	 * (com.newtouch.lion.session.context.SessionRequestContext,
	 * java.lang.String)
	 */
	@Override
	public void removeAttribute(DistributedSessionContext requestContext, String key) {
		if (key == null) {
			return;
		}

		String decodeCookieName = key;
		if (distributedCookieAttributeConfig.getEncoder() != null) {
			decodeCookieName = distributedCookieAttributeConfig.getEncoder()
					.encodeName(decodeCookieName);
		}
		Cookie cookie = new Cookie(decodeCookieName, null);
		cookie.setDomain(distributedCookieAttributeConfig.getDomain());
		cookie.setPath(distributedCookieAttributeConfig.getPath());
		cookie.setSecure(distributedCookieAttributeConfig.isSecure());
		cookie.setMaxAge(0);
		logger.debug("Remove-cookie: {}", key);
		requestContext.getResponse().addCookie(cookie);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.newtouch.lion.dsession.store.DistributedCookieStore#getAllAttributeNames
	 * (com.newtouch.lion.session.context.RequestContext)
	 */
	@Override
	public List<String> getAllAttributeNames(DistributedSessionContext sessionContext) {
		List<String> attributes = new ArrayList<String>();
		Cookie[] cookies = sessionContext.getRequest().getCookies();
		// 判断是否为空
		if (cookies == null || cookies.length == 0) {
			return attributes;
		}
		for (Cookie cookie : cookies) {
			String cookieName = cookie.getName();
			String decodeCookieName = cookieName;
			if (distributedCookieAttributeConfig.getEncoder() != null) {
				decodeCookieName = distributedCookieAttributeConfig
						.getEncoder().decodeName(cookieName);
			}
			if (distributedCookieAttributeConfig.isMatch(decodeCookieName)) {
				attributes.add(decodeCookieName);
			}
		}

		return attributes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.newtouch.lion.dsession.store.DistributedCookieStore#
	 * getCookieAttributeConfig()
	 */
	@Override
	public DistributedCookieAttributeConfig getDistributedCookieAttributeConfig() {

		return this.distributedCookieAttributeConfig;
	}

	/**
	 * @param distributedCookieAttributeConfig
	 *            the distributedCookieAttributeConfig to set
	 */
	public void setDistributedCookieAttributeConfig(
			DistributedCookieAttributeConfig distributedCookieAttributeConfig) {
		this.distributedCookieAttributeConfig = distributedCookieAttributeConfig;
	}

}
