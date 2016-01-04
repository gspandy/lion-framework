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
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * check，检查对象，返回检查结果
 * 
 * @author dongfeng.zhang
 * @version 1.0
 */
public class CheckUtil {
	private static Logger logger = LoggerFactory.getLogger(CheckUtil.class);

	/**
	 * @see CheckUtil#emptyStr(String, String, Object...)
	 */
	public static boolean emptyStr(String text) {
		return emptyStr(text, null);
	}

	/**
	 * 是否空串，例子：
	 * "" : true,
	 * null : true,
	 * "   ": true,
	 * "abc" :false,
	 * " abc " : false
	 * 
	 * @param text
	 *            待检查串
	 * @param message
	 *            非空串记录日志文本（带占位符，java.text.MessageFormat格式）
	 * @param values
	 *            日志占位符替代值列表
	 * 
	 * @return 空串（包括全空格，null，长度为0的空串）返回true，非空返回false
	 */
	public static boolean emptyStr(String text, String message, Object... values) {
		if (isNull(text)) {
			return true;
		}

		boolean empty = (text.length() == 0 || text.trim().length() == 0) ? true : false;

		return isTrue(empty, message, values);
	}

	public static boolean emptyList(List<?> list) {
		return emptyList(list, null);
	}

	public static boolean emptyList(List<?> list, String message, Object... values) {
		if (isNull(list)) {
			return true;
		}

		boolean empty = list.isEmpty() ? true : false;

		return isTrue(empty, message, values);
	}

	public static boolean hasText(String text) {
		return hasText(text, null);
	}

	public static boolean hasText(String text, String message, Object... values) {
		if (emptyStr(text)) {
			return false;
		}

		boolean hasText = (text.trim().length() > 0) ? true : false;

		return isTrue(hasText, message, values);
	}

	public static boolean isNumber(String text) {
		return isNumber(text, null);
	}

	public static boolean isNumber(String text, String message, Object... values) {
		if (emptyStr(text)) {
			return false;
		}

		boolean isNumber = true;

		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);

