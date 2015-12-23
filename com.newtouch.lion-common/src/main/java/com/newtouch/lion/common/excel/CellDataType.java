/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: CellDataType.java 9552 2015年1月27日 下午10:59:18 WangLijun$
*/
package com.newtouch.lion.common.excel; 
/**
 * <p>
 * Title: 单元数据格式
 * </p>
 * <p>
 * Description: 单元数据格式(字符串-0 数字-1 、布尔-2、日期-3、字节数组-4)
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
public enum CellDataType {
	/**字符串-0 */
	STRING(0),
	/**数字类型*/
	NUMBER(1),
	/**布尔*/
	BOOLEAN(2),
	/**日期*/
	DATE(3),
	/**字节数组*/
	BYTES(4);
	/**单元格数据格式*/
	private Integer code;
	/**单元格数据格式*/
	private CellDataType(Integer code){
		this.code=code;
	}
	/**单元格数据格式*/
    public Integer code(){
    	return this.code;
    }
}

	