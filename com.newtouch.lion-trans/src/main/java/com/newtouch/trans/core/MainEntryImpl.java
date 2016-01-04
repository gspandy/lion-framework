/*
 * Copyright 2012, NEWTOUCH Co., Ltd. All right reserved.
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF NEWTOUCH CO., LTD.
 * THE CONTENTS OF THIS FILE MAY NOT BE DISCLOSED
 * TO THIRD PARTIES, COPIED OR DUPLICATED IN ANY FORM, IN WHOLE OR IN PART,
 * WITHOUT THE PRIOR WRITTEN PERMISSION OF NEWTOUCH CO., LTD.
 */
package com.newtouch.trans.core;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.newtouch.trans.core.exception.InterceptorException;
import com.newtouch.trans.core.exception.TransException;
import com.newtouch.trans.core.helper.CheckUtil;
import com.newtouch.trans.core.lifecycle.ApplicationProperties;
import com.newtouch.trans.core.lifecycle.executor.TransactionExecutor;
import com.newtouch.trans.core.lifecycle.factory.TransContextFactory;
import com.newtouch.trans.core.lifecycle.interceptor.RequestInterceptor;
import com.newtouch.trans.core.lifecycle.interceptor.ResponseInterceptor;
import com.newtouch.trans.core.model.ApplicationErrorCode;
import com.newtouch.trans.core.model.Request;
import com.newtouch.trans.core.model.Response;
import com.newtouch.trans.core.model.TransContext;
import com.newtouch.trans.core.provider.ProviderInterface;
import com.newtouch.trans.core.provider.impl.TimeoutControlProviderImpl;

/**
 * 请求入口实现类
 * @author dongfeng.zhang
 * @author 阳葵
 * @version 1.0
 */
@Component(value="mainEntry")
public class MainEntryImpl implements MainEntry {
	private Logger logger = LoggerFactory.getLogger(MainEntryImpl.class);
	private RequestInterceptor[] requestInterceptors = new RequestInterceptor[0];
	private ResponseInterceptor[] responseInterceptors = new ResponseInterceptor[0];
	@Autowired
	private TransContextFactory transContextFactory;
	@Autowired
	private TransactionExecutor transactionExecutor;
	private ProviderInterface timeoutControl = null;

	/**
	 * 请求入口方法，对请求先进行拦截器序列执行，然后调用交易执行器进行交易处理，最后执行响应拦截器序列，
	 * 请拦截器负责执行请求的过滤和拦截功能，对请求数据做交易前的预处理，响应拦截器序列做响应预处理
	 * 
	 * @param request
	 *            请求数据包
	 * 
	 * @return 响应数据包
	 */
	public Response submit(Request request) {
		Response response;
		if (request == null || CheckUtil.emptyStr(request.getTransCode())) {
			response = buildResponse(request, ApplicationErrorCode.REQUEST_IS_NULL, "报文格式错误，请求对象为空或者或交易代码为空！");
			logger.error("REQUEST is null or REQUEST.TRANSNO is empty！");
			return response;
		}
		String timeout = ApplicationProperties.getProperty(ApplicationProperties.REQUEST_TIMEOUT_PREFIX + "."
				+ request.getTransCode());
		if (timeout == null) {
			timeout = ApplicationProperties.getProperty(ApplicationProperties.REQUEST_TIMEOUT_PREFIX);
		}
		if (timeout != null) {
			timeoutControl = new TimeoutControlProviderImpl(123l, request.getRequestNo());
			((TimeoutControlProviderImpl) timeoutControl).start();
			response = service(request);
			((TimeoutControlProviderImpl) timeoutControl).cancel();
			notifyAll();
		} else {
			response = service(request);
		}
		return response;
	}

	private Response service(Request request) {
		Response response = null;

		request.setArrivalTime(new Date());

		// 如果请求被拦截，则返回Response给用户,否则获取request进行下一个拦截处理
		for (RequestInterceptor reqInterceptor : requestInterceptors) {
			try {
				reqInterceptor.intercept(request);
			} catch (InterceptorException ie) {
				// 被拦后生成响应包，跳过交易处理环节进入响应处理环节
				response = buildResponse(request, ie.getCode(), ie.getMessage());
				break;
			} catch (Exception e) {
				response = buildResponse(request, ApplicationErrorCode.EXCEPTION, "系统异常：请求处理异常！");
				logger.error("Request:{}", JSON.toJSON(request));
				logger.error("SystemError:RequestError!", e);
				break;
			}
		}
		if (response == null) {
			TransContext transContext = transContextFactory.createTransContext(request);
			transactionExecutor.doTransaction(transContext);
			TransException exception = transContext.getException();
			// 交易环节产生异常信息，则直接返回错误给用户
			if (exception != null) {
				response = buildResponse(request, exception.getCode(), exception.getMessage());
			} else {
				response = transContext.getResponse();
				if (response == null) {
					response = buildResponse(request, ApplicationErrorCode.RESPONSE_IS_NULL, "系统异常：返回对象为空！");
				} else {
					if (response.getResponseBody() == null) {
						response = buildResponse(request, ApplicationErrorCode.RESPONSE_BODY_IS_NULL, "系统异常，返回对象主体为空！");
					}
				}
			}

		}

		// 设置处理耗时
		response.setCostTime((new Date()).getTime() - request.getArrivalTime().getTime());

		// 如果响应被拦截，则返回Response给用户,否则获取处理后的response进行下一个拦截处理
		for (ResponseInterceptor respInterceptor : responseInterceptors) {
			try {
				respInterceptor.intercept(response);
			} catch (InterceptorException ie) {
				// 被拦截直接返回
				response = buildResponse(request, ie.getCode(), ie.getMessage());
				break;
			} catch (Exception e) {
				response = buildResponse(request, ApplicationErrorCode.EXCEPTION, "系统异常：返回对象处理异常！");
				logger.error("Response:{}", JSON.toJSON(request));
				logger.error("SystemError:ResponseError!", e);
				break;
			}
		}

		return response;
	}

	/*
	 * 异常信息构件响应报文
	 */
	private Response buildResponse(Request request, String errCode, String errMsg) {
		Response response = new Response();
		response.setTransCode(request.getTransCode());
		response.setErrorCode(errCode);
		response.setErrorMsg(errMsg);
		response.setStatus(Response.STATUS_ERROR);
		return response;
	}

	public void setRequestInterceptors(RequestInterceptor[] requestInterceptors) {
		this.requestInterceptors = requestInterceptors;
	}

	public void setResponseInterceptors(ResponseInterceptor[] responseInterceptors) {
		this.responseInterceptors = responseInterceptors;
	}

	public void setTransactionExecutor(TransactionExecutor transactionExecutor) {
		this.transactionExecutor = transactionExecutor;
	}

	public void setTransContextFactory(TransContextFactory transContextFactory) {
		this.transContextFactory = transContextFactory;
	}

}
