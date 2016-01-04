/*
 * Copyright 2013, NEWTOUCH Co., Ltd. All right reserved.
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF NEWTOUCH CO., LTD.
 * THE CONTENTS OF THIS FILE MAY NOT BE DISCLOSED
 * TO THIRD PARTIES, COPIED OR DUPLICATED IN ANY FORM, IN WHOLE OR IN PART,
 * WITHOUT THE PRIOR WRITTEN PERMISSION OF NEWTOUCH CO., LTD.
 */
package com.newtouch.trans.core.exception;

/**
 * 请求报文异常类
 * 
 * @author dongfeng.zhang
 * @version 1.0
 */
public class RequestException extends RuntimeException {
	private static final long serialVersionUID = 7947725231138210156L;
	private String code;

	/**
	 * Creates a new RequestException object. 构造方法
	 * 
	 * @param code
	 *            DOCUMENT ME!
	 * @param message
	 *            DOCUMENT ME!
	 */
	public RequestException(String code, String message) {
		super(message);
		this.code = code;
	}

	public RequestException(String code, String message, Throwable throwable) {
		super(message, throwable);
		this.code = code;
	}

	/**
	 * XXX DOCUMENT ME!
	 * 
	 * @return XXX DOCUMENT ME!
	 */
	public String getCode() {
		return code;
	}
}
