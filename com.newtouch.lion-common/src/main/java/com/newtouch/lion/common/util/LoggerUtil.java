/**
 * 
 */
package com.newtouch.lion.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wanglijun
 * 
 */
public class LoggerUtil {
	public static void error(Logger logger, Object obj) {
		logger.error("{}",obj);
	}

	public static void error(Logger logger, Object obj, Throwable throwable) {
		logger.error(throwable.getMessage(), throwable);
		logger.error("Object:{},Throwable:{}",obj,throwable);
	}

	public static void info(Logger logger, Object obj) {
		logger.info("{}",obj);
	}

	public static void info(Logger logger, Object obj, Throwable throwable) {
	 
		logger.info("Object:{},Throwable:{}",obj,throwable);
	}

	public static void debug(Logger logger, Object obj) {
		logger.debug("{}",obj);
	}

	public static void debug(Logger logger, Object obj, Throwable throwable) {
		logger.debug("Object:{},Throwable:{}",obj,throwable);
	}

	public static void warn(Logger logger, Object obj) {
		logger.warn(":{}",obj);
	}

	public static void warn(Logger logger, Object obj, Throwable throwable) {
		logger.warn("Object:{},Throwable:{}",obj,throwable);
	}

	public static Logger getLog(Class<?> clazz) {
		return LoggerFactory.getLogger(clazz);
	}

	public static Logger getLog(String clazz) {
		return LoggerFactory.getLogger(clazz);
	}
}
