/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: Config.java 9552 2015年1月27日 下午4:57:36 WangLijun$
*/
package com.newtouch.lion.common.excel; 

import java.util.HashMap;
import java.util.Map;

import com.newtouch.lion.common.date.DateUtil;

/**
 * <p>
 * Title: Excel导出配置信息
 * </p>
 * <p>
 * Description: Excel导出配置信息，包含标题、标题栏、内容的字体颜色样式宽度
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
public class ExcelConfig {
	/**默认*/
	public  final static String  DEFAULT_FONTNAME="微软雅黑";
	/** 字体 默认为：微软雅黑 **/
	private String fontName =DEFAULT_FONTNAME;
	/**标题栏字体大小 */
	private Short titleFontSize=14;
	/**字体大小*/
	private Short fontSize=10;
	/**小数格式:默认保留两位小数*/
	private String decimalFmt="#,##0.00";
	/**全局日期格式:默认格式为：yyyy-MM-dd HH:mm:ss*/
	private String dataPattern=DateUtil.FORMAT_DATETIME_DEFAULT;
	/**默认Boolean转换*/
	protected static Map<Object,Object> DEFAULT_BOOLEAN_MAP;
	/**默认宽度 15*/
	private Integer defaultColWidth=15;
	
	static{
		DEFAULT_BOOLEAN_MAP=new HashMap<Object, Object>();
		DEFAULT_BOOLEAN_MAP.put(Boolean.FALSE,"否");
		DEFAULT_BOOLEAN_MAP.put(Boolean.TRUE, "是");
	}
	/***
	 * 默认
	 */
	public ExcelConfig() {
		super();
	}
	
	/***
	 * 构造函数
	 * @param fontName 字体名称
	 * @param titleFontSize 标题栏字体大小
	 * @param fontSize   内容字体大小
	 * @param decimalFmt 数据字格式
	 * @param dataPattern 内容日期格式
	 * @param defaultColWidth 默认列宽度
	 */
	
	/***
	 * 获取默认Boolean转换的对象
	 * @return 返回默认配置
	 */
	public Map<Object,Object> getBooleanMap(){
		return DEFAULT_BOOLEAN_MAP;
	}
	
	public ExcelConfig(String fontName,
			Short titleFontSize, Short fontSize, String decimalFmt,
			String dataPattern, Integer defaultColWidth) {
		super();
		this.fontName = fontName;
		this.titleFontSize = titleFontSize;
		this.fontSize = fontSize;
		this.decimalFmt = decimalFmt;
		this.dataPattern = dataPattern;
		this.defaultColWidth = defaultColWidth;
	}
	/**
	 * @return the 内容字体
	 */
	public String getFontName() {
		return fontName;
	}
	/**
	 * @param fontName 内容字体
	 */
	public void setFontName(String fontName) {
		this.fontName = fontName;
	}
	/**
	 * @return the titleFontSize 标题内容字体大小
	 */
	public Short getTitleFontSize() {
		return titleFontSize;
	}
	/**
	 * @param titleFontSize 标题内容字体大小
	 */
	public void setTitleFontSize(Short titleFontSize) {
		this.titleFontSize = titleFontSize;
	}
	/**
	 * @return the fontSize 全局内容字体大小
	 */
	public Short getFontSize() {
		return fontSize;
	}
	/**
	 * @param fontSize 全局内容字体大小
	 */
	public void setFontSize(Short fontSize) {
		this.fontSize = fontSize;
	}
	/**
	 * @return the 小数格式
	 */
	public String getDecimalFmt() {
		return decimalFmt;
	}
	/**
	 * @param decimalFmt 小点格式
	 */
	public void setDecimalFmt(String decimalFmt) {
		this.decimalFmt = decimalFmt;
	}
	/**
	 * @return the 全局日期格式
	 */
	public String getDataPattern() {
		return dataPattern;
	}
	/**
	 * @param dataPattern 全局日期格式
	 */
	public void setDataPattern(String dataPattern) {
		this.dataPattern = dataPattern;
	}
	/**
	 * @return the 默认列宽度
	 */
	public Integer getDefaultColWidth() {
		return defaultColWidth;
	}
	/**
	 * @param defaultColWidth 默认列宽度
	 */
	public void setDefaultColWidth(Integer defaultColWidth) {
		this.defaultColWidth = defaultColWidth;
	}
}	

	