			if ((c < '0') || (c > '9')) {
				isNumber = false;

				break;
			}
		}

		return isTrue(isNumber, message, values);
	}

	public static boolean isInteger(String text) {
		return isInteger(text, null);
	}

	public static boolean isInteger(String text, String message, Object... values) {
		if (emptyStr(text)) {
			return false;
		}

		boolean isInteger = true;

		try {
			Integer.parseInt(text);
		} catch (NumberFormatException nfm) {
			isInteger = false;
		}

		return isTrue(isInteger, message, values);
	}

	public static boolean isFloat(String text) {
		return isFloat(text, null);
	}

	public static boolean isFloat(String text, String message, Object... values) {
		if (emptyStr(text)) {
			return false;
		}

		boolean isFloat = true;

		try {
			Float.parseFloat(text);
		} catch (NumberFormatException nfm) {
			isFloat = false;
		}

		return isTrue(isFloat, message, values);
	}

	// 手机号
	public static boolean isMoblileNum(String text) {
		return isMoblileNum(text, null);
	}

	public static boolean isMoblileNum(String text, String message, Object... values) {
		boolean flag = false;
		String reg = "(^(\\+86\\-|86\\-)?[0-9]{3}[-]\\d{8})|(^(\\+86\\-|86\\-)?[0-9]{4}[-]\\d{7})";
		Pattern pattern = Pattern.compile(reg);
		flag = pattern.matcher(text).matches();

		return isTrue(flag, message, values);
	}

	public static boolean isEmail(String text) {
		return isEmail(text, null);
	}

	public static boolean isEmail(String text, String message, Object... values) {
		boolean flag = false;
		String reg = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
		Pattern pattern = Pattern.compile(reg);
		flag = pattern.matcher(text).matches();

		return isTrue(flag, message, values);
	}

	// 邮编
	public static boolean isZip(String text) {
		return isZip(text, null);
	}

	public static boolean isZip(String text, String message, Object... values) {
		boolean flag = false;
		String reg = "^([0-9])\\d{5}";
		Pattern pattern = Pattern.compile(reg);
		flag = pattern.matcher(text).matches();

		return isTrue(flag, message, values);
	}

	// 身份证
	public static boolean isICNum(String text) {
		return isICNum(text, null);
	}

	public static boolean isICNum(String text, String message, Object... values) {
		boolean flag = false;
		String reg = "(^\\d{15}$)|(\\d{17}(?:\\d|x|X)$)";
		Pattern pattern = Pattern.compile(reg);
		flag = pattern.matcher(text).matches();

		return isTrue(flag, message, values);
	}

	public static boolean isAgeRange(int age, int min, int max) {
		return isAgeRange(age, min, max, null);
	}

	// 年龄范围，age，min，max
	public static boolean isAgeRange(int age, int min, int max, String message, Object... values) {
		boolean flag = false;

		if ((age > min) && (age < max)) {
			flag = true;
		}

		return isTrue(flag, message, values);
	}

	public static boolean hasChinese(String text) {
		return hasChinese(text, null, null);
	}

	// 含有中文
	public static boolean hasChinese(String text, String message, Object... values) {
		if (emptyStr(text)) {
			return false;
		}

		boolean isChinese = false;

		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);

			if (c > 256) {
				isChinese = true;

				break;
			}
		}

		return isTrue(isChinese, message, values);
	}

	public static boolean isAllChinese(String text) {
		return isAllChinese(text, null);
	}

	// 全中文构成
	public static boolean isAllChinese(String text, String message, Object... values) {
		if (emptyStr(text)) {
			return false;
		}

		boolean isChinese = true;

		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);

			if (c < 256) {
				isChinese = false;

				break;
			}
		}

		return isTrue(isChinese, message, values);
	}

	public static boolean isTrue(boolean condition) {
		return isTrue(condition, null);
	}

	public static boolean isTrue(boolean condition, String message, Object... values) {
		boolean isTrue = true;

		if (!(condition)) {
			if (null != message) {
				logging(message, values);
			}

			isTrue = false;
		}

		return isTrue;
	}

	public static boolean isFalse(boolean condition) {
		return isTrue(!(condition), null);
	}

	public static boolean isFalse(boolean condition, String message, Object... values) {
		return isTrue(!(condition), message, values);
	}

	public static boolean isEquals(Object expected, Object actual) {
		return isEquals(expected, actual, null);
	}

	public static boolean isEquals(Object expected, Object actual, String message, Object... values) {
		if ((expected == null) && (actual == null)) {
			return true;
		}

		if ((expected != null) && (expected.equals(actual))) {
			return true;
		}

		if (null != message) {
			logging(message, values);
		}

		return false;
	}

	public static boolean isEquals(double expected, double actual, double delta) {
		return isEquals(expected, actual, delta, null);
	}

	public static boolean isEquals(double expected, double actual, double delta, String message, Object... values) {
		if (Math.abs(expected - actual) > delta) {
			if (null != message) {
				logging(message, values);
			}

			return false;
		}

		return true;
	}

	public static boolean isEquals(float expected, float actual, float delta) {
		return isEquals(expected, actual, delta, null);
	}

	public static boolean isEquals(float expected, float actual, float delta, String message, Object... values) {
		if (Math.abs(expected - actual) > delta) {
			if (null != message) {
				logging(message, values);
			}

			return false;
		}

		return true;
	}

	public static boolean isEquals(long expected, long actual) {
		return isEquals(new Long(expected), new Long(actual));
	}

	public static boolean isEquals(long expected, long actual, String message, Object... values) {
		return isEquals(new Long(expected), new Long(actual), message, values);
	}

	public static boolean isEquals(boolean expected, boolean actual) {
		return isEquals(Boolean.valueOf(expected), Boolean.valueOf(actual));
	}

	public static boolean isEquals(boolean expected, boolean actual, String message, Object... values) {
		return isEquals(Boolean.valueOf(expected), Boolean.valueOf(actual), message, values);
	}

	public static boolean isEquals(byte expected, byte actual) {
		return isEquals(expected, actual, null, null);
	}

	public static boolean isEquals(byte expected, byte actual, String message, Object... values) {
		return isEquals(new Byte(expected), new Byte(actual), message, values);
	}

	public static boolean isEquals(char expected, char actual) {
		return isEquals(expected, actual, null, null);
	}

	public static boolean isEquals(char expected, char actual, String message, Object... values) {
		return isEquals(new Character(expected), new Character(actual), message, values);
	}

	public static boolean isEquals(short expected, short actual) {
		return isEquals(expected, actual, null, null);
	}

	public static boolean isEquals(short expected, short actual, String message, Object... values) {
		return isEquals(new Short(expected), new Short(actual), message, values);
	}

	public static boolean isEquals(int expected, int actual) {
		return isEquals(expected, actual, null, null);
	}

	public static boolean isEquals(int expected, int actual, String message, Object... values) {
		return isEquals(new Integer(expected), new Integer(actual), message, values);
	}

	public static boolean isNotNull(Object object) {
		return isNotNull(object, null);
	}

	public static boolean isNotNull(Object object, String message, Object... values) {
		return isTrue(object != null, message, values);
	}

	public static boolean isNull(Object object) {
		return isNull(object, null);
	}

	public static boolean isNull(Object object, String message, Object... values) {
		return isTrue(object == null, message, values);
	}

	public static boolean isSame(Object expected, Object actual) {
		return isSame(expected, actual, null);
	}

	public static boolean isSame(Object expected, Object actual, String message, Object... values) {
		if (expected == actual) {
			return true;
		}

		if (null != message) {
			logging(message, values);
		}

		return false;
	}

	public static boolean isNotSame(Object expected, Object actual) {
		return isNotSame(expected, actual, null);
	}

	public static boolean isNotSame(Object expected, Object actual, String message, Object... values) {
		if (expected == actual) {
			if (null != message) {
				logging(message, values);
			}

			return false;
		}

		return true;
	}

	private static void logging(String message, Object... values) {
		message = (null == message) ? "" : message;
		values = (null == values) ? new String[] {} : values;
		String outMessage = MessageFormat.format(message, values);
		logger.warn(outMessage);
	}
}
