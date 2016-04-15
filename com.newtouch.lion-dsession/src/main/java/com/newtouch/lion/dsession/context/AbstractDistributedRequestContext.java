/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: AbstractDistributedContext.java 9552 2015年5月21日 下午2:52:43 WangLijun$
 */
package com.newtouch.lion.dsession.context;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.Assert;

/**
 * <p>
 * Title: 分布式上下文抽像类
 * </p>
 * <p>
 * Description: 分布式上下文抽像类
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
public abstract class AbstractDistributedRequestContext implements DistributedRequestContext {
	/***
	 * 分布式上下文
	 */
	private final DistributedRequestContext distributedRequestContext;

	/**
	 * servletContext
	 */
	private final ServletContext servletContext;

	/**
	 * HttpServletRequest
	 */
	private HttpServletRequest request;

	/**
	 * HttpServletResponse
	 */
	private HttpServletResponse response;

	/**
	 * originalRequest
	 */
	private HttpServletRequest originalRequest;

	/**
	 * @return the originalRequest
	 */
	public HttpServletRequest getOriginalRequest() {
		return originalRequest;
	}



	/**
	 * originalResponse
	 */
	private HttpServletResponse originalResponse;
	
	public AbstractDistributedRequestContext(DistributedRequestContext distributedRequestContext) {
		Assert.isNull(distributedRequestContext, "wrappedContext is null");
		this.distributedRequestContext=distributedRequestContext;
		this.servletContext = distributedRequestContext.getServletContext();
        this.originalRequest = distributedRequestContext.getRequest();
        this.originalResponse = distributedRequestContext.getResponse();
	}

	
	
	/**
	 * @param servletContext
	 * @param request
	 * @param response
	 */
	public AbstractDistributedRequestContext(ServletContext servletContext,
			HttpServletRequest request, HttpServletResponse response) {
		super();
		this.distributedRequestContext = null;
		this.servletContext = servletContext;
		this.request = request;
		this.response = response;
	}

	

	 
	/**
	 * @return the distributedRequestContext
	 */
	public DistributedRequestContext getDistributedRequestContext() {
		return distributedRequestContext;
	}



	/**
	 * @param response the response to set
	 */
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * @param originalRequest the originalRequest to set
	 */
	public void setOriginalRequest(HttpServletRequest originalRequest) {
		this.originalRequest = originalRequest;
	}

	/**
	 * @param originalResponse the originalResponse to set
	 */
	public void setOriginalResponse(HttpServletResponse originalResponse) {
		this.originalResponse = originalResponse;
	}



	/**
	 * @return the request
	 */
	public HttpServletRequest getRequest() {
		return request;
	}



	/**
	 * @param request the request to set
	 */
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}



	/**
	 * @return the servletContext
	 */
	public ServletContext getServletContext() {
		return servletContext;
	}



	/**
	 * @return the response
	 */
	public HttpServletResponse getResponse() {
		return response;
	}



	/**
	 * @return the originalResponse
	 */
	public HttpServletResponse getOriginalResponse() {
		return originalResponse;
	}
	
	
}
