/*
* Copyright (c)  2014, Newtouch
* All rights reserved. 
*
* $id: CredentialExpiredException.java 9552 2014年12月29日 下午6:14:47 WangLijun$
*/
package com.newtouch.lion.web.shiro.authc; 

import org.apache.shiro.authc.AccountException;

/**
 * <p>
 * Title: 账户密码是无效的异常处理
 * </p>
 * <p>
 * Description: 
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
public class CredentialExpiredException extends AccountException {
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 479248188994814102L;

	/***
	 * 默认
	 */
	public CredentialExpiredException() {
		super();
	}
	/***
	 * 错误的异常信息
	 * @param message 异常消息
	 */
	public CredentialExpiredException(String message){
		super(message);
	}
	/***
	 * 
	 * @param cause the underlying Throwable that caused this exception to be thrown.
	 */
	public CredentialExpiredException(Throwable  cause){
		super(cause);
	}
	
	/***
	 *  
	 * @param message 异常消息
	 * @param cause  the underlying Throwable that caused this exception to be thrown.
	 */
	public CredentialExpiredException(String message, Throwable cause){
		super(message,cause);
	}
}

	