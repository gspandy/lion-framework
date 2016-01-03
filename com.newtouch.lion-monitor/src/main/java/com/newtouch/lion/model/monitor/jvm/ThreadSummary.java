/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: ThreadInfo.java 9552 2015年2月28日 下午8:17:26 WangLijun$
*/
package com.newtouch.lion.model.monitor.jvm; 

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * Title: JVM线程信息
 * </p>
 * <p>
 * Description: JVM线程信息
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
public class ThreadSummary  implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -7345603200985879137L;
	/**获取活动线程的当前数目，包括守护线程和非守护线程。*/
	private int threadCount;
	/**JVM总线程数*/
	private long totalStartedThreadCount;
	/**自从 Java 虚拟机启动或峰值重置以来峰值活动线程计数*/
	private int peakThreadCount;
	/**活动守护线程的当前数目*/
	private int daemonThreadCount;
	/**是否启用了线程争用监视*/
	private boolean threadContentionMonitoringEnabled;
	/**Java 虚拟机是否支持线程争用监视*/
	private boolean threadContentionMonitoringSupported;
	/**是否启用了线程 CPU 时间测量*/
	private boolean threadCpuTimeEnabled;
	/**Java 虚拟机是否支持当前线程的 CPU 时间测量*/
	private boolean threadCpuTimeSupported;
	/**线程明细*/
	private List<ThreadDetail> threadDetails;
	/***
	 * 线程信息默认构造函数
	 */
	public ThreadSummary() {
		 super();
	}
	/**
	 * @return the threadCount
	 */
	public int getThreadCount() {
		return threadCount;
	}
	/**
	 * @param threadCount the threadCount to set
	 */
	public void setThreadCount(int threadCount) {
		this.threadCount = threadCount;
	}
	/**
	 * @return the totalStartedThreadCount
	 */
	public long getTotalStartedThreadCount() {
		return totalStartedThreadCount;
	}
	/**
	 * @param totalStartedThreadCount the totalStartedThreadCount to set
	 */
	public void setTotalStartedThreadCount(long totalStartedThreadCount) {
		this.totalStartedThreadCount = totalStartedThreadCount;
	}
	/**
	 * @return the peakThreadCount
	 */
	public int getPeakThreadCount() {
		return peakThreadCount;
	}
	/**
	 * @param peakThreadCount the peakThreadCount to set
	 */
	public void setPeakThreadCount(int peakThreadCount) {
		this.peakThreadCount = peakThreadCount;
	}
	/**
	 * @return the daemonThreadCount
	 */
	public int getDaemonThreadCount() {
		return daemonThreadCount;
	}
	/**
	 * @param daemonThreadCount the daemonThreadCount to set
	 */
	public void setDaemonThreadCount(int daemonThreadCount) {
		this.daemonThreadCount = daemonThreadCount;
	}
	/**
	 * @return the threadContentionMonitoringEnabled
	 */
	public boolean isThreadContentionMonitoringEnabled() {
		return threadContentionMonitoringEnabled;
	}
	/**
	 * @param threadContentionMonitoringEnabled the threadContentionMonitoringEnabled to set
	 */
	public void setThreadContentionMonitoringEnabled(
			boolean threadContentionMonitoringEnabled) {
		this.threadContentionMonitoringEnabled = threadContentionMonitoringEnabled;
	}
	/**
	 * @return the threadContentionMonitoringSupported
	 */
	public boolean isThreadContentionMonitoringSupported() {
		return threadContentionMonitoringSupported;
	}
	/**
	 * @param threadContentionMonitoringSupported the threadContentionMonitoringSupported to set
	 */
	public void setThreadContentionMonitoringSupported(
			boolean threadContentionMonitoringSupported) {
		this.threadContentionMonitoringSupported = threadContentionMonitoringSupported;
	}
	/**
	 * @return the threadCpuTimeEnabled
	 */
	public boolean isThreadCpuTimeEnabled() {
		return threadCpuTimeEnabled;
	}
	/**
	 * @param threadCpuTimeEnabled the threadCpuTimeEnabled to set
	 */
	public void setThreadCpuTimeEnabled(boolean threadCpuTimeEnabled) {
		this.threadCpuTimeEnabled = threadCpuTimeEnabled;
	}
	/**
	 * @return the threadCpuTimeSupported
	 */
	public boolean isThreadCpuTimeSupported() {
		return threadCpuTimeSupported;
	}
	/**
	 * @param threadCpuTimeSupported the threadCpuTimeSupported to set
	 */
	public void setThreadCpuTimeSupported(boolean threadCpuTimeSupported) {
		this.threadCpuTimeSupported = threadCpuTimeSupported;
	}
	/**
	 * @return the threadDetails
	 */
	public List<ThreadDetail> getThreadDetails() {
		return threadDetails;
	}
	/**
	 * @param threadDetails the threadDetails to set
	 */
	public void setThreadDetails(List<ThreadDetail> threadDetails) {
		this.threadDetails = threadDetails;
	}
	
	
}

	