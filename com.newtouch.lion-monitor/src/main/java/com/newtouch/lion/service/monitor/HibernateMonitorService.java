/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: HibernateMontiorService.java 9552 2015年3月1日 上午12:18:13 WangLijun$
*/
package com.newtouch.lion.service.monitor; 

import javax.persistence.EntityManager;

/**
 * <p>
 * Title: Hibernate 监控统计信息服务接口定义
 * </p>
 * <p>
 * Description: Hibernate 监控统计信息服务接口定义
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
public  interface HibernateMonitorService {
	/***
	 * 获取JPA的EntityManager
	 * @return  EntityManager
	 */
	public EntityManager getEntityManager();
}

	