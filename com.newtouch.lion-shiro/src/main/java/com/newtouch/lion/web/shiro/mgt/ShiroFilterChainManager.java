/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: ShiroFilerChainManager.java 9552 2015年3月3日 下午1:23:14 WangLijun$
*/
package com.newtouch.lion.web.shiro.mgt; 

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.NamedFilterList;

import com.newtouch.lion.web.shiro.constant.Constants;
import com.newtouch.lion.web.shiro.model.AuthorityModel;

/**
 * <p>
 * Title: 自定义角色与权限定义
 * </p>
 * <p>
 * Description:  自定义角色与权限定义
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
 
public class ShiroFilterChainManager {
 
	private DefaultFilterChainManager filterChainManager;
	
	private Map<String, NamedFilterList> defaultFilterChains;
	
 
	public void init(){
		defaultFilterChains=new HashMap<String, NamedFilterList>(filterChainManager.getFilterChains());
	}
	
	public void initFilterChains(List<AuthorityModel> lists){
		 //1、首先删除以前老的filter chain并注册默认的  
        filterChainManager.getFilterChains().clear();  
        if(defaultFilterChains != null) {  
            filterChainManager.getFilterChains().putAll(defaultFilterChains);  
        } 
       
        for(AuthorityModel model:lists){
        	String url=model.getUrl();
        	//URL与角色关联
        	if(StringUtils.isNotEmpty(model.getRoles())){
        		this.filterChainManager.addToChain(url,Constants.ROLES,model.getRoles());
        	}
        	
        	//URL与权限关联
        	if(StringUtils.isNotEmpty(model.getPermissions())){
        		this.filterChainManager.addToChain(url,Constants.PERMS,model.getPermissions());
        	}
        }
	}
}

	