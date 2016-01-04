/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: ShiroResouceService.java 9552 2015年3月3日 下午6:01:55 WangLijun$
*/
package com.newtouch.lion.web.shiro.service; 

import java.util.List;

import com.newtouch.lion.web.shiro.model.AuthorityModel;

/**
 * <p>
 * Title: Shiro资源管理
 * </p>
 * <p>
 * Description: Shiro资源管理
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
public interface ShiroResouceManager {
	/****
	 * 根据资源查询所有权限信息
	 * @return
	 */
	public List<AuthorityModel> doFindAll();
}

	