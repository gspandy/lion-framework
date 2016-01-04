/*
 * Copyright 2012, NEWTOUCH Co., Ltd. All right reserved.
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF NEWTOUCH CO., LTD.
 * THE CONTENTS OF THIS FILE MAY NOT BE DISCLOSED
 * TO THIRD PARTIES, COPIED OR DUPLICATED IN ANY FORM, IN WHOLE OR IN PART,
 * WITHOUT THE PRIOR WRITTEN PERMISSION OF NEWTOUCH CO., LTD.
 */
package com.newtouch.trans.core.lifecycle.executor;

import com.newtouch.trans.core.model.TransContext;


/**
 * 交易调用入口
 * 
 * @author dongfeng.zhang
 * @author YangKui
 * 
 */
public interface TransactionExecutor {
	/**
	 * 处理请求
	 * 
	 * @param transContext
	 *            交易上下文
	 */
	public void doTransaction(TransContext transContext);
	
}
