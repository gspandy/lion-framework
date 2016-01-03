/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: RuntimeModel.java 9552 2015年2月28日 下午2:55:53 WangLijun$
*/
package com.newtouch.lion.model.monitor.jvm; 

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * Title: JVM 运行时信息
 * </p>
 * <p>
 * Description:  JVM 运行时信息
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
public class RuntimeInfo implements Serializable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -3829207668806440450L;
	/**返回表示正在运行的 Java 虚拟机的名称*/
	private String name;
	/**Java 虚拟机实现名称*/
	private String vmName;
	/**Java 虚拟机实现版本*/
	private String vmVersion;
	/**Java 虚拟机实现供应商*/
	private String vmVendor;
	/**Java 虚拟机规范名称*/
	private String specName;
	/**Java 虚拟机规范供应商*/
	private String specVendor;
	/**Java 虚拟机规范版本*/	
	private String specVersion;
	/***Java 虚拟机的启动时间（以毫秒为单位）*/
	private long startTime;
	/**Java 虚拟机的正常运行时间（以毫秒为单位）*/
	private long upTime;
	/**参数*/
	private List<String> inputArguments;
	/****
	 * Java 库路径
	 */
	private String libraryPath;
	/***
	 * 系统类加载器用于搜索类文件的 Java 类路径
	 */
	private String classPath;
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
	 * @return the vmName
	 */
	public String getVmName() {
		return vmName;
	}
	/**
	 * @param vmName the vmName to set
	 */
	public void setVmName(String vmName) {
		this.vmName = vmName;
	}
	/**
	 * @return the vmVersion
	 */
	public String getVmVersion() {
		return vmVersion;
	}
	/**
	 * @param vmVersion the vmVersion to set
	 */
	public void setVmVersion(String vmVersion) {
		this.vmVersion = vmVersion;
	}
	/**
	 * @return the vmVendor
	 */
	public String getVmVendor() {
		return vmVendor;
	}
	/**
	 * @param vmVendor the vmVendor to set
	 */
	public void setVmVendor(String vmVendor) {
		this.vmVendor = vmVendor;
	}
	/**
	 * @return the specName
	 */
	public String getSpecName() {
		return specName;
	}
	/**
	 * @param specName the specName to set
	 */
	public void setSpecName(String specName) {
		this.specName = specName;
	}
	/**
	 * @return the specVendor
	 */
	public String getSpecVendor() {
		return specVendor;
	}
	/**
	 * @param specVendor the specVendor to set
	 */
	public void setSpecVendor(String specVendor) {
		this.specVendor = specVendor;
	}
	/**
	 * @return the specVersion
	 */
	public String getSpecVersion() {
		return specVersion;
	}
	/**
	 * @param specVersion the specVersion to set
	 */
	public void setSpecVersion(String specVersion) {
		this.specVersion = specVersion;
	}
	/**
	 * @return the startTime
	 */
	public long getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	/**
	 * @return the upTime
	 */
	public long getUpTime() {
		return upTime;
	}
	/**
	 * @param upTime the upTime to set
	 */
	public void setUpTime(long upTime) {
		this.upTime = upTime;
	}
	/**
	 * @return the inputArguments
	 */
	public List<String> getInputArguments() {
		return inputArguments;
	}
	/**
	 * @param inputArguments the inputArguments to set
	 */
	public void setInputArguments(List<String> inputArguments) {
		this.inputArguments = inputArguments;
	}
	/**
	 * @return the libraryPath
	 */
	public String getLibraryPath() {
		return libraryPath;
	}
	/**
	 * @param libraryPath the libraryPath to set
	 */
	public void setLibraryPath(String libraryPath) {
		this.libraryPath = libraryPath;
	}
	/**
	 * @return the classPath
	 */
	public String getClassPath() {
		return classPath;
	}
	/**
	 * @param classPath the classPath to set
	 */
	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}
 
	
	
}

	