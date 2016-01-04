/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: Order.java 9552 2015年2月9日 下午1:21:22 WangLijun$
 */
package com.newtouch.lion.web.model;


/**
 * <p>
 * Title: 排序方向
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
public class Order {
	/**排序字段名称*/
	private String sortCol;
	/**排序：升序或降序*/
	private String dir;
	/**默认构造函数*/
	public Order() {
		super();
	}
	/**
	 * @return the sortCol
	 */
	public String getSortCol() {
		return sortCol;
	}
	/**
	 * @param sortCol the sortCol to set
	 */
	public void setSortCol(String sortCol) {
		this.sortCol = sortCol;
	}
	/**
	 * @return the dir
	 */
	public String getDir() {
		return dir;
	}
	/**
	 * @param dir the dir to set
	 */
	public void setDir(String dir) {
		this.dir = dir;
	}
}
