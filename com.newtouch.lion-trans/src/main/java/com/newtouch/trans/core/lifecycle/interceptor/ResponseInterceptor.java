/*
 * Copyright 2012, NEWTOUCH Co., Ltd. All right reserved.
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF NEWTOUCH CO., LTD.
 * THE CONTENTS OF THIS FILE MAY NOT BE DISCLOSED
 * TO THIRD PARTIES, COPIED OR DUPLICATED IN ANY FORM, IN WHOLE OR IN PART,
 * WITHOUT THE PRIOR WRITTEN PERMISSION OF NEWTOUCH CO., LTD.
 */
package com.newtouch.trans.core.lifecycle.interceptor;

import com.newtouch.trans.core.exception.InterceptorException;
import com.newtouch.trans.core.model.Response;

/**
 * 响应拦截器接口，intercept方法返回是否进行拦截
 * 
 * @author dongfeng.zhang
 * @version 1.0
 */
public interface ResponseInterceptor {
	/**
	 * 拦截方法，用于进行拦截实现，处理拦截请求,如果被拦截则抛出InterceptorException拦截异常
	 * 
	 * @param response
	 *            拦截响应包
	 * 
	 */
	public void intercept(Response response) throws InterceptorException;

}
