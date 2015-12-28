/*
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: MethodTimerAdvice.java 9552 2013-4-7 上午10:33:28 WangLijun$
 */
package com.newtouch.lion.advice;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

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
public class MethodTimerAdvice implements MethodBeforeAdvice,
		AfterReturningAdvice, CombinedThrowAdvice {

	private final Logger log = LoggerFactory.getLogger(super.getClass());

	private static final int WARNING_LIMIT = 3000;

	private ThreadLocal<Map<String, List<MethodTimerData>>> methodInfo = new ThreadLocal<Map<String, List<MethodTimerData>>>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lion.framework.advice.CombinedThrowAdvice#afterThrowing(java.lang
	 * .reflect.Method, java.lang.Object[], java.lang.Object,
	 * java.lang.Exception)
	 */
	@Override
	public void afterThrowing(Method  method, Object[] args,
			Object target, Exception paramException) throws Throwable {
		callReturn(method, args, target);

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
		callReturn(method, args, target);

	}

	private void callReturn(Method method, Object[] args, Object target) {
		String hashKey = target.getClass().getSimpleName() + "."+ method.getName();
		long returnTime = Calendar.getInstance().getTimeInMillis();
		this.log.debug("MethodTimerAdvice:  after calling method " + hashKey + " at " + returnTime + ".");
		if (this.methodInfo.get() == null)
			return;
		if (!(this.methodInfo.get().containsKey(hashKey)))
			return;

		List<MethodTimerData> arrayList = this.methodInfo.get().get(hashKey);
		
		int arraySize = arrayList.size() - 1;
		if (arraySize < 0) {
			this.methodInfo.get().remove(hashKey);
		}

		MethodTimerData mtd =arrayList.remove(arraySize);
		long costTime = returnTime - mtd.getCallTime();
		this.log.info("MethodTimerAdvice:  method " + hashKey + " cost "+ costTime + " miliseconds.");

		if (costTime >=WARNING_LIMIT) {
			this.log.warn("Warning!!!!  MethodTimerAdvice:  method " + hashKey
					+ " cost " + costTime + " miliseconds.");
			this.log.warn("Warning!!!!  exceeded time cost limits. Please consider method optimization. ");
			this.log.warn("Warning!!!!  input parameters: ");
			describeParameter(args);
		}

		arraySize = arrayList.size() - 1;
		if (arraySize < 0)
		this.methodInfo.get().remove(hashKey);
	}

	private void describeParameter(Object[] args) {
		try {
			for (Object arg : args)
				if (arg == null) {
					log.warn("null");
				} else if (arg.getClass().isArray()) {
					for (int i = 0; i < Array.getLength(arg); ++i){
						log.warn(null, BeanUtils.describe(Array.get(arg, i)));
					}
				} else{
					    log.warn(null, BeanUtils.describe(arg));
				}
		} catch (Throwable t) {
			t.printStackTrace();
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
		if (this.methodInfo.get() == null) {
			Map<String, List<MethodTimerData>> hashMap = new HashMap<String, List<MethodTimerData>>();
			this.methodInfo.set(hashMap);
		}

		String hashKey = target.getClass().getSimpleName() + "."
				+ method.getName();
		if (!((this.methodInfo.get()).containsKey(hashKey))) {
			List<MethodTimerData> arrayList = new ArrayList<MethodTimerData>();
			this.methodInfo.get().put(hashKey, arrayList);
		}
		MethodTimerData mtd = new MethodTimerData();
		
		mtd.setCallMethod(method);
		
		mtd.setCallParameters(args);
		
		mtd.setCallTime(Calendar.getInstance().getTimeInMillis());
		
		this.methodInfo.get().get(hashKey).add(mtd);

		log.info("MethodTimerAdvice:  before calling method " + hashKey+ " at " + mtd.getCallTime() + ".");
	}

}
