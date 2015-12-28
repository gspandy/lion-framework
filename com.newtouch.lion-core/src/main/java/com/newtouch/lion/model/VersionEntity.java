/*
 * Copyright (c)  2012, Newtouch
 * All rights reserved. 
 *
 * $id: VersionEntity.java 9552 2012-7-8 上午12:22:27 WangLijun$
 */
package com.newtouch.lion.model;

import java.util.Date;

/**
 * <p>
 * Title:VersionEntity
 * </p>
 * <p>
 * Description:用于记录更新记录的Version,是否删除、删除日期
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
public abstract class VersionEntity<PK> extends AuditEntity<PK> {

	private static final long serialVersionUID = -746324913862566791L;
	/***
	 * 操作Version
	 */
	private Long optCounter;
	/** 是否删除 */
	private Boolean isDeleted = Boolean.valueOf(false);
	/** 删除日期 */
	private Date deleteDate;

	/**
	 * getter method
	 * 
	 * @return the optCounter
	 */
	public Long getOptCounter() {

		return optCounter;
	}

	/**
	 * setter method
	 * 
	 * @param optCounter
	 *            the optCounter to set
	 */
	public void setOptCounter(Long optCounter) {

		this.optCounter = optCounter;
	}

	/**
	 * getter method
	 * 
	 * @return the isDeleted
	 */
	 
	public Boolean getIsDeleted() {

		return isDeleted;
	}

	/**
	 * setter method
	 * 
	 * @param isDeleted
	 *            the isDeleted to set
	 */
	public void setIsDeleted(Boolean isDeleted) {

		this.isDeleted = isDeleted;
	}

	/**
	 * getter method
	 * 
	 * @return the deleteDate
	 */
	public Date getDeleteDate() {

		return deleteDate;
	}

	/**
	 * setter method
	 * 
	 * @param deleteDate
	 *            the deleteDate to set
	 */
	public void setDeleteDate(Date deleteDate) {

		this.deleteDate = deleteDate;
	}

}
