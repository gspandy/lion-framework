/*
 * Copyright 2013, NEWTOUCH Co., Ltd. All right reserved.
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF NEWTOUCH CO., LTD.
 * THE CONTENTS OF THIS FILE MAY NOT BE DISCLOSED
 * TO THIRD PARTIES, COPIED OR DUPLICATED IN ANY FORM, IN WHOLE OR IN PART,
 * WITHOUT THE PRIOR WRITTEN PERMISSION OF NEWTOUCH CO., LTD.
 */
package com.newtouch.trans.core.helper;

import java.text.MessageFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.newtouch.trans.core.exception.CheckException;
import com.newtouch.trans.core.model.ApplicationErrorCode;

/**
 * Check ,检查对象是否符合要求，如果不符合，就抛出异常，可自定义异常类型
 * 
 * @author dongfeng.zhang
 * @version 1.0
 */
public class Check {
	private static Logger logger = LoggerFactory.getLogger(Check.class);

	/**
	 * XXX DOCUMENT ME!
	 * 
	 * @param text
	 *            XXX DOCUMENT ME!
	 * @param message
	 *            XXX DOCUMENT ME!
	 */
	public static void emptyStr(String text, String message, Object... values) {
		boolean need = CheckUtil.emptyStr(text);
		needThrow(need, message, values);
	}

	public static void emptyStr(String text, RuntimeException exception) {
		boolean need = CheckUtil.emptyStr(text);
		needThrow(need, exception);
	}

	public static void emptyList(List<Object> list, String message, Object... values) {
		boolean need = CheckUtil.emptyList(list);
		needThrow(need, message, values);
	}

	public static void emptyList(List<Object> list, RuntimeException exception) {
		boolean need = CheckUtil.emptyList(list);
		needThrow(need, exception);
	}

	public static void hasText(String text, String message, Object... values) {
		boolean need = CheckUtil.hasText(text);
		needThrow(need, message, values);
	}

	public static void hasText(String text, RuntimeException exception) {
		boolean need = CheckUtil.hasText(text);
		needThrow(need, exception);
	}

	public static void isNumber(String text, String message, Object... values) {
		boolean need = CheckUtil.isNumber(text);
		needThrow(need, message, values);
	}

	public static void isNumber(String text, RuntimeException exception) {
		boolean need = CheckUtil.isNumber(text);
		needThrow(need, exception);
	}

	public static void isInteger(String text, String message, Object... values) {
		boolean need = CheckUtil.isInteger(text);
		needThrow(need, message, values);
	}

	public static void isInteger(String text, RuntimeException exception) {
		boolean need = CheckUtil.isInteger(text);
		needThrow(need, exception);
	}

	public static void isFloat(String text, String message, Object... values) {
		boolean need = CheckUtil.isFloat(text);
		needThrow(need, message, values);
	}

	public static void isFloat(String text, RuntimeException exception) {
		boolean need = CheckUtil.isFloat(text);
		needThrow(need, exception);
	}

	public static void isMoblileNum(String text, String message, Object... values) {
		boolean need = CheckUtil.isMoblileNum(text);
		needThrow(need, message, values);
	}

	public static void isMoblileNum(String text, RuntimeException exception) {
		boolean need = CheckUtil.isMoblileNum(text);
		needThrow(need, exception);
	}

	public static void isEmail(String text, String message, Object... values) {
		boolean need = CheckUtil.isEmail(text);
		needThrow(need, message, values);
	}

	public static void isEmail(String text, RuntimeException exception) {
		boolean need = CheckUtil.isEmail(text);
		needThrow(need, exception);
	}

	// 邮编
	public static void isZip(String text, String message, Object... values) {
		boolean need = CheckUtil.isZip(text);
		needThrow(need, message, values);
	} // 邮编

	public static void isZip(String text, RuntimeException exception) {
		boolean need = CheckUtil.isZip(text);
		needThrow(need, exception);
	}

	// 身份证
	public static void isICNum(String text, String message, Object... values) {
		boolean need = CheckUtil.isICNum(text);
		needThrow(need, message, values);
	} // 身份证

	public static void isICNum(String text, RuntimeException exception) {
		boolean need = CheckUtil.isICNum(text);
		needThrow(need, exception);
	}

	// 年龄范围，age，min，max
	public static void isAgeRange(int age, int min, int max, String message, Object... values) {
		boolean need = CheckUtil.isAgeRange(age, min, max);
		needThrow(need, message, values);
	} // 年龄范围，age，min，max

	public static void isAgeRange(int age, int min, int max, RuntimeException exception) {
		boolean need = CheckUtil.isAgeRange(age, min, max);
		needThrow(need, exception);
	}

	// 含有中文
	public static void hasChinese(String text, String message, Object... values) {
		boolean need = CheckUtil.hasChinese(text);
		needThrow(need, message, values);
	} // 含有中文

	public static void hasChinese(String text, RuntimeException exception) {
		boolean need = CheckUtil.hasChinese(text);
		needThrow(need, exception);
	}

	// 全中文构成
	public static void isAllChinese(String text, String message, Object... values) {
		boolean need = CheckUtil.isAllChinese(text);
		needThrow(need, message, values);
	}

	// 全中文构成
	public static void isAllChinese(String text, RuntimeException exception) {
		boolean need = CheckUtil.isAllChinese(text);
		needThrow(need, exception);
	}

	public static void isTrue(boolean condition, String message, Object... values) {
		boolean need = CheckUtil.isTrue(condition);
		needThrow(need, message, values);
	}

