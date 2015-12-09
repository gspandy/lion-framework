/*
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: SystemException.java 9552 2013-4-7 下午12:05:36 WangLijun$
 */
package com.newtouch.lion.exception;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * Title:系统基础异常
 * </p>
 * <p>
 * Description:系统基础异常
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class SystemException extends BaseException {

	private static final long serialVersionUID = -1490742393672145880L;

	/** 错误代码 */
	private String code;
	/** 参数 */
	private Object[] params;

	public SystemException(String code) {
		this(code, StringUtils.EMPTY);
	}

	public SystemException(String code, String msg) {
		this(code, null, msg);
	}

	public SystemException(String code, String[] params) {
		this(code, params, null);
	}

	public SystemException(String code, String[] params, String msg) {
		this(code, params, msg, null);
	}

	public SystemException(String code, String[] params, String msg,
			Throwable cause) {
		super(code, msg, cause);
		this.code = code;
		this.params = params;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the params
	 */
	public Object[] getParams() {
		return params;
	}

	/**
	 * @param params
	 *            the params to set
	 */
	public void setParams(Object[] params) {
		this.params = params;
	}
}
