/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: HibernateMonitorDaoImpl.java 9552 2015年7月17日 下午5:45:05 WangLijun$
*/
package com.newtouch.lion.dao.impl; 

import org.springframework.stereotype.Repository;

import com.newtouch.lion.dao.HibernateMonitorDao;
import com.newtouch.lion.model.monitor.hibernate.HibernateMonitor;

/**
 * <p>
 * Title:  HibernateDao层监控实现类
 * </p>
 * <p>
 * Description:  HibernateDao层监控实现类
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
@Repository
public class HibernateMonitorDaoImpl extends BaseDaoImpl<HibernateMonitor, Long>  implements HibernateMonitorDao{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 7701821590144242155L;
	
}

	