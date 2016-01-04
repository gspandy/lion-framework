/*
 *  Copyright 2012, NEWTOUCH Co., Ltd.  All right reserved.
 *
 *  THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF NEWTOUCH CO., LTD.
 *  THE CONTENTS OF THIS FILE MAY NOT BE DISCLOSED
 *  TO THIRD PARTIES, COPIED OR DUPLICATED IN ANY FORM, IN WHOLE OR IN PART,
 *  WITHOUT THE PRIOR WRITTEN PERMISSION OF NEWTOUCH CO., LTD.
 */
package com.newtouch.trans.core.exception;

/**
 * 业务异常类
 * 
 * @author dongfeng.zhangs
 * @version 1.0
 */
public class TimeoutException extends RuntimeException {
	private static final long serialVersionUID = -4702239041166023687L;

	public TimeoutException() {
		super();
	}

	public TimeoutException(String message) {
		super(message);
	}

	public TimeoutException(Throwable cause) {
		super(cause);
	}

	public TimeoutException(String message, Throwable cause) {
		super(message, cause);
	}
}