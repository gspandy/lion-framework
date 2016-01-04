/*
 * Copyright 2012, NEWTOUCH Co., Ltd. All right reserved.
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF NEWTOUCH CO., LTD.
 * THE CONTENTS OF THIS FILE MAY NOT BE DISCLOSED
 * TO THIRD PARTIES, COPIED OR DUPLICATED IN ANY FORM, IN WHOLE OR IN PART,
 * WITHOUT THE PRIOR WRITTEN PERMISSION OF NEWTOUCH CO., LTD.
 */
package com.newtouch.trans.core.model;

import java.util.HashMap;
import java.util.Map;

import com.newtouch.trans.core.exception.TransException;
import com.newtouch.trans.core.lifecycle.processunit.ProcessUnit;

/**
 * 交易上下文
 * 
 * @author dongfeng.zhang
 * @version 1.0
 */
public class TransContext implements java.io.Serializable {
	private static final long serialVersionUID = 3847889406639504739L;
	private ProcessUnit[] transProcessUnits;
	private TransException exception;
	private Map<String, Object> context = null;
	private Request request;
	private Response response;
	

	/**
	 * Creates a new TransContext object. 构造方法
	 * 
	 * @param request
	 *            请求包
	 * @param transDef
	 *            本交易定义
	 * @param applicationProperties
	 *            应用配置属性
	 * @param transProcessUnits
	 *            本交易处理单元
	 */
	public TransContext(Map<String, Object> requestMap, ProcessUnit[] transProcessUnits) {
		super();
		context=new HashMap<String,Object>();
		context.putAll(requestMap);
		this.transProcessUnits = transProcessUnits;
	}

	/**
	 * Creates a new TransContext object. 构造方法
	 * 
	 * @param request
	 *            请求包
	 * @param transDef
	 *            本交易定义
	 * @param applicationProperties
	 *            应用配置属性
	 * @param transProcessUnits
	 *            本交易处理单元
	 */
	public TransContext(Request request, ProcessUnit[] transProcessUnits) {
		super();
		context=new HashMap<String,Object>();
		this.request = request;
		this.transProcessUnits = transProcessUnits;
	}
	
	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public Response getResponse() {
		if(this.response==null){
			this.response = new Response(ApplicationErrorCode.SUCCESS,"处理完成",Response.STATUS_SUCCESS);
		}
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	/**
	 * 获取执行期异常
	 * 
	 * @return 异常信息
	 */
	public TransException getException() {
		return exception;
	}

	/**
	 * 异常信息
	 * 
	 * @param exception
	 *            异常
	 */
	public void setException(TransException exception) {
		this.exception = exception;
	}

	/**
	 * 获取本交易需要处理的执行单元
	 * 
	 * @return 执行单元
	 */
	public ProcessUnit[] getTransProcessUnits() {
		return transProcessUnits;
	}

	public Object get(String key) {
		Object object = context.get(key);
		return object;
	}

	public void put(String key, Object value) {
		// if (this.context.containsKey(key))
		// throw new TransException(ApplicationErrorCode.APP_CONTEXT_EXCEPTION, "context key:" + key +
		// ",not allowed to overwrite!");
		// if (null == value)
		// throw new TransException(ApplicationErrorCode.APP_CONTEXT_EXCEPTION, "context value:" + value +
		// ", do not allow null!");
		this.context.put(key, value);
	}

	public void remove(String key) {
		this.context.remove(key);
	}

	public Map<String, Object> getContext() {
		return context;
	}

	
}
