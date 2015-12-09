package com.newtouch.lion.redis.exception;

public class RedisClientException extends RuntimeException {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7460934076911268418L;

	/**
	 * 构造方法
	 * 
	 * @param msg
	 *            异常信息
	 */
	public RedisClientException(String msg) {
		super(msg);
	}

	/**
	 * 构造方法
	 * 
	 * @param exception
	 *            异常原因
	 */
	public RedisClientException(Throwable exception) {
		super(exception);
	}

	/**
	 * 构造方法
	 * 
	 * @param mag
	 *            异常信息
	 * @param exception
	 *            异常原因
	 */
	public RedisClientException(String mag, Exception exception) {
		super(mag, exception);
	}
}
