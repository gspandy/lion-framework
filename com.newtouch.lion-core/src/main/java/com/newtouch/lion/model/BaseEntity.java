/*
 * Copyright (c)  2012, Newtouch
 * All rights reserved. 
 *
 * $id: AuditEntity.java 9552 2012-7-8 上午12:12:33 WangLijun$
 */
package com.newtouch.lion.model;

import java.io.Serializable;

/**
 * <p>
 * Title:BaseEntity
 * </p>
 * <p>
 * Description:Model基础类，所有Model必须继承该类 创建日期、修改日期、删除日期必须记录到(yyyyMMdd HH:mm:ss:SSS)
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
public abstract class BaseEntity<PK> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8506395864266132858L;

	/**
	 * 
	 * @return Long
	 */
	public abstract PK getId();

	/***
	 * 序列号
	 */
	public static final String Id = "id";

	/*
	 * <p>Title: hashCode</p> <p>Description: </p>
	 * 
	 * @return
	 * 
	 * @see java.lang.Object#hashCode()
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getId() == null) ? super.hashCode() : getId().hashCode());
		return result;
	}

	/*
	 * <p>Title: equals</p> <p>Description: </p>
	 * 
	 * @param obj*
	 * 
	 * @return boolean
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (super.getClass() != obj.getClass())
			return false;
		@SuppressWarnings("unchecked")
		BaseEntity<PK> other = (BaseEntity<PK>) obj;
		if (getId() == null)
			if (other.getId() != null)
				return false;
			else if (!(getId().equals(other.getId())))
				return false;
		return true;
	}

}
