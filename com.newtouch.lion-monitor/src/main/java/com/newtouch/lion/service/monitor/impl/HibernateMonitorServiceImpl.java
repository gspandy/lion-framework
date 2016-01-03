/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: HibernateMontiorServiceImpl.java 9552 2015年3月1日 上午12:22:32 WangLijun$
*/
package com.newtouch.lion.service.monitor.impl; 

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newtouch.lion.dao.HibernateMonitorDao;
import com.newtouch.lion.service.AbstractService;
import com.newtouch.lion.service.monitor.HibernateMonitorService;

/**
 * <p>
 * Title: Hibernate 监控统计信息服务实现类
 * </p>
 * <p>
 * Description:  Hibernate 监控统计信息服务实现类
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
@Service
public class HibernateMonitorServiceImpl extends AbstractService implements
		HibernateMonitorService {
	
	@Autowired
	private HibernateMonitorDao hibernateMonitorDao;

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.montior.HibernateMontiorService#getEntityManager()
	 */
	@Override
	public EntityManager getEntityManager() {
		return hibernateMonitorDao.getEntityManager();
	}

}

	