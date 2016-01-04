/*
 * Copyright 2013, NEWTOUCH Co., Ltd. All right reserved.
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF NEWTOUCH CO., LTD.
 * THE CONTENTS OF THIS FILE MAY NOT BE DISCLOSED
 * TO THIRD PARTIES, COPIED OR DUPLICATED IN ANY FORM, IN WHOLE OR IN PART,
 * WITHOUT THE PRIOR WRITTEN PERMISSION OF NEWTOUCH CO., LTD.
 */
package com.newtouch.trans.core.exception;

/**
 * 检查异常类
 * 
 * @author dongfeng.zhang
 * @version 1.0
 */
public class CheckException extends TransException {
	private static final long serialVersionUID = -306209105165375774L;

	/**
	 * 构造方法
	 * 
	 * @param message
	 *            异常消息
	 */
	public CheckException(String code,String message) {
		super(code,message);
	}
}
