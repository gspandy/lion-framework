/*
 * Copyright (c)  2013, Newtouch
 * All rights reserved. 
 *
 * $id: MethodTimerData.java 9552 2013-4-7 上午10:37:07 WangLijun$
 */
package com.newtouch.lion.advice;

import java.io.Serializable;
import java.lang.reflect.Method;

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
public class MethodTimerData implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1955667777770286157L;
	
	private Method callMethod = null;

	private Object[] callParameters = null;

	private long callTime = 0L;

	/**
	 * @return the callMethod
	 */
	public Method getCallMethod() {
		return callMethod;
	}

	/**
	 * @param callMethod the callMethod to set
	 */
	public void setCallMethod(Method callMethod) {
		this.callMethod = callMethod;
	}

	/**
	 * @return the callParameters
	 */
	public Object[] getCallParameters() {
		return callParameters;
	}

	/**
	 * @param callParameters the callParameters to set
	 */
	public void setCallParameters(Object[] callParameters) {
		this.callParameters = callParameters;
	}

	/**
	 * @return the callTime
	 */
	public long getCallTime() {
		return callTime;
	}

	/**
	 * @param callTime the callTime to set
	 */
	public void setCallTime(long callTime) {
		this.callTime = callTime;
	}
}
