/*
 * Copyright (c)  2012, Newtouch
 * All rights reserved. 
 *
 * $id: AuditEntity.java 9552 2012-7-8 上午12:12:33 WangLijun$
 */
package com.newtouch.lion.model;

import java.util.Date;

/**
 * <p>
 * Title:AuditEntity
 * </p>
 * <p>
 * Description:用于Model创建、更新时添加创建用户Id,日期、更新用户Id,日期，无逻辑删除Model 可继承该类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public abstract class AuditEntity<PK> extends BaseEntity<PK> {

	/**
	 * @Fields serialVersionUID
	 */

	private static final long serialVersionUID = 9064060658032054919L;
	/***
	 * @Fields createById: is create UserId
	 */
	private Long createdById;
	/***
	 * @Fields createdDate: is create Date
	 */
	private Date createdDate;
	/***
	 * @Fields updatedById: is update UserId
	 */
	private Long updatedById;
	/***
	 * @Fields updatedById: is update Date
	 */
	private Date updatedDate;

	/**
	 * getter method
	 * 
	 * @return the createdById
	 */

	public Long getCreatedById() {

		return createdById;
	}

	/**
	 * setter method
	 * 
	 * @param createdById
	 *            the createdById to set
	 */

	public void setCreatedById(Long createdById) {

		this.createdById = createdById;
	}

	/**
	 * getter method
	 * 
	 * @return the createdDate
	 */

	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * setter method
	 * 
	 * @param createdDate
	 *            the createdDate to set
	 */

	public void setCreatedDate(Date createdDate) {

		this.createdDate = createdDate;
	}

	/**
	 * getter method
	 * 
	 * @return the updatedById
	 */

	public Long getUpdatedById() {

		return updatedById;
	}

	/**
	 * setter method
	 * 
	 * @param updatedById
	 *            the updatedById to set
	 */

	public void setUpdatedById(Long updatedById) {

		this.updatedById = updatedById;
	}

	/**
	 * getter method
	 * 
	 * @return the updatedDate
	 */

	public Date getUpdatedDate() {

		return updatedDate;
	}

	/**
	 * setter method
	 * 
	 * @param updatedDate
	 *            the updatedDate to set
	 */

	public void setUpdatedDate(Date updatedDate) {

		this.updatedDate = updatedDate;
	}

}
