/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: QueryVo.java 9552 2015年2月3日 下午3:27:49 WangLijun$
*/
package com.newtouch.lion.web.model; 

import com.newtouch.lion.common.sql.QueryOrder;

/**
 * <p>
 * Title:  DataGrid查询对象
 * </p>
 * <p>
 * Description:  DataGrid查询对象 -针对的easyUI-DataGrid查询
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
public class QueryVo{
	/**当前页索引*/
	private Integer page=0;
	/**每页显示记录数*/
	private Integer rows=15;
	/**排序字段*/
	private String sort;
	/**排序显示顺序：order 升序或降序*/
	private String order=QueryOrder.ASC;
	/***表格ID*/
	private String tableId;
	
	public QueryVo() {
		 super();
	}

	/**
	 * @return 当前页索引
	 */
	public Integer getPage() {
		 
		return this.page;
	}

	/**
	 * @param page 设置前页索引
	 */
	public void setPage(Integer page) {
		this.page = page;
	}

	/**
	 * @return the 每页显示记录数
	 */
	public Integer getRows() {
		return rows;
	}

	/**
	 * @param rows 设置每页显示记录数
	 */
	public void setRows(Integer rows) {
		this.rows = rows;
	}

	/**
	 * @return 排序字段名称
	 */
	public String getSort() {
		return sort;
	}

	/**
	 * @param  sort 设置排序字段名称
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}

	/**
	 * @return the order 排序顺序
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * @param order 设置排序顺序
	 */
	public void setOrder(String order) {
		this.order = order;
	}

	/**
	 * @return 表格ID
	 */
	public String getTableId() {
		return tableId;
	}

	/**
	 * @param tableId 表格ID
	 */
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	
	
}

	