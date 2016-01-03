/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: OperatingSystem.java 9552 2015年2月28日 下午4:45:39 WangLijun$
*/
package com.newtouch.lion.model.monitor.jvm; 

import java.io.Serializable;

/**
 * <p>
 * Title: JVM-操作系统信息
 * </p>
 * <p>
 * Description: JVM-操作系统信息
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
public class OperatingSystemInfo implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -1880395897108008506L;
	/**操作系统的架构*/
	private String arch;
	/**Java 虚拟机可以使用的处理器数目*/
	private int availableProcessors;
	/**操作系统名称*/
	private String name;
	/**操作系统版本*/
	private String version;
	/**SystemLoadAverage*/
	private double systemLoadAverage;
	/**空闲的物理内存量*/
	private long freePhysicalMemorySize;
	/**总的物理内存量*/
	private long totalPhysicalMemorySize;
	/*** 总的虚拟内存量*/
	private long totalSwapSpaceSize;
	/**空闲的虚拟内存量*/
	private long freeSwapSpaceSize;
	/**CPU运行的时间*/
	private long processCpuTime;
	/**CPU负载量*/
	private double systemCpuLoad;
	/**ProcessCpuLoad*/
	private double processCpuLoad;
	/**
	 * 默认构造函数
	 */
	public OperatingSystemInfo() {
			super();
	}

	/**
	 * @return the arch
	 */
	public String getArch() {
		return arch;
	}

	/**
	 * @param arch the arch to set
	 */
	public void setArch(String arch) {
		this.arch = arch;
	}

	/**
	 * @return the availableProcessors
	 */
	public int getAvailableProcessors() {
		return availableProcessors;
	}

	/**
	 * @param availableProcessors the availableProcessors to set
	 */
	public void setAvailableProcessors(int availableProcessors) {
		this.availableProcessors = availableProcessors;
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
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the systemLoadAverage
	 */
	public double getSystemLoadAverage() {
		return systemLoadAverage;
	}

	/**
	 * @param systemLoadAverage the systemLoadAverage to set
	 */
	public void setSystemLoadAverage(double systemLoadAverage) {
		this.systemLoadAverage = systemLoadAverage;
	}

	/**
	 * @return the freePhysicalMemorySize
	 */
	public long getFreePhysicalMemorySize() {
		return freePhysicalMemorySize;
	}

	/**
	 * @param freePhysicalMemorySize the freePhysicalMemorySize to set
	 */
	public void setFreePhysicalMemorySize(long freePhysicalMemorySize) {
		this.freePhysicalMemorySize = freePhysicalMemorySize;
	}

	/**
	 * @return the totalPhysicalMemorySize
	 */
	public long getTotalPhysicalMemorySize() {
		return totalPhysicalMemorySize;
	}

	/**
	 * @param totalPhysicalMemorySize the totalPhysicalMemorySize to set
	 */
	public void setTotalPhysicalMemorySize(long totalPhysicalMemorySize) {
		this.totalPhysicalMemorySize = totalPhysicalMemorySize;
	}

	/**
	 * @return the totalSwapSpaceSize
	 */
	public long getTotalSwapSpaceSize() {
		return totalSwapSpaceSize;
	}

	/**
	 * @param totalSwapSpaceSize the totalSwapSpaceSize to set
	 */
	public void setTotalSwapSpaceSize(long totalSwapSpaceSize) {
		this.totalSwapSpaceSize = totalSwapSpaceSize;
	}

	/**
	 * @return the freeSwapSpaceSize
	 */
	public long getFreeSwapSpaceSize() {
		return freeSwapSpaceSize;
	}

	/**
	 * @param freeSwapSpaceSize the freeSwapSpaceSize to set
	 */
	public void setFreeSwapSpaceSize(long freeSwapSpaceSize) {
		this.freeSwapSpaceSize = freeSwapSpaceSize;
	}

	/**
	 * @return the processCpuTime
	 */
	public long getProcessCpuTime() {
		return processCpuTime;
	}

	/**
	 * @param processCpuTime the processCpuTime to set
	 */
	public void setProcessCpuTime(long processCpuTime) {
		this.processCpuTime = processCpuTime;
	}

	/**
	 * @return the systemCpuLoad
	 */
	public double getSystemCpuLoad() {
		return systemCpuLoad;
	}

	/**
	 * @param systemCpuLoad the systemCpuLoad to set
	 */
	public void setSystemCpuLoad(double systemCpuLoad) {
		this.systemCpuLoad = systemCpuLoad;
	}

	/**
	 * @return the processCpuLoad
	 */
	public double getProcessCpuLoad() {
		return processCpuLoad;
	}

	/**
	 * @param processCpuLoad the processCpuLoad to set
	 */
	public void setProcessCpuLoad(double processCpuLoad) {
		this.processCpuLoad = processCpuLoad;
	}
	
	
	
}

	