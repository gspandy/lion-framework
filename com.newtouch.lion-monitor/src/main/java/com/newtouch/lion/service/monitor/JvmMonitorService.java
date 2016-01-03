/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: JvmMontiorService.java 9552 2015年2月28日 下午2:51:31 WangLijun$
*/
package com.newtouch.lion.service.monitor; 

import com.newtouch.lion.model.monitor.jvm.MemoryUsage;
import com.newtouch.lion.model.monitor.jvm.OperatingSystemInfo;
import com.newtouch.lion.model.monitor.jvm.RuntimeInfo;
import com.newtouch.lion.model.monitor.jvm.ThreadSummary;

/**
 * <p>
 * Title: JVM监控管理
 * </p>
 * <p>
 * Description:  JVM监控管理
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
public interface JvmMonitorService {
	/***
	 * 获取JVM运行时信息
	 * @return RuntimeInfo 
	 */
	public RuntimeInfo getJvmRuntimeInfo();	
	/***
	 * 获取JVM内存使用情况
	 */
	public MemoryUsage getMemory();
	
	/***获取操作系统信息*/
	public OperatingSystemInfo getOperatingSystem();
	
	/**JVM线程监控*/
	public ThreadSummary getThreadInfo();
	
}

	