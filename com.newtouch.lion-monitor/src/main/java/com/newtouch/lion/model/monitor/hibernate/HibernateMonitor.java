/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: HibernateMonitor.java 9552 2015年7月17日 下午5:29:19 WangLijun$
*/
package com.newtouch.lion.model.monitor.hibernate; 

import com.newtouch.lion.model.BaseEntity;

/**
 * <p>
 * Title: 用于Hibernate监控
 * </p>
 * <p>
 * Description:  用于Hibernate监控
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
public class HibernateMonitor extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1721160465248978763L;
	
	private Long id;

	@Override
	public Long getId() {
		return this.id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	
}	

	