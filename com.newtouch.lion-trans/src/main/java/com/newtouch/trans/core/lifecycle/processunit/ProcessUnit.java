/*
 * Copyright 2012, NEWTOUCH Co., Ltd. All right reserved.
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF NEWTOUCH CO., LTD.
 * THE CONTENTS OF THIS FILE MAY NOT BE DISCLOSED
 * TO THIRD PARTIES, COPIED OR DUPLICATED IN ANY FORM, IN WHOLE OR IN PART,
 * WITHOUT THE PRIOR WRITTEN PERMISSION OF NEWTOUCH CO., LTD.
 */
package com.newtouch.trans.core.lifecycle.processunit;

import com.newtouch.trans.core.model.TransContext;

/**
 * 处理单元接口
 * 
 * @author dongfeng.zhang
 * @version 1.0
 */
public interface ProcessUnit {
	// 交易代码
	public String getTransCode();

	// 排序，从小到大
	public int getOrder();
	/**
	 * 当前PU是否参加处理，需要返回为true才代表参与处理
	 * @param ctx
	 * @return
	 */
	public boolean isResponsibilityOwner(TransContext ctx);

	/**
	 * 执行处理
	 */
	public void execute(TransContext ctx);
	

}
