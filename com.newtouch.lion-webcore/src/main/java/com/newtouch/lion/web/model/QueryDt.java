/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: QueryDT.java 9552 2015年2月9日 上午11:46:12 WangLijun$
*/
package com.newtouch.lion.web.model; 

import java.util.List;





/**
 * <p>
 * Title: DataTable 请求对象
 * </p>
 * <p>
 * Description: DataTable
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
public class QueryDt extends QueryVo{
	/**DataTables 请求ID*/
	private  Integer requestId=0;
	/**列数大小*/
	private Integer columnSize;
	/**列数名称，用','分割*/
	private String columnNames;	
	/**需要排序的列数*/
	private Integer sortingCols;
	/**多行排序*/
	private List<Order> orders;
	/**单行排序的列名**/
	private String sortCol;
 
	
	
	/**默认*/
	public QueryDt() {
		super();
	}


	/**
	 * @return DataTables 请求ID
	 */
	public Integer getRequestId() {
		return requestId;
	}


	/**
	 * @param requestId DataTables 请求ID
	 */
	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}


	/**
	 * @return the columnSize
	 */
	public Integer getColumnSize() {
		return columnSize;
	}


	/**
	 * @param columnSize the columnSize to set
	 */
	public void setColumnSize(Integer columnSize) {
		this.columnSize = columnSize;
	}


	/**
	 * @return the columnNames
	 */
	public String getColumnNames() {
		return columnNames;
	}


	/**
	 * @param columnNames the columnNames to set
	 */
	public void setColumnNames(String columnNames) {
		this.columnNames = columnNames;
	}


	/**
	 * @return the sortingCols
	 */
	public Integer getSortingCols() {
		return sortingCols;
	}


	/**
	 * @param sortingCols the sortingCols to set
	 */
	public void setSortingCols(Integer sortingCols) {
		this.sortingCols = sortingCols;
	}


	/**
	 * @return 多行排序
	 */
	public List<Order> getOrders() {
		return orders;
	}


	/**
	 * @param orders 多行排序
	 */
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}


	/**
	 * @return 单行排序的列名
	 */
	public String getSortCol() {
		return sortCol;
	}


	/**
	 * @param sortCol 单行排序的列名
	 */
	public void setSortCol(String sortCol) {
		this.sortCol = sortCol;
	}
}

	