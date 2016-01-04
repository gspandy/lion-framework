/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: LoginSessionUtil.java 9552 2015年1月4日 下午3:40:01 WangLijun$
*/
package com.newtouch.lion.web.shiro.session; 

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.newtouch.lion.common.user.UserInfo;

/**
 * <p>
 * Title: 用户登录工具类
 * </p>
 * <p>
 * Description: 用户登录工具类
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
public class LoginSecurityUtil {
	/***
	 * 获取用户信息
	 * @return User;
	 */
	public static  UserInfo getUser(){
		Subject  subject=SecurityUtils.getSubject();
		if(subject!=null&&(subject.getPrincipal() instanceof UserInfo)){ 
			return  (UserInfo)subject.getPrincipal();
		}
		return null;
	}
}

	