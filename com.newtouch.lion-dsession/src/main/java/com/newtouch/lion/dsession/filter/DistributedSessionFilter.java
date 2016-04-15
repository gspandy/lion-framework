/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: DistributedSessionFilter.java 9552 2015年6月11日 上午10:53:53 WangLijun$
*/
package com.newtouch.lion.dsession.filter; 

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.newtouch.lion.dsession.config.DistributedCookieConfig;
import com.newtouch.lion.dsession.config.DistributedSessionConfig;
import com.newtouch.lion.dsession.context.DefaultDistributedSessionContext;
import com.newtouch.lion.dsession.util.DistributedContextUtil;
import com.newtouch.lion.session.common.SessionConstant;

/**
 * <p>
 * Title: 统一SESSION的入口filter
 * </p>
 * <p>
 * Description: 统一SESSION的入口filter
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
public class DistributedSessionFilter  implements Filter{
	
	
	/***日志*/
	private static final Logger logger=LoggerFactory.getLogger(DistributedSessionFilter.class);
	
	 /**
     * ServletContext
     */
    private ServletContext servletContext = null;
    /**Session配置*/
    private DistributedSessionConfig sessionConfig;
    /**Cookie配置*/
    private DistributedCookieConfig distributedCookieConfig;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		   servletContext = filterConfig.getServletContext();
		   WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
	        try {
	            sessionConfig = applicationContext.getBean(DistributedSessionConfig.class);
	            distributedCookieConfig = applicationContext.getBean(DistributedCookieConfig.class);
	        } catch (BeansException e) {
	        	logger.error(e.getMessage(),e);
	        }
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request= (HttpServletRequest) req;
        HttpServletResponse response= (HttpServletResponse) res;
        if (sessionConfig == null) {
            chain.doFilter(request, response);
        } else {
        	DefaultDistributedSessionContext distributedSessionContext = new DefaultDistributedSessionContext(servletContext, request,response, sessionConfig, distributedCookieConfig);
            // 跟新session的上次访问时间
        	DistributedContextUtil.writeKeyValueToCookie(distributedSessionContext,SessionConstant.SESSION_LASTACCESS_TIME,String.valueOf(System.currentTimeMillis()));
            chain.doFilter(distributedSessionContext.getRequest(), distributedSessionContext.getResponse());
        }
	}

	@Override
	public void destroy() {
		
	}
	
}	

	