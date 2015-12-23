/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: Align.java 9552 2015年1月27日 下午10:41:41 WangLijun$
*/
package com.newtouch.lion.common.excel; 

import org.apache.poi.hssf.usermodel.HSSFCellStyle;


/**
 * <p>
 * Title: 单元对齐方式
 * </p>
 * <p>
 * Description: 单元对齐方式
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
public enum CellAlign {
	/**左对齐*/
	LEFT("LEFT",HSSFCellStyle.ALIGN_LEFT),
	/**居中对齐*/
	CENTER("CENTER",HSSFCellStyle.ALIGN_CENTER),
	/**右对齐*/
	RIGHT("RIGHT",HSSFCellStyle.ALIGN_RIGHT);
	/**对齐方式*/
	private String code;
	/**单元列表*/
	private short  cellCode;
	
	private CellAlign(String code,short cellCode) {
		 this.code=code;
		 this.cellCode=cellCode;
	}

	/**
	 * @return HTML单元格对齐方式
	 */
	public String code() {
		return code;
	}

 
	/**
	 * Excel 单元格对齐方式
	 * @return the cellCode
	 */
	public short cellCode() {
		return cellCode;
	}
}

	