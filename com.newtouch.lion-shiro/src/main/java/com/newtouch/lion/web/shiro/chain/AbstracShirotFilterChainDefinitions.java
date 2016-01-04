/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: AbstractFilterChainDefinitionsService.java 9552 2015年3月4日 下午3:23:10 WangLijun$
 */
package com.newtouch.lion.web.shiro.chain;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.config.IniFilterChainResolverFactory;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.newtouch.lion.common.lang.StringFormatter;
import com.newtouch.lion.web.shiro.constant.Constants;
import com.newtouch.lion.web.shiro.model.AuthorityModel;

/**
 * <p>
 * Title: 动态修改资源权限不需要重新启动应用
 * </p>
 * <p>
 * Description: 动态修改资源权限不需要重新启动应用
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
public abstract class AbstracShirotFilterChainDefinitions implements FilterChainDefinitions, FactoryBean<Section> {
	/***
	 * 日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(AbstracShirotFilterChainDefinitions.class);

	/** shiro默认的链接定义 ***/
	private String filterChainDefinitions;

	@Autowired
	private ShiroFilterFactoryBean shiroFilterFactoryBean;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.newtouch.lion.web.shiro.chain.FilterChainDefinitionsService#
	 * intiPermission()
	 */
	@PostConstruct
	@Override
	public void initPermission() {
		shiroFilterFactoryBean.setFilterChainDefinitionMap(obtainPermission());
		logger.info("initialize shiro permission success...");
	}

	private Section obtainPermission() {
		try {
			return this.getObject();
		} catch (java.lang.Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.newtouch.lion.web.shiro.chain.FilterChainDefinitionsService#
	 * updatePermission()
	 */
	@Override
	public void updatePermission() {
		synchronized (shiroFilterFactoryBean) {

			AbstractShiroFilter shiroFilter = null;

			try {
				//加载资源
				shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
			} catch (java.lang.Exception e) {
				logger.error(e.getMessage());
			}

			// 获取过滤管理器
			PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter
					.getFilterChainResolver();
			DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver
					.getFilterChainManager();

			// 清空初始权限配置
			manager.getFilterChains().clear();
			shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();

			// 重新构建生成
			shiroFilterFactoryBean.setFilterChainDefinitions(this.getFilterChainDefinitions());
			//重新加载资源
			shiroFilterFactoryBean.setFilterChainDefinitionMap(obtainPermission());

			Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();

			for (Map.Entry<String, String> entry : chains.entrySet()) {
				String url = entry.getKey();
				String chainDefinition = entry.getValue().trim().replace(" ",StringUtils.EMPTY);
				manager.createChain(url, chainDefinition);
			}
			logger.info("update shiro permission success...");
		}
	}

	/**
	 * @return  对默认的链接过滤定义
	 */
	public String getFilterChainDefinitions() {
		return filterChainDefinitions;
	}


	/**
	 * 通过filterChainDefinitions对默认的链接过滤定义
	 * 
	 * @param filterChainDefinitions    默认的接过滤定义
	 */
	public void setFilterChainDefinitions(String filterChainDefinitions) {
		this.filterChainDefinitions = filterChainDefinitions;
	}

	@Override
	public Section getObject() throws java.lang.Exception{
		Ini ini = new Ini();
		// 加载默认的url
		ini.load(filterChainDefinitions);
		Section section = ini.getSection(IniFilterChainResolverFactory.URLS);
		if (CollectionUtils.isEmpty(section)) {
			section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
		}
		List<AuthorityModel> resources = this.initOtherPermission();
		// 循环数据库资源的url
		for (AuthorityModel authorityModel : resources) {
			StringBuffer sb = new StringBuffer();
			sb.append(Constants.AUTHC);
			/****
			 * 权限
			 */
			if (StringUtils.isNotEmpty(authorityModel.getPermissions())) {
				sb.append(",");
				sb.append(StringFormatter.format(Constants.PERMS_STRING,
						authorityModel.getPermissions()));
			}
			logger.info("{} = {}", authorityModel.getUrl(), sb.toString());
			section.put(authorityModel.getUrl(), sb.toString());
		}
		return section;
	}

	public abstract List<AuthorityModel> initOtherPermission();

	@Override
	public java.lang.Class<?> getObjectType() {
		return Section.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
