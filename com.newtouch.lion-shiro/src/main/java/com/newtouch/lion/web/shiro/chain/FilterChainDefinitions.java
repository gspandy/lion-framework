/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: FilterChainDefinitionsService.java 9552 2015年3月4日 下午3:14:41 WangLijun$
*/
package com.newtouch.lion.web.shiro.chain; 

import java.util.List;

import com.newtouch.lion.web.shiro.model.AuthorityModel;

/**
 * <p>
 * Title: Shiro权限动态更新
 * </p>
 * <p>
 * Description: Shiro权限动态更新
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
public interface FilterChainDefinitions {
	 /** 初始化框架权限资源配置 
	 * @throws Exception */  
    public abstract void initPermission() throws Exception;
    
    /** 重新加载框架权限资源配置 (强制线程同步) */  
    public abstract void updatePermission();
    
    /**始化第三方权限资源配置 */  
    public abstract  List<AuthorityModel> initOtherPermission();
}

	