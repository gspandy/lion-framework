/**
 * 
 */
package com.newtouch.lion.exception;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wanglijun
 * 
 */
public abstract class MethodExceptionAdvice implements MethodInterceptor {
	
	protected Logger logger = LoggerFactory.getLogger(super.getClass());

	public Object invoke(MethodInvocation invocation) throws Throwable {
		try {
			return invocation.proceed();
		} catch (Exception ex) {
			logger.error(ex.getMessage(),ex);
			return handleException(ex, invocation.getMethod().getReturnType());
		}
		
	}

	protected abstract Object handleException(Throwable paramThrowable,
			Class<?> paramClass) throws Throwable;
}
