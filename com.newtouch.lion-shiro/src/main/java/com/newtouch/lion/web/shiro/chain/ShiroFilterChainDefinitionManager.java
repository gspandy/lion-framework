/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: ShiroFilterChainDefinitionManger.java 9552 2015年3月4日 上午1:04:20 WangLijun$
 */
package com.newtouch.lion.web.shiro.chain;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.newtouch.lion.web.shiro.model.AuthorityModel;
import com.newtouch.lion.web.shiro.service.ShiroResourceManager;

/**
 * <p>
 * Title:扩展Shiro过滤器
 * </p>
 * <p>
 * Description:扩展Shiro过滤器
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
public class ShiroFilterChainDefinitionManager  extends AbstractFilterChainDefinitions {
	
	/**日志*/
	private static final Logger logger=LoggerFactory.getLogger(ShiroFilterChainDefinitionManager.class);
	/***
	 * 资源权限读取
	 */
	@Autowired
	private ShiroResourceManager shiroResouceManager;

 
	@Override
	public List<AuthorityModel> initOtherPermission() {
	    logger.info("加载菜单资源进行拦截");
		return shiroResouceManager.doFindAll();
	}
}
