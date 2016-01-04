/*
 * Copyright 2012, NEWTOUCH Co., Ltd. All right reserved.
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF NEWTOUCH CO., LTD.
 * THE CONTENTS OF THIS FILE MAY NOT BE DISCLOSED
 * TO THIRD PARTIES, COPIED OR DUPLICATED IN ANY FORM, IN WHOLE OR IN PART,
 * WITHOUT THE PRIOR WRITTEN PERMISSION OF NEWTOUCH CO., LTD.
 */
package com.newtouch.trans.core.provider.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.newtouch.trans.core.exception.TimeoutException;
import com.newtouch.trans.core.provider.ProviderInterface;

/**
 * 处理超时控制工具类,用来控制某个线程是否执行超时
 * 
 * @author dongfeng.zhang
 */
public class TimeoutControlProviderImpl extends Thread implements ProviderInterface {
	Logger logger = LoggerFactory.getLogger(TimeoutControlProviderImpl.class);
	private long timeOut;
	private boolean isTimeout;
	private boolean isCanceled;

	public TimeoutControlProviderImpl(long timeOut, String requestNo) {
		super();
		this.timeOut = timeOut;
		this.setName("TimeoutControl-" + requestNo);
		this.setDaemon(true);
	}

	public synchronized void cancel() {
		this.isCanceled = true;

	}

	public synchronized void run() {
		try {
			wait(timeOut);
			if (!isCanceled) {
				this.isTimeout = true;
				logger.error("process request[" + "TimeoutControl-" + "] timeout.");
				throw new TimeoutException("process request[" + "TimeoutControl-" + "] timeout.");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean isTimeout() {
		return isTimeout;
	}

}
