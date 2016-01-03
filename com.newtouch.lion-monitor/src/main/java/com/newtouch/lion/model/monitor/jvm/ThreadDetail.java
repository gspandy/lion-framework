/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: ThreadDetail.java 9552 2015年2月28日 下午10:14:11 WangLijun$
*/
package com.newtouch.lion.model.monitor.jvm; 

import java.io.Serializable;
import java.lang.Thread.State;

/**
 * <p>
 * Title: 线程详细信息
 * </p>
 * <p>
 * Description: 线程详细信息()
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
public class ThreadDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6050815859388005141L;
	/**线程的ID*/
	private long id;
	/**线程名称*/
	private String name;
	/**状态*/
	private State state;
	/**是否被挂起*/
	private boolean suspended;
	/**线程被锁定并等待该对象*/
	private String lockName;
	/**关联的线程被阻塞并等待该对象*/
	private String lockOwnerName;
	/**CPU 开销的时间*/
	private long cupCostTime;
	/**线程堆栈跟踪的信息*/
	private String stackInfo;
	/**线程等待的次数WaitedCount */
	private long waitedCount;
	/**线程等待的时间*/
	private long waitedTime;
	/**阻塞次数*/
	private long blockedCount;
	/**阻塞的时间*/
	private long blockedTime;
	/***
	 * 默认构造函数
	 */
	public ThreadDetail() {
		super();
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the state
	 */
	public State getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(State state) {
		this.state = state;
	}
	/**
	 * @return the suspended
	 */
	public boolean isSuspended() {
		return suspended;
	}
	/**
	 * @param suspended the suspended to set
	 */
	public void setSuspended(boolean suspended) {
		this.suspended = suspended;
	}
	/**
	 * @return the lockName
	 */
	public String getLockName() {
		return lockName;
	}
	/**
	 * @param lockName the lockName to set
	 */
	public void setLockName(String lockName) {
		this.lockName = lockName;
	}
	/**
	 * @return the lockOwnerName
	 */
	public String getLockOwnerName() {
		return lockOwnerName;
	}
	/**
	 * @param lockOwnerName the lockOwnerName to set
	 */
	public void setLockOwnerName(String lockOwnerName) {
		this.lockOwnerName = lockOwnerName;
	}
	/**
	 * @return the cupCostTime
	 */
	public long getCupCostTime() {
		return cupCostTime;
	}
	/**
	 * @param cupCostTime the cupCostTime to set
	 */
	public void setCupCostTime(long cupCostTime) {
		this.cupCostTime = cupCostTime;
	}
	/**
	 * @return the stackInfo
	 */
	public String getStackInfo() {
		return stackInfo;
	}
	/**
	 * @param stackInfo the stackInfo to set
	 */
	public void setStackInfo(String stackInfo) {
		this.stackInfo = stackInfo;
	}
	/**
	 * @return the waitedCount
	 */
	public long getWaitedCount() {
		return waitedCount;
	}
	/**
	 * @param waitedCount the waitedCount to set
	 */
	public void setWaitedCount(long waitedCount) {
		this.waitedCount = waitedCount;
	}
	/**
	 * @return the waitedTime
	 */
	public long getWaitedTime() {
		return waitedTime;
	}
	/**
	 * @param waitedTime the waitedTime to set
	 */
	public void setWaitedTime(long waitedTime) {
		this.waitedTime = waitedTime;
	}
	/**
	 * @return the blockedCount
	 */
	public long getBlockedCount() {
		return blockedCount;
	}
	/**
	 * @param blockedCount the blockedCount to set
	 */
	public void setBlockedCount(long blockedCount) {
		this.blockedCount = blockedCount;
	}
	/**
	 * @return the blockedTime
	 */
	public long getBlockedTime() {
		return blockedTime;
	}
	/**
	 * @param blockedTime the blockedTime to set
	 */
	public void setBlockedTime(long blockedTime) {
		this.blockedTime = blockedTime;
	}
	
	
    
}

	