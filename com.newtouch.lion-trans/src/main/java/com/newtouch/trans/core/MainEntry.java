/*
 * Copyright 2012, NEWTOUCH Co., Ltd. All right reserved.
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF NEWTOUCH CO., LTD.
 * THE CONTENTS OF THIS FILE MAY NOT BE DISCLOSED
 * TO THIRD PARTIES, COPIED OR DUPLICATED IN ANY FORM, IN WHOLE OR IN PART,
 * WITHOUT THE PRIOR WRITTEN PERMISSION OF NEWTOUCH CO., LTD.
 */
package com.newtouch.trans.core;

import com.newtouch.trans.core.model.Request;
import com.newtouch.trans.core.model.Response;

/**
 * 请求入口服务
 * 
 * @author dongfeng.zhang
 * @version 1.0
 */
public interface MainEntry {
	/**
	 * 服务入口方法
	 * 
	 * @param request
	 *            请求对象
	 * 
	 * @return 响应对象
	 */
	public Response submit(Request request);
}
