/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: Sheet.java 9552 2015年1月27日 下午4:56:10 WangLijun$
*/
package com.newtouch.lion.common.excel; 

import java.util.List;

/**
 * <p>
 * Title: 工作表定义
 * </p>
 * <p>
 * Description: 工作表定义(标题、标题栏)
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
public class ExcelSheet {
	/**标题*/
	private String title;
	/**标题栏列表*/
	private List<Header> headers;
	/**
	 * 默认
	 * */
	public ExcelSheet() {
		super();
	}
	
	/***
	 * 
	 * @param title 标题
	 * @param headers 标题栏列表
	 */
	public ExcelSheet(String title, List<Header> headers) {
		super();
		this.title = title;
		this.headers = headers;
	}
	/**
	 * @return 标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title 标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return 标题栏列表
	 */
	public List<Header> getHeaders() {
		return headers;
	}
	/**
	 * @param headers 标题栏列表
	 */
	public void setHeaders(List<Header> headers) {
		this.headers = headers;
	}
	
	
}

	