/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: LoginUser.java 9552 2015年2月17日 下午4:58:32 WangLijun$
*/
package com.newtouch.lion.web.shiro.model; 
/**
 * <p>
 * Title: 用户登录模型
 * </p>
 * <p>
 * Description: 用户登录模型
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
public class LoginUser {
	/**用户名*/
	private String username;
	/**密码*/
	private String password;
	/**验证码*/
	private String verifyCode;
	/**是否记录登录状态*/
	private Boolean rememberMe=Boolean.FALSE;
	
	public LoginUser() {
		 super();
	}
	
	

	/**
	 * 
	 * @param username 用户名
	 * @param password  密码
	 * @param verifyCode 验证码
	 */
	public LoginUser(String username, String password, String verifyCode) {
		super();
		this.username = username;
		this.password = password;
		this.verifyCode = verifyCode;
	}



	/**
	 * @return the username 用户名
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username 用户名
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password 密码
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password  密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the verifyCode 验证码
	 */
	public String getVerifyCode() {
		return verifyCode;
	}

	/**
	 * @param verifyCode the verifyCode  验证码
	 */
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}



	/**
	 * @return 是否记录登录状态
	 */
	public Boolean getRememberMe() {
		return rememberMe;
	}



	/**
	 * @param rememberMe 是否记录登录状态
	 */
	public void setRememberMe(Boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
	
	
}

	