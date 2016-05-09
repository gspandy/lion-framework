/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: ExcelException.java 9552 2015年1月27日 下午9:05:19 WangLijun$
*/
package com.newtouch.lion.exception; 
/**
 * <p>
 * Title: excel 异常
 * </p>
 * <p>
 * Description: 异常类，用导入和导出功能
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
public class ExcelException extends BaseException {
	
	/****
	 * 错误代码和错误消息 
	 * @param code 错误代码
	 * @param msg  错误消息
	 */
	public ExcelException(String code, String msg) {
		super(code, msg);
	}
	

	/***
	 *  错误代码、错误消息、异常
	 * @param code
	 * @param msg
	 * @param cause
	 */
	public ExcelException(String code,String msg,Throwable cause){
		super(code,msg,cause);
	}
	
	public ExcelException(String code,Throwable cause,String[] params) {
		super(code,"",cause);
		this.params=params;
	}
	
	
	
	
	/**
	 * @return 获取异常参数
	 */
	public Object[] getParams() {
		return params;
	}


	/**
	 * @param params 设置异常参数
	 */
	public void setParams(Object[] params) {
		this.params = params;
	}




	/** 参数 */
	private Object[] params;

	/**
	 * 
	 */
	private static final long serialVersionUID = -6571794703794698221L;
	
}

	