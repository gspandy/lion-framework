/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: ExcelHeader.java 9552 2015年1月27日 下午4:55:18 WangLijun$
 */
package com.newtouch.lion.common.excel;

/**
 * <p>
 * Title: Header 标题栏定义
 * </p>
 * <p>
 * Description: Header 标题栏定义,包含字段名称、宽度、字段显示名称
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
public class Header {
	/** 字段名称 */
	private String fieldName;
	/** 宽度 */
	private Integer width;
	/** 字段显示名称 */
	private String name;

	/***
	 * 默认
	 */
	public Header() {
		super();
	}
	/***
	 * 
	 * @param fieldName 
	 * @param name
	 */
	public Header(String fieldName, String name){
		this.fieldName=fieldName;
		this.name=name;
	}
	
	/**
	 * @param fieldName 显示名称
	 * @param width 宽度
	 * @param name 名称
	 */
	public Header(String fieldName, String name,Integer width) {
		this.fieldName = fieldName;
		this.width = width;
		this.name = name;
	}



	/**
	 * @return the 字段名称
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * @param fieldName  字段名称
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * @return the 宽度
	 */
	public Integer getWidth() {
		return width;
	}

	/**
	 * @param width 宽度
	 */
	public void setWidth(Integer width) {
		this.width = width;
	}

	/**
	 * @return  显示名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name 显示名称
	 */
	public void setName(String name) {
		this.name = name;
	}

}
