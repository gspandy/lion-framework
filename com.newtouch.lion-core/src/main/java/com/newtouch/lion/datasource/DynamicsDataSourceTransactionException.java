/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: DynamicsDataSourceTransactionException.java 9552 2015年10月9日 下午3:02:07 WangLijun$
*/
package com.newtouch.lion.datasource;

import com.newtouch.lion.exception.BaseException;

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
public class DynamicsDataSourceTransactionException extends BaseException{

	public DynamicsDataSourceTransactionException(String code) {
		super(code);
	}
	
	/**
	 * Construct a {@code NestedRuntimeException} with the specified detail message
	 * and nested exception.
	 * @param msg the detail message
	 * @param cause the nested exception
	 */
	public DynamicsDataSourceTransactionException(String msg, Throwable cause) {
		super(msg, cause);
	}

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 8811836036548037217L;
	
	
	
}

	