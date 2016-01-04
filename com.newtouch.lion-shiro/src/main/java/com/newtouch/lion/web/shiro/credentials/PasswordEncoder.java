/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: PasswordEncoder.java 9552 2015年2月4日 上午9:39:21 WangLijun$
*/
package com.newtouch.lion.web.shiro.credentials; 

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * Title: 密码加密码类
 * </p>
 * <p>
 * Description: 密码加密码类 包括加密方法、验证密码方法
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
@Component
public class PasswordEncoder {
	
	private static final Logger logger=LoggerFactory.getLogger(PasswordEncoder.class);
	
	/****
	 * Shiro加密码服务
	 */
	@Autowired
	private   RetryLimitHashedCredentialsMatcher  credentialsMatcher;
	
	/***
	 * 加密服务 调用Shiro  RetryLimitHashedCredentialsMatcher 与登录保持一致加密算法
	 * @param password 密码
	 * @param salt 盐值
	 * @return 加密码后密码
	 */
	public String encodePassword(String password,Object salt){
		long startTime=System.currentTimeMillis();
		String hash = new SimpleHash(credentialsMatcher.getHashAlgorithmName(),password,ByteSource.Util.bytes(salt),credentialsMatcher.getHashIterations()).toString();
		long costTime=System.currentTimeMillis()-startTime;
		logger.info("password encoed cost time:{}",costTime);
		return hash;
	}
}

	