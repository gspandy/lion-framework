/*
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: CombinedThrowAdvice.java 9552 2013-2-1 下午1:29:21 WangLijun$
 */
package com.newtouch.lion.advice;

import java.lang.reflect.Method;

import org.springframework.aop.ThrowsAdvice;

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
public interface CombinedThrowAdvice extends ThrowsAdvice {
	
	/****
	 * @param paramMethod
	 * @param paramArrayOfObject
	 * @param paramObject
	 * @param paramException
	 */
	public void afterThrowing(Method paramMethod, Object[] paramArrayOfObject,
							  Object paramObject, Exception paramException) throws Throwable;
}
