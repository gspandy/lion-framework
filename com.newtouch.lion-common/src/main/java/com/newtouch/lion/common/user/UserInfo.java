/*
 * Copyright (c)  2014, Newtouch
 * All rights reserved. 
 * 
 * $id: UserInfo.java 9552 2014年12月29日 下午5:30:07 WangLijun$
 */
package com.newtouch.lion.common.user;

import java.io.Serializable;

/**
 * <p>
 * Title: 用户信息类
 * </p>
 * <p>
 * Description: 用户信息类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class UserInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**用户名*/
	private String username;
	/**用户ID*/
	private Long id;
	
	/** 用户真实姓名－中文 */
	private String realnameZh;
	/** 用户真实姓名－英文 */
	private String realnameEn;
	
	private String password;
	
	private String userIP;
	
	private String image;
	
	private String macAddress;

	public UserInfo() {
	}

	
	
	/**
	 * @param username
	 * @param id
	 */
	public UserInfo(String username, Long id) {
		super();
		this.username = username;
		this.id = id;
	}


	/**
	 * @param username
	 * @param id
	 * @param realName
	 */
	public UserInfo(String username, Long id, String realnameZh,String realnameEn) {
		super();
		this.username = username;
		this.id = id;
		this.realnameZh = realnameZh;
		this.realnameEn=realnameEn;
	}
	
	/**
	 * @param username
	 * @param id
	 * @param realName
	 * @param image
	 */
	public UserInfo(String username, Long id, String realnameZh,String realnameEn,String image) {
		super();
		this.username = username;
		this.id = id;
		this.realnameZh = realnameZh;
		this.realnameEn=realnameEn;
		this.image = image;
	}



	public UserInfo(String username, Long id, String realnameZh,String realnameEn,String password,
			String userIP) {
		this.username = username;
		this.id = id;
		this.realnameZh = realnameZh;
		this.realnameEn=realnameEn;
		this.password = password;
		this.userIP = userIP;
	}
	
	public UserInfo(String username, Long id, String realnameZh,String realnameEn,String password,
			String userIP,String image) {
		this.username = username;
		this.id = id;
		this.realnameZh = realnameZh;
		this.realnameEn=realnameEn;
		this.password = password;
		this.userIP = userIP;
		this.image = image;
	}

	public Long getId() {
		return this.id;
	}

	public String getLoginId() {
		return this.username;
	}
 
	public String getPassword() {
		return this.password;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLoginId(String loginId) {
		this.username = loginId;
	}

	 

	public void setPassword(String password) {
		this.password = password;
	}

	public java.util.Locale getPreferredLocale() {
		// return
		return null;
	}

	public String getUserIP() {
		return this.userIP;
	}

	public void setUserIP(String userIP) {
		this.userIP = userIP;
	}



	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}



	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the macAddress
	 */
	public String getMacAddress() {
		return macAddress;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}



	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}



	/**
	 * @param macAddress the macAddress to set
	 */
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}



	/**
	 * @return the realnameZh
	 */
	public String getRealnameZh() {
		return realnameZh;
	}



	/**
	 * @param realnameZh the realnameZh to set
	 */
	public void setRealnameZh(String realnameZh) {
		this.realnameZh = realnameZh;
	}



	/**
	 * @return the realnameEn
	 */
	public String getRealnameEn() {
		return realnameEn;
	}



	/**
	 * @param realnameEn the realnameEn to set
	 */
	public void setRealnameEn(String realnameEn) {
		this.realnameEn = realnameEn;
	}
	
	
	
}
