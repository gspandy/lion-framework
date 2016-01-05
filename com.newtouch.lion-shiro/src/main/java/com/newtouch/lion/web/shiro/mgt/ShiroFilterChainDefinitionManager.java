/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: ShiroFilterChainDefinitionManger.java 9552 2015年3月4日 上午1:04:20 WangLijun$
 */
package com.newtouch.lion.web.shiro.mgt;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.config.IniFilterChainResolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.newtouch.lion.common.lang.StringFormatter;
import com.newtouch.lion.web.shiro.constant.Constants;
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
public class ShiroFilterChainDefinitionManager  implements FactoryBean<Section> {
	
	/**日志*/
	private static final Logger logger=LoggerFactory.getLogger(ShiroFilterChainDefinitionManager.class);
	/***
	 * 资源权限读取
	 */
	@Autowired
	private ShiroResourceManager shiroResouceManager;

	/** shiro默认的链接定义***/
	private String filterChainDefinitions;

	/**
	 * 通过filterChainDefinitions对默认的链接过滤定义
	 * 
	 * @param filterChainDefinitions
	 *            默认的接过滤定义
	 */
	public void setFilterChainDefinitions(String filterChainDefinitions) {
		this.filterChainDefinitions = filterChainDefinitions;
	}

	@Override
	public Section getObject() throws Exception {
		Ini ini = new Ini();
		// 加载默认的url
		ini.load(filterChainDefinitions);
		Section section = ini.getSection(IniFilterChainResolverFactory.URLS);
		if (CollectionUtils.isEmpty(section)) {
			section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
		}
		 List<AuthorityModel> resources =shiroResouceManager.doFindAll();
		// 循环数据库资源的url
		for (AuthorityModel authorityModel:resources) {
			 StringBuffer sb=new StringBuffer();
			 sb.append(Constants.AUTHC);
			/***
			 * 角色
			 * */
			//if(StringUtils.isNotEmpty(authorityModel.getRoles())){
			//	sb.append(",");
			//	sb.append(this.format(Constants.ROLE_STRING,authorityModel.getRoles()));
			//}
			/****
			 *权限
			 */
			if(StringUtils.isNotEmpty(authorityModel.getPermissions())){
				sb.append(",");
				sb.append(StringFormatter.format(Constants.PERMS_STRING,authorityModel.getPermissions()));
			}
			logger.debug("{} = {}",authorityModel.getUrl(),sb.toString());
			section.put(authorityModel.getUrl(),sb.toString());
			
		}
		return section;
	}
	
	@Override
	public Class<?> getObjectType() {
		return Section.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
