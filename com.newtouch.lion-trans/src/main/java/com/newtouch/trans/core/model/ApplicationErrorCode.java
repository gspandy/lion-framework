package com.newtouch.trans.core.model;

public interface ApplicationErrorCode {
	/**
	 * 请求成功
	 */
	static final String SUCCESS					= "A0000000";
	/**
	 * 请求异常
	 */
	static final String REQUEST_ERROR		     	= "A00R0000";
	/**
	 * 请求拒绝
	 */
	static final String REQUEST_REFUSED			= "A00R0001";
	/**
	 * 请求对象为空
	 */
	static final String REQUEST_IS_NULL			= "A00R0002";
	/**
	 * 请求对象body为空
	 */
	static final String REQUEST_BODY_IS_NULL		= "A00R0003";
	/**
	 * 异常
	 */
	static final String EXCEPTION					= "A00E0001";
	/**
	 * 返回对象为空
	 */
	static final String RESPONSE_IS_NULL			= "A00E0010";
	/**
	 * 返回对象body为空
	 */
	static final String RESPONSE_BODY_IS_NULL		= "A00E0020";
	
	/**
	 * 应用上下文异常
	 */
	static final String APP_CONTEXT_EXCEPTION		= "C00E0001";
}
