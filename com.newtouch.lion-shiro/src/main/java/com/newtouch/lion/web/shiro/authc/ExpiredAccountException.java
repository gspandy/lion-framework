/*
* Copyright (c)  2014, Newtouch
* All rights reserved. 
*
* $id: ExpiredAccountException.java 9552 2014年12月29日 下午6:07:42 WangLijun$
*/
package com.newtouch.lion.web.shiro.authc; 

import org.apache.shiro.authc.DisabledAccountException;

/**
 * <p>
 * Title: 账户有效期已过
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
public class ExpiredAccountException  extends  DisabledAccountException{

 
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 2725156421667788427L;

	/***
	 * 默认
	 */
	public ExpiredAccountException() {
		super();
	}
	/***
	 * 错误的异常信息
	 * @param message 异常消息
	 */
	public ExpiredAccountException(String message){
		super(message);
	}
	/***
	 * 
	 * @param cause the underlying Throwable that caused this exception to be thrown.
	 */
	public ExpiredAccountException(Throwable  cause){
		super(cause);
	}
	
	/***
	 *  
	 * @param message 异常消息
	 * @param cause  the underlying Throwable that caused this exception to be thrown.
	 */
	public ExpiredAccountException(String message, Throwable cause){
		super(message,cause);
	}
	 	
		
}

	