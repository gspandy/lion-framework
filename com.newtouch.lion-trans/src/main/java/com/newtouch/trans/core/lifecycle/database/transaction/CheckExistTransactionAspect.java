package com.newtouch.trans.core.lifecycle.database.transaction;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionSynchronizationManager;


/**
 * 事务拦截器
 * 
 * @author dongfeng.zhang
 * 
 */
@Aspect
public class CheckExistTransactionAspect {
	private Logger logger = LoggerFactory.getLogger(CheckExistTransactionAspect.class);

	/**
	 * 拦截方法记录日志，如果被包含在事务中则输出日志
	 */
	@Before("execution(* com.cpic.cmp.server..integration..*.*(..))")
	public void before(JoinPoint joinPoint) {
		boolean isActualTransactionActive = TransactionSynchronizationManager.isActualTransactionActive();
		if (isActualTransactionActive) {
			boolean isCurrentTransactionReadOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
			String currentTransactionName = TransactionSynchronizationManager.getCurrentTransactionName();
			MethodSignature signature = (MethodSignature) joinPoint.getSignature();
			Method method = signature.getMethod();

			if (Configuration.hasTransactionThrowException()) {
				throw new RuntimeException("current method=" + method.toGenericString() + " be included in the transaction.");
			} else {
				logger.warn("!!current method={} in transaction={},readOnly={}!!", new Object[]{method.toGenericString(), currentTransactionName,
						isCurrentTransactionReadOnly});
			}
		}
	}

}
