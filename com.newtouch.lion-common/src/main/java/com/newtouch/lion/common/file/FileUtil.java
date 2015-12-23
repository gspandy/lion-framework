/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: FileUtil.java 9552 2015年1月28日 下午8:02:52 WangLijun$
*/
package com.newtouch.lion.common.file; 

import java.io.File;

/**
 * <p>
 * Title: 文件工具类
 * </p>
 * <p>
 * Description: 文件工具类
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
public class FileUtil{
	
	/**Excel文件扩展名*/
	public static final String EXCEL_EXTENSION=".xls";
	
	/***
	 *  判断是否创建该目录是否正确，如果不正确则创建目录；
	 * @param path 目录
	 * @return String 
	 */
	public static String mkDirs(String path){
		File file = new File(path);
		// 如果文件夹不存在则创建
		if (!file.exists() && !file.isDirectory()) {
			file.mkdirs();
		}
		return file.getAbsolutePath();
	}
	/***
	 * 根据文件路径、文件名创建文件
	 * @param path 路径
	 * @param fileName 文件名 
	 * @return 文件
	 */
	public static File newFile(String path,String fileName){
		StringBuilder sb=new StringBuilder();
		sb.append(FileUtil.mkDirs(path));
		sb.append(File.separator);
		sb.append(fileName);
		return new File(sb.toString());
	}
}

	