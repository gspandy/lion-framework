/*
 * Copyright 2012, NEWTOUCH Co., Ltd. All right reserved.
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF NEWTOUCH CO., LTD.
 * THE CONTENTS OF THIS FILE MAY NOT BE DISCLOSED
 * TO THIRD PARTIES, COPIED OR DUPLICATED IN ANY FORM, IN WHOLE OR IN PART,
 * WITHOUT THE PRIOR WRITTEN PERMISSION OF NEWTOUCH CO., LTD.
 */
package com.newtouch.trans.core.exporter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.MDC;

import com.newtouch.trans.core.lifecycle.ApplicationProperties;

/**
 * 集成spring的exporter附加了系统上下文serverIP的设置
 * 
 * @author dongfeng.zhang
 * @version 1.0
 */
public class HttpInvokerServiceExporter extends org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter {
	/**
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter#handleRequest(javax.servlet.http.
	 *      HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (ApplicationProperties.getServerIp() == null) {
			ApplicationProperties.setServerIp(request.getLocalAddr());
			ApplicationProperties.setServerName(request.getLocalName());
		}
		String no = (String) MDC.get("RequestNo");
		if (StringUtils.isEmpty(no)) {
			String requestno = Long.toHexString(System.currentTimeMillis()) + String.valueOf(Math.random()).substring(2, 8);
			MDC.put("RequestNo", requestno);
		}
		super.handleRequest(request, response);
		MDC.clear();
	}
}
