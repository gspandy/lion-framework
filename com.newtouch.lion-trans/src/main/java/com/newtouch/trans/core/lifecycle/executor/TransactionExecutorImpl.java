/*
 * Copyright 2012, NEWTOUCH Co., Ltd. All right reserved.
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF NEWTOUCH CO., LTD.
 * THE CONTENTS OF THIS FILE MAY NOT BE DISCLOSED
 * TO THIRD PARTIES, COPIED OR DUPLICATED IN ANY FORM, IN WHOLE OR IN PART,
 * WITHOUT THE PRIOR WRITTEN PERMISSION OF NEWTOUCH CO., LTD.
 */
package com.newtouch.trans.core.lifecycle.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.newtouch.trans.core.exception.BizException;
import com.newtouch.trans.core.exception.CheckException;
import com.newtouch.trans.core.exception.TransException;
import com.newtouch.trans.core.lifecycle.processunit.ProcessUnit;
import com.newtouch.trans.core.model.ApplicationErrorCode;
import com.newtouch.trans.core.model.TransContext;

/**
 * 请求主控制/执行单元，该模型用于处理交易请求
 * 
 * @author dongfeng.zhang
 */
@Component
public class TransactionExecutorImpl implements TransactionExecutor {
	private Logger logger = LoggerFactory.getLogger(TransactionExecutorImpl.class);
	
	/*
	 * (non-Javadoc)
	 * @see
	 * com.cpic.eup.transaction.core.lifecycle.executor.TranscationExecutor#doTranscation(com.cpic.eup.transaction.core
	 * .model.TransContext)
	 */
	public void doTransaction(TransContext transContext) {
		ProcessUnit[] processUnits = transContext.getTransProcessUnits();
		String transCode =transContext.getRequest().getTransCode();
		for (ProcessUnit processUnit : processUnits) {
			if (!processUnit.isResponsibilityOwner(transContext)) {
				continue;
			}
			try {
				processUnit.execute(transContext);
			} catch (CheckException ce) {
				logger.info("check Exception transCode:{},code:{},message:{}",transCode,ce.getCode(),ce.getMessage());
				TransException te = new TransException(ce.getCode(), ce.getMessage());
				transContext.setException(te);
				break;
			} catch (TransException te) {
				logger.error("transcation execute exception!transCode:{}",transCode, te);
				transContext.setException(te);
				break;
			} catch (BizException be) {
				logger.error("transcation execute exception!transCode:{}",transCode, be);
				TransException te = new TransException(be.getCode(), be.getMessage());
				transContext.setException(te);
				break;
			}catch (Exception ex) {
				logger.error("transcation execute exception!transCode:{}",transCode, ex);
				TransException te = new TransException(ApplicationErrorCode.EXCEPTION, ex.getMessage());
				transContext.setException(te);
				break;
			}
		}
	}
	
}
