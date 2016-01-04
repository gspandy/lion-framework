/*
 * Copyright 2012, NEWTOUCH Co., Ltd. All right reserved.
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF NEWTOUCH CO., LTD.
 * THE CONTENTS OF THIS FILE MAY NOT BE DISCLOSED
 * TO THIRD PARTIES, COPIED OR DUPLICATED IN ANY FORM, IN WHOLE OR IN PART,
 * WITHOUT THE PRIOR WRITTEN PERMISSION OF NEWTOUCH CO., LTD.
 */
package com.newtouch.trans.core.lifecycle.interceptor;

import com.newtouch.trans.core.exception.InterceptorException;
import com.newtouch.trans.core.model.Request;

/**
 * 请求拦截器，用来拦截请求包
 * 
 * @author dongfeng.zhang
 * @version 1.0
 */
public interface RequestInterceptor {
	/**
	 * 拦截方法，如果符合拦截条件则抛出InterceptorException异常
	 * 
	 * @param request
	 *            请求数据包
	 */
	public void intercept(Request request) throws InterceptorException;

}
