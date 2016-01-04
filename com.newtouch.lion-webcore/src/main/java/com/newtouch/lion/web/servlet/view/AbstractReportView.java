/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: AbstractReportView.java 9552 2015年1月28日 下午1:23:09 WangLijun$
*/
package com.newtouch.lion.web.servlet.view; 

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.lang.CharEncoding;
import org.springframework.web.servlet.view.AbstractView;

/**
 * <p>
 * Title: 报表视图抽像类
 * </p>
 * <p>
 * Description: 报表视图抽像类
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
public abstract class AbstractReportView extends AbstractView {
	/****
	 * 生成下载报表的文件名,支持中文名称下载，对下载文件名进行编码
	 * @param prefixName 前缀名称
	 * @param extension  扩展名
	 * @param fileName   文件名
	 * @return 下载附件文件名
	 * @throws UnsupportedEncodingException 
	 */
	public String getAttachmentFileName(String prefixName,String extension,String fileName) throws UnsupportedEncodingException{
		StringBuilder sb=new StringBuilder(prefixName);
		sb.append(URLEncoder.encode(fileName,CharEncoding.UTF_8));
		sb.append(extension);
		return sb.toString();
	}
}

	