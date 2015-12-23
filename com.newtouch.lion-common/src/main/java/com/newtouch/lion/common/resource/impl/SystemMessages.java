/*
 * Copyright (c)  2013, lion
 * All rights reserved. 
 *
 * $id: SystemMessages.java 9552 2013-4-7 上午11:32:39 WangLijun$
 */
package com.newtouch.lion.common.resource.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.newtouch.lion.common.resource.MessageResource;

/**
 * <p>
 * Title:系统异常消息处理
 * </p>
 * <p>
 * Description:系统异常消息处理
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: lion
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */

public class SystemMessages extends ResourceBundleMessageSource implements
		MessageResource { 
	/**日志*/
	private final static Logger logger=LoggerFactory.getLogger(SystemMessages.class);
	/**消息路径*/
	private  final static String BUNDLE_NAME=MessageResource.class.getPackage().getName()+".messages";
	/**消息访问对象*/
	private static MessageSourceAccessor accessor = null;

	public SystemMessages() {		
		this(BUNDLE_NAME);
		logger.info("BUNDLE_NAME:{}",BUNDLE_NAME);
	}

	public SystemMessages(String bundleName) {
		this.setBasename(bundleName);
		this.setUseCodeAsDefaultMessage(true);
	}

	private MessageSourceAccessor getAccessor() {
		if (accessor == null) {
			accessor = new MessageSourceAccessor(this);
		}
		return accessor;
	}

	public String getMessage(String code) {
		return getAccessor().getMessage(code);
	}

	public String getMessage(String code, Object[] args) {
		return getAccessor().getMessage(code, args);
	}	
}
