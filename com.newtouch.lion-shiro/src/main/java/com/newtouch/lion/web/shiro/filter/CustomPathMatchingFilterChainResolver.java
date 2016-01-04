/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: CustomPathMatchingFilterChainResolver.java 9552 2015年3月3日 下午4:55:58 WangLijun$
 */
package com.newtouch.lion.web.shiro.filter;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;

import com.newtouch.lion.web.shiro.mgt.CustomDefaultFilterChainManager;

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
public class CustomPathMatchingFilterChainResolver extends PathMatchingFilterChainResolver {

	private CustomDefaultFilterChainManager customDefaultFilterChainManager;

	public void setCustomDefaultFilterChainManager(CustomDefaultFilterChainManager customDefaultFilterChainManager) {
		this.customDefaultFilterChainManager = customDefaultFilterChainManager;
		setFilterChainManager(customDefaultFilterChainManager);
	}

	public FilterChain getChain(ServletRequest request,ServletResponse response, FilterChain originalChain) {
		
		FilterChainManager filterChainManager = getFilterChainManager();
		
		if (!filterChainManager.hasChains()) {
			return null;
		}

		String requestURI = getPathWithinApplication(request);

		List<String> chainNames = new ArrayList<String>();
		
		for (String pathPattern : filterChainManager.getChainNames()) {			
			if (pathMatches(pathPattern, requestURI)) {
				chainNames.add(pathPattern);
			}
		}

		if (chainNames.size() == 0) {
			return null;
		}
		return customDefaultFilterChainManager.proxy(originalChain, chainNames);
	}

}
