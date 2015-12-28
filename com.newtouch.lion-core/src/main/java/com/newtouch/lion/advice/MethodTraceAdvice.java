/*
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: MethodTraceAdvice.java 9552 2013-4-7 上午11:01:56 WangLijun$
 */
package com.newtouch.lion.advice;

import java.lang.reflect.Method;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.StaleObjectStateException;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.TransactionException;

import com.newtouch.lion.common.resource.MessageResource;
import com.newtouch.lion.exception.SystemException;



/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class MethodTraceAdvice implements MethodBeforeAdvice,
		AfterReturningAdvice, CombinedThrowAdvice {

	private final Logger log = LoggerFactory.getLogger(super.getClass());

	public static final String ERROR_MESSAGE_CODE_PREFIX = "";

	@Autowired
	@Qualifier("sysMessages")
	private MessageResource msg = null;

	private boolean enabledMethodTrace = false;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.advice.CombinedThrowAdvice#afterThrowing(java.lang
	 * .reflect.Method, java.lang.Object[], java.lang.Object,
	 * java.lang.Exception)
	 */
	@Override
	public void afterThrowing(Method method, Object[] objects, Object target,
			Exception e) throws Throwable {
		this.log.error(this.msg.getMessage("SE0013", new Object[] {target.getClass().getName(), method.getName() }), e);
		if (e instanceof DataAccessException)
			e = translateDataAccessException((DataAccessException) e);
		else if (e instanceof TransactionException)
			e = new SystemException("SE0005");
		else if (e instanceof HibernateException) {
			e = translateHibernateException(e);
		}

		this.log.error(e.getMessage(), e);

		if (e instanceof SystemException) {
			SystemException systemException=(SystemException) e;
			String code = systemException.getCode();
			String message;
			if(systemException.getParams()!=null){
				Object[] params=systemException.getParams();
				message = this.msg.getMessage(code,params);
			}else{
				message = this.msg.getMessage(code);
			}
			e = new SystemException(code,message);
		}
		throw e;

	}

	private SystemException translateHibernateException(Exception e) {
		SystemException se = new SystemException("SE0002");

		if (e instanceof ObjectNotFoundException) {
			se = new SystemException("SE0003",new String[]{((ObjectNotFoundException) e) .getEntityName()});
		}

		return se;
	}

	private SystemException translateDataAccessException(DataAccessException e) {
		Throwable cause = e.getCause();
		SystemException se = new SystemException("SE0002");
		if (cause instanceof ConstraintViolationException) {
			this.log.info("Exception with Excuting SQL:"+ ((ConstraintViolationException) cause).getSQL());
			se = new SystemException("SE0004");
		} else if (cause instanceof StaleObjectStateException) {
			this.log.info("Exception with Excuting SQL:"+ ((StaleObjectStateException) cause).getCause());
			se = new SystemException("SE0014");
		} else if (cause instanceof JDBCException) {
			this.log.info("Exception with Excuting SQL:" + ((JDBCException) cause).getSQL());
			se = new SystemException("SE0002");
		}
		return se;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.aop.AfterReturningAdvice#afterReturning(java.lang
	 * .Object, java.lang.reflect.Method, java.lang.Object[], java.lang.Object)
	 */
	@Override
	public void afterReturning(Object returnValue, Method method,
			Object[] args, Object target) throws Throwable {
		boolean traceEnabled = this.log.isDebugEnabled();
		if ((isEnabledMethodTrace()) && (traceEnabled)) {
			this.log.info(this.msg.getMessage("SI0002", new Object[] { target.getClass(), method.getName() }));
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.aop.MethodBeforeAdvice#before(java.lang.reflect.Method
	 * , java.lang.Object[], java.lang.Object)
	 */
	@Override
	public void before(Method method, Object[] args, Object target)
			throws Throwable {
		boolean traceEnabled = this.log.isDebugEnabled();
		if ((isEnabledMethodTrace()) && (traceEnabled)) {
			this.log.info(this.msg.getMessage("SI0001",
					new Object[] { target.getClass(), method.getName() }));
		}

	}

	public boolean isEnabledMethodTrace() {
		return this.enabledMethodTrace;
	}

	public void setEnabledMethodTrace(boolean enabled) {
		this.enabledMethodTrace = enabled;
	}

}
