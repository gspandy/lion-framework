package com.newtouch.trans.core.lifecycle.interceptor.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.newtouch.trans.core.exception.InterceptorException;
import com.newtouch.trans.core.lifecycle.interceptor.ResponseInterceptor;
import com.newtouch.trans.core.model.Response;

/**
 * 清空当前线程当中的requestNo值，防止在使用线程池时导致下一次的请求当中的线程有原线程的requestNo值
 * @author YangKui
 *
 */
public class ResponseLogInterceptor implements ResponseInterceptor {
	Logger logger = LoggerFactory.getLogger(ResponseLogInterceptor.class);
	@Override
	public void intercept(Response response) throws InterceptorException {
		try {
			//MDC.put("RequestNo", ""); //该操作转入到SsoSecurityHandlerFilter类当中，防止filter类当中的日志无RequestNO
		} catch (Exception e) {
			logger.error("RequestNo sava to MDC error！", e);
		}

	}

}
