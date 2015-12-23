/*
 * Copyright (c)  2014, Newtouch
 * All rights reserved. 
 *
 * $id: CodeListConstant.java 9552 2014-4-6 上午10:58:11 WangLijun$
 */
package com.newtouch.lion.common.codelist;

/**
 * <p>
 * Title: CodeList定义
 * </p>
 * <p>
 * Description: CodeList定义
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
public class CodeListConstant {
	/** 应用资源类型级别分割符 */
	public static final String RESTYPE_LEVEL_SEPARATOR = "_";
	/** 应用资源分类 */
	public static final String RESTYPE_CATEGORY = "category";
	/** 应用模块分类资源 */
	public static final String RESTYPE_MENU = "menu";
	/** 应用资源明细 */
	public static final String RESTYPE_ITME = "item";
	/**应用资源按钮*/
	public static final String RESTYPE_BUTTON="button";
	/** 应用资源类型 */
	public static final String RESTYPE_APPLICATION = "application";
	/** 应用模块资源类型 */
	public static final String RESTYPE_MODULE = "module";
	/** 应用模块资源类型-菜单 */
	public static final String RESTYPE_MODULE_MENU_CATEGORY = RESTYPE_MODULE
			+ RESTYPE_LEVEL_SEPARATOR + RESTYPE_MENU + RESTYPE_LEVEL_SEPARATOR
			+ RESTYPE_CATEGORY;
	/** 应用模块资源类型-菜单明细 */
	public static final String RESTYPE_MODULE_CATEGORY_ITEM = RESTYPE_MODULE_MENU_CATEGORY
			+ RESTYPE_LEVEL_SEPARATOR + RESTYPE_ITME;
	/** 应用模块资源类型-菜单明细 */
	public static final String RESTYPE_MODULE_CATEGORY_ITEM_BUTTOM= RESTYPE_MODULE_MENU_CATEGORY
			+ RESTYPE_LEVEL_SEPARATOR + RESTYPE_ITME+RESTYPE_LEVEL_SEPARATOR+RESTYPE_BUTTON;
}
