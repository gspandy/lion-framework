/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: MemoryModel.java 9552 2015年2月28日 下午3:58:54 WangLijun$
*/
package com.newtouch.lion.model.monitor.jvm; 

import java.io.Serializable;

/**
 * <p>
 * Title: JVM 内存信息
 * </p>
 * <p>
 * Description:  JVM 内存信息
 * 	<br/>init - Java 虚拟机分配的初始内存量（以字节为单位）；或者，如果未定义，则为 -1。
	<br/>used - 已经使用的内存量（以字节为单位）。
	<br/>committed - 已经提交的内存量（以字节为单位）。
	<br/>max - 可以使用的最大内存量（以字节为单位）；或者，如果未定义，则为 -1。
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
public class MemoryUsage implements Serializable{

	/** 序列化 */
	private static final long serialVersionUID = 3245277942397511535L;
	/**已提交给 Java 虚拟机使用的内存量*/
	private long committed;
	/*** 初始化内存量 */
	private long init;
	/** 最大内存量 */
	private long max;	
	/**已使用内存量*/
	private long used;
	/*** 其终止被挂起的对象的近似数目 */
	private int objectPendingFinalizationCount;
	/**是否启动GC日志输出*/
	private boolean verbose;
	/***
	 * 默认构造函数
	 */
	public MemoryUsage() {
		super();
	}
	/**
	 * @return the committed
	 */
	public long getCommitted() {
		return committed;
	}
	/**
	 * @param committed the committed to set
	 */
	public void setCommitted(long committed) {
		this.committed = committed;
	}
	/**
	 * @return the init
	 */
	public long getInit() {
		return init;
	}
	/**
	 * @param init the init to set
	 */
	public void setInit(long init) {
		this.init = init;
	}
	/**
	 * @return the max
	 */
	public long getMax() {
		return max;
	}
	/**
	 * @param max the max to set
	 */
	public void setMax(long max) {
		this.max = max;
	}
	/**
	 * @return the used
	 */
	public long getUsed() {
		return used;
	}
	/**
	 * @param used the used to set
	 */
	public void setUsed(long used) {
		this.used = used;
	}
	/**
	 * @return the objectPendingFinalizationCount
	 */
	public int getObjectPendingFinalizationCount() {
		return objectPendingFinalizationCount;
	}
	/**
	 * @param objectPendingFinalizationCount the objectPendingFinalizationCount to set
	 */
	public void setObjectPendingFinalizationCount(int objectPendingFinalizationCount) {
		this.objectPendingFinalizationCount = objectPendingFinalizationCount;
	}
	/**
	 * @return the verbose
	 */
	public boolean isVerbose() {
		return verbose;
	}
	/**
	 * @param verbose the verbose to set
	 */
	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}
	
	
}

	