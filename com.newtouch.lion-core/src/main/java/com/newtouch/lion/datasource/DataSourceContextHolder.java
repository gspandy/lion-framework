/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: DataSourceContextHolder.java 9552 2015年10月9日 下午12:09:07 WangLijun$
*/
package com.newtouch.lion.datasource;
/**
 * <p>
 * Title: 数据源上下文环境
 * </p>
 * <p>
 * Description: 数据源上下文环境
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
public class DataSourceContextHolder {
	/**数据标识*/
	private static final ThreadLocal<String> contextHolder=new ThreadLocal<String>();
	/**从数据标识*/
	public static final String SLAVE="slave";
	/**主数据源标识*/
	public static final String 	MASTER="master";
	
	public static  String getDateSourceType(){
		return contextHolder.get();
	}
	
	public static  void setDataSourceType(String dataSourceType){
		contextHolder.set(dataSourceType);
	}
	
	public static void setSlave(){
		setDataSourceType(SLAVE);
	}
	
	public static void setMaster(){
		setDataSourceType(MASTER);
	}
	
	public static void clear(){
		contextHolder.remove();
	}
	
	public static boolean isNull(){
		return null==contextHolder.get();
	}
	
	public static boolean isSlave(){
		return SLAVE.equals(contextHolder.get());
	}
	
	public static boolean isMaster(){
		return MASTER.equals(contextHolder.get());
	}
}

	