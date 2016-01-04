/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: FieldErrorsMessage.java 9552 2014-3-24 下午10:31:12 WangLijun$
 */
package com.newtouch.lion.web.servlet.view.support.errors;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Title:字段验证消息
 * </p>
 * <p>
 * Description:字段验证消息，包括字段名称、字段值、字段验证错误Code、字段验证错误消息
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
public class FieldValidateMessage {

	private String fieldName;

	private Object fieldValue;

	private List<String> errorCodes = new ArrayList<String>();

	private List<String> errorMessages = new ArrayList<String>();

	public FieldValidateMessage() {
		super();
	};

	public FieldValidateMessage(String fieldName, Object fieldValue,
			String errorCode, String errorMessage) {
		super();
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
		this.errorCodes.add(errorCode);
		this.errorMessages.add(errorMessage);
	}

	/**
	 * @return the fieldValue
	 */
	public Object getFieldValue() {
		return fieldValue;
	}

	/**
	 * @param fieldValue
	 *            the fieldValue to set
	 */
	public void setFieldValue(Object fieldValue) {
		this.fieldValue = fieldValue;
	}

	/**
	 * @return the fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * @param fieldName
	 *            the fieldName to set
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * @return the errorCodes
	 */
	public List<String> getErrorCodes() {
		return errorCodes;
	}

	/**
	 * @param errorCodes
	 *            the errorCodes to set
	 */
	public void setErrorCodes(List<String> errorCodes) {
		this.errorCodes = errorCodes;
	}

	/**
	 * @return the errorMessages
	 */
	public List<String> getErrorMessages() {
		return errorMessages;
	}

	public void addErrorCode(String errorCode) {
		this.errorCodes.add(errorCode);
	}

	/**
	 * @param errorMessages
	 *            the errorMessages to set
	 */
	public void setErrorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}

	public void addErrorMessage(String errorMessage) {
		this.errorMessages.add(errorMessage);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FieldErrorMessage [fieldName=" + fieldName + ", fieldValue="
				+ fieldValue + ", errorCode=" + this.errorCodes.toString()
				+ ", errorMessages=" + this.errorMessages.toString() + "]";
	}

}
