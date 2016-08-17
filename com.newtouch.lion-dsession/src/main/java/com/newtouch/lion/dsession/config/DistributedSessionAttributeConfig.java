/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: SessionAttributeConfig.java 9552 2015年5月21日 下午2:40:36 WangLijun$
 */
package com.newtouch.lion.dsession.config;

import com.newtouch.lion.dsession.encoder.DefaultEncoder;
import com.newtouch.lion.dsession.encoder.Encoder;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
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
public class DistributedSessionAttributeConfig {
	/**
	 * key group
	 */
	private String keyGroup;

	/**
	 * keyPattern
	 */
	private Pattern keyPattern;

	/**
	 * session key和value的转换工具
	 */
	private Encoder encoder = new DefaultEncoder();

	/**
	 * 
	 * 功能描述:getKeyGroup <br>
	 * @return keyGroup
	 */
	public String getKeyGroup() {
		return keyGroup;
	}

	/**
	 * 
	 * 功能描述: setKeyGroup<br>
	 * @param keyGroup    keyGroup
	 */
	public void setKeyGroup(String keyGroup) {
		this.keyGroup = keyGroup;
	}

	/**
	 * 
	 * 功能描述:getKeyPattern <br>
	 * @return keyPattern
	 */
	public Pattern getKeyPattern() {
		if (keyPattern == null) {
			keyPattern = Pattern.compile(keyGroup);
		}
		return keyPattern;
	}

	/**
	 * 
	 * 功能描述:setKeyPattern <br>
	 * @param keyPattern       keyPattern
	 */
	public void setKeyPattern(Pattern keyPattern) {
		this.keyPattern = keyPattern;
	}

	/**
	 * 
	 * 功能描述:Encoder <br>
	 * @return encoder
 
	 */
	public Encoder getEncoder() {
		return encoder;
	}

	/**
	 * 
	 * 功能描述:setEncoder <br>
	 * @param encoder     encoder
  
	 */
	public void setEncoder(Encoder encoder) {
		this.encoder = encoder;
	}

	/**
	 * 
	 * 功能描述:isMatch <br>
	 * @param key   String
	 * @return boolean
	 */
	public boolean isMatch(String key) {
		if (!StringUtils.hasLength(key)) {
			return false;
		}
		Matcher matcher = getKeyPattern().matcher(key);
		return matcher.matches();
	}
}
