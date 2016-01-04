/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: BindError.java 9552 2014-3-25 上午11:04:15 WangLijun$
 */
package com.newtouch.lion.web.servlet.view.support;

import java.util.Map;

import com.newtouch.lion.web.servlet.view.support.errors.FieldValidateMessage;

/**
 * <p>
 * Title:消息绑定类
 * </p>
 * <p>
 * Description:消息绑定类，包含是否消息错误、是否有全局消息错误、全局消息错误对象、是否有字段错误消息及错误字段消息
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: NewTouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class BindMessage {

	public static final String ERRORS_MODEL_KEY = BindMessage.class.getName()+ "." + "errors";

	public static final String SUCCESS = BindMessage.class.getName() + "."+ "success";
	/** 提示信息 */
	private String message;
	/** 验证对象名称 */
	private String objectName;
	/** 是否有错误 */
	private boolean hasError;
	/** 全局错误 */
	private String[] errorMessage;
	/** 是否有全局错误 */
	private boolean hasGlobalError;
	/** 是否有字段验证错误 */
	private boolean hasFieldError;
	/** 字段验证错误信息 */
	private Map<String, FieldValidateMessage> fieldValidateMessages;

	/**
	 * default
	 * */
	public BindMessage() {
		super();
	}

	/**
	 * default
	 * */
	public BindMessage(String message) {
		super();
		this.message = message;
	}

	/***
	 * @param objectName
	 *            验证对象名称
	 * @param errorCodes
	 *            全局错误
	 * @param fieldValidateMessages
	 *            字段验证错误
	 */
	public BindMessage(String objectName, String[] errorMessage,
			Map<String, FieldValidateMessage> fieldValidateMessages) {
		super();
		this.objectName = objectName;
		this.errorMessage = errorMessage;
		this.fieldValidateMessages = fieldValidateMessages;
		this.initBindError();
	}

	private void initBindError() {
		// 判断是否有全局错误
		if (this.errorMessage == null || this.errorMessage.length == 0) {
			this.hasGlobalError = Boolean.FALSE;
		} else {
			this.hasGlobalError = Boolean.TRUE;
		}
		// 判断是否有字段错误
		if (this.fieldValidateMessages == null
				|| this.fieldValidateMessages.isEmpty()) {
			this.hasFieldError = Boolean.FALSE;
		} else {
			this.hasFieldError = Boolean.TRUE;
		}

		// 判断是否有错误
		if (this.hasGlobalError || this.hasFieldError) {
			this.hasError = Boolean.TRUE;
		} else {
			this.hasError = Boolean.FALSE;
		}
	}

	/***
	 * 
	 * @param objectName
	 *            验证对象名称
	 * @param errorMessage
	 *            全局错误
	 * @param hasGlobalError
	 *            是否有全局验证错误
	 * @param hasFieldError
	 *            是否有字段错误验证错误
	 * @param fieldErrorMessage
	 *            字段验证错误
	 */
	public BindMessage(String objectName, String[] errorMessage,
			boolean hasGlobalError, boolean hasFieldError,
			Map<String, FieldValidateMessage> fieldErrorMessage) {
		super();
		this.objectName = objectName;
		this.errorMessage = errorMessage;
		this.hasGlobalError = hasGlobalError;
		this.hasFieldError = hasFieldError;
		this.fieldValidateMessages = fieldErrorMessage;
	}

	/**
	 * @return the objectName
	 */
	public String getObjectName() {
		return objectName;
	}

	/**
	 * @param objectName
	 *            the objectName to set
	 */
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	/**
	 * @return the errorMessage
	 */
	public String[] getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage
	 *            the errorMessage to set
	 */
	public void setErrorMessage(String[] errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the hasGlobalError
	 */
	public boolean isHasGlobalError() {
		return hasGlobalError;
	}

	/**
	 * @param hasGlobalError
	 *            the hasGlobalError to set
	 */
	public void setHasGlobalError(boolean hasGlobalError) {
		this.hasGlobalError = hasGlobalError;
	}

	/**
	 * @return the hasFieldError
	 */
	public boolean isHasFieldError() {
		return hasFieldError;
	}

	/**
	 * @param hasFieldError
	 *            the hasFieldError to set
	 */
	public void setHasFieldError(boolean hasFieldError) {
		this.hasFieldError = hasFieldError;
	}

	/**
	 * @return the fieldValidateMessages
	 */
	public Map<String, FieldValidateMessage> getFieldValidateMessages() {
		return fieldValidateMessages;
	}

	/**
	 * @param fieldValidateMessages
	 *            the fieldValidateMessages to set
	 */
	public void setFieldValidateMessages(
			Map<String, FieldValidateMessage> fieldValidateMessages) {
		this.fieldValidateMessages = fieldValidateMessages;
	}

	/**
	 * @return the hasError
	 */
	public boolean isHasError() {
		return hasError;
	}

	/**
	 * @param hasError
	 *            the hasError to set
	 */
	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
