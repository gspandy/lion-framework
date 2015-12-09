
/*
* Copyright (c)  2014, Newtouch
* All rights reserved. 
*
* $id: BaseException.java 9552 2014年12月29日 上午10:41:40 WangLijun$
*/
package com.newtouch.lion.exception; 

import org.springframework.core.NestedRuntimeException;

/**
 * <p>
 * Title: 异常抽象类
 * </p>
 * <p>
 * Description: 异常抽象类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public  class BaseException extends NestedRuntimeException {
	

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1076714774087460610L;
    /**错误代码*/
	protected String code;
	
 
	/***
	 * 代码消息为空
	 * @param msg 错误代码
	 */
	public BaseException(String code) {
		super("");
		this.code=code;
	}
	/***
	 * 
	 * @param code 错误代码
	 * @param msg 错误消息
	 */
	public BaseException(String code,String msg) {
		super(msg);
		this.code=code;
	}

	/**
	 * Construct a {@code NestedRuntimeException} with the specified detail message
	 * and nested exception.
	 * @param msg the detail message
	 * @param cause the nested exception
	 */
	public BaseException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	/***
	 * 
	 * @param code
	 * @param msg
	 * @param cause
	 */
	public BaseException(String code,String msg,Throwable cause){
		super(msg,cause);
		this.code=code;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	
	
}

	