	public static void isTrue(boolean condition, RuntimeException exception) {
		boolean need = CheckUtil.isTrue(condition);
		needThrow(need, exception);
	}

	public static void isFalse(boolean condition, String message, Object... values) {
		boolean need = CheckUtil.isFalse(condition);
		needThrow(need, message, values);
	}

	public static void isFalse(boolean condition, RuntimeException exception) {
		boolean need = CheckUtil.isFalse(condition);
		needThrow(need, exception);
	}

	public static void isEquals(Object expected, Object actual, String message, Object... values) {
		boolean need = CheckUtil.isEquals(expected, actual);
		needThrow(need, message, values);
	}

	public static void isEquals(Object expected, Object actual, RuntimeException exception) {
		boolean need = CheckUtil.isEquals(expected, actual);
		needThrow(need, exception);
	}

	public static void isEquals(double expected, double actual, double delta, String message, Object... values) {
		boolean need = CheckUtil.isEquals(expected, actual, delta);
		needThrow(need, message, values);
	}

	public static void isEquals(double expected, double actual, double delta, RuntimeException exception) {
		boolean need = CheckUtil.isEquals(expected, actual, delta);
		needThrow(need, exception);
	}

	public static void isEquals(float expected, float actual, float delta, String message, Object... values) {
		boolean need = CheckUtil.isEquals(expected, actual, delta);
		needThrow(need, message, values);
	}

	public static void isEquals(float expected, float actual, float delta, RuntimeException exception) {
		boolean need = CheckUtil.isEquals(expected, actual, delta);
		needThrow(need, exception);
	}

	public static void isEquals(long expected, long actual, String message, Object... values) {
		boolean need = CheckUtil.isEquals(expected, actual);
		needThrow(need, message, values);
	}

	public static void isEquals(long expected, long actual, RuntimeException exception) {
		boolean need = CheckUtil.isEquals(expected, actual);
		needThrow(need, exception);
	}

	public static void isEquals(boolean expected, boolean actual, String message, Object... values) {
		boolean need = CheckUtil.isEquals(expected, actual);
		needThrow(need, message, values);
	}

	public static void isEquals(boolean expected, boolean actual, RuntimeException exception) {
		boolean need = CheckUtil.isEquals(expected, actual);
		needThrow(need, exception);
	}

	public static void isEquals(byte expected, byte actual, String message, Object... values) {
		boolean need = CheckUtil.isEquals(expected, actual);
		needThrow(need, message, values);
	}

	public static void isEquals(byte expected, byte actual, RuntimeException exception) {
		boolean need = CheckUtil.isEquals(expected, actual);
		needThrow(need, exception);
	}

	public static void isEquals(char expected, char actual, String message, Object... values) {
		boolean need = CheckUtil.isEquals(expected, actual);
		needThrow(need, message, values);
	}

	public static void isEquals(char expected, char actual, RuntimeException exception) {
		boolean need = CheckUtil.isEquals(expected, actual);
		needThrow(need, exception);
	}

	public static void isEquals(short expected, short actual, String message, Object... values) {
		boolean need = CheckUtil.isEquals(expected, actual);
		needThrow(need, message, values);
	}

	public static void isEquals(short expected, short actual, RuntimeException exception) {
		boolean need = CheckUtil.isEquals(expected, actual);
		needThrow(need, exception);
	}

	public static void isEquals(int expected, int actual, String message, Object... values) {
		boolean need = CheckUtil.isEquals(expected, actual);
		needThrow(need, message, values);
	}

	public static void isEquals(int expected, int actual, RuntimeException exception) {
		boolean need = CheckUtil.isEquals(expected, actual);
		needThrow(need, exception);
	}

	public static void isNotNull(Object object, String message, Object... values) {
		boolean need = CheckUtil.isNotNull(object);
		needThrow(need, message, values);
	}

	public static void isNotNull(Object object, RuntimeException exception) {
		boolean need = CheckUtil.isNotNull(object);
		needThrow(need, exception);
	}

	public static void isNull(Object object, String message, Object... values) {
		boolean need = CheckUtil.isNull(object);
		needThrow(need, message, values);
	}

	public static void isNull(Object object, RuntimeException exception) {
		boolean need = CheckUtil.isNull(object);
		needThrow(need, exception);
	}

	public static void isSame(Object expected, Object actual, String message, Object... values) {
		boolean need = CheckUtil.isSame(expected, actual);
		needThrow(need, message, values);
	}

	public static void isSame(Object expected, Object actual, RuntimeException exception) {
		boolean need = CheckUtil.isSame(expected, actual);
		needThrow(need, exception);
	}

	public static void isNotSame(Object expected, Object actual, String message, Object... values) {
		boolean need = CheckUtil.isNotSame(expected, actual);
		needThrow(need, message, values);
	}

	public static void isNotSame(Object expected, Object actual, RuntimeException exception) {
		boolean need = CheckUtil.isNotSame(expected, actual);
		needThrow(need, exception);
	}

	private static void needThrow(boolean need, RuntimeException exception) {
		if (need) {
			throw exception;
		}
	}

	private static void needThrow(boolean need, String message, Object... values) {
		if (need) {
			String outMessage = formatMessage(message, values);
			logger.error(outMessage);
			throw new CheckException(ApplicationErrorCode.EXCEPTION,outMessage);
		}
	}

	private static String formatMessage(String message, Object... values) {
		message = (null == message) ? "" : message;
		values = (null == values) ? new String[] {} : values;

		String outMessage = MessageFormat.format(message, values);
		return outMessage;
	}
}
