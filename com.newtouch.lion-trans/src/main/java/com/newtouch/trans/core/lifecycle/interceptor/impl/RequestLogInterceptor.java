/*
 * Copyright 2012, NEWTOUCH Co., Ltd. All right reserved.
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF NEWTOUCH CO., LTD.
 * THE CONTENTS OF THIS FILE MAY NOT BE DISCLOSED
 * TO THIRD PARTIES, COPIED OR DUPLICATED IN ANY FORM, IN WHOLE OR IN PART,
 * WITHOUT THE PRIOR WRITTEN PERMISSION OF NEWTOUCH CO., LTD.
 */
package com.newtouch.trans.core.lifecycle.interceptor.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.newtouch.trans.core.lifecycle.interceptor.RequestInterceptor;
import com.newtouch.trans.core.model.Request;

/**
 * 存储请求包到数据库
 * 
 * @author dongfeng.zhang
 * @version 1.0
 */
public class RequestLogInterceptor implements RequestInterceptor {
	Logger logger = LoggerFactory.getLogger(RequestLogInterceptor.class);

	/*
	 * (non-Javadoc)
	 * @see
	 * com.cpic.eup.transaction.core.lifecycle.interceptor.RequestInterceptor#intercept(com.cpic.eup.transaction.core
	 * .model.Request)
	 */
	public void intercept(Request request) {
		try {
			if(StringUtils.isEmpty(MDC.get("RequestNo"))){
				MDC.put("RequestNo", request.getRequestNo());
			}
		} catch (Exception e) {
			logger.error("RequestNo sava to MDC error！", e);
		}
	}
}
