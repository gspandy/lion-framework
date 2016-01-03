/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: JvmMontiorServiceImpl.java 9552 2015年2月28日 下午3:05:15 WangLijun$
*/
package com.newtouch.lion.service.monitor.impl; 
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.newtouch.lion.model.monitor.jvm.MemoryUsage;
import com.newtouch.lion.model.monitor.jvm.OperatingSystemInfo;
import com.newtouch.lion.model.monitor.jvm.RuntimeInfo;
import com.newtouch.lion.model.monitor.jvm.ThreadDetail;
import com.newtouch.lion.model.monitor.jvm.ThreadSummary;
import com.newtouch.lion.service.AbstractService;
import com.newtouch.lion.service.monitor.JvmMonitorService;
import com.sun.management.OperatingSystemMXBean;

/**
 * <p>
 * Title: JVM监控实现
 * </p>
 * <p>
 * Description:  JVM监控实现
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
@SuppressWarnings("restriction")
@Service
public class JvmMonitorServiceImpl extends AbstractService implements
		JvmMonitorService { 
	
	
	
	
	
	
	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.montior.JvmMontiorService#getThreadInfo()
	 */
	@Override
	public ThreadSummary getThreadInfo() {
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		threadMXBean.setThreadContentionMonitoringEnabled(true);
		ThreadSummary threadSummary=new ThreadSummary();
		threadSummary.setTotalStartedThreadCount(threadMXBean.getTotalStartedThreadCount());
		threadSummary.setDaemonThreadCount(threadMXBean.getDaemonThreadCount());
		threadSummary.setPeakThreadCount(threadMXBean.getPeakThreadCount());
		threadSummary.setThreadCount(threadMXBean.getThreadCount());
		//线程
		threadSummary.setThreadContentionMonitoringEnabled(threadMXBean.isThreadContentionMonitoringEnabled());
		threadSummary.setThreadContentionMonitoringSupported(threadMXBean.isThreadContentionMonitoringSupported());
		threadSummary.setThreadCpuTimeEnabled(threadMXBean.isThreadCpuTimeEnabled());
		threadSummary.setThreadCpuTimeSupported(threadMXBean.isThreadCpuTimeSupported());
		//线程明细信息
		long[] thIds=threadMXBean.getAllThreadIds();
		List<ThreadDetail> threadDetails=new ArrayList<ThreadDetail>(thIds.length);
		for(int i=0;i<thIds.length;i++){
			ThreadDetail threadDetail=new ThreadDetail();
			Long threadId=thIds[i];
			Long cupCostTime=threadMXBean.getThreadCpuTime(threadId);
			ThreadInfo threadInfo = threadMXBean.getThreadInfo(threadId,Integer.MAX_VALUE);
			threadDetail.setId(threadId);
			//CPU消耗的时间
			threadDetail.setCupCostTime(cupCostTime);
			//名称
			threadDetail.setName(threadInfo.getThreadName());
			//状态
			threadDetail.setState(threadInfo.getThreadState());
			//是否挂起
			threadDetail.setSuspended(threadInfo.isSuspended());
			//**线程被锁定并等待该对象
			threadDetail.setLockName(threadDetail.getLockName());
			/**关联的线程被阻塞并等待该对象*/
			threadDetail.setLockOwnerName(threadInfo.getLockOwnerName());
		 
			/**线程堆栈跟踪的信息*/
			StringBuilder sb=new StringBuilder();
			StackTraceElement[] stackTraceElements=threadInfo.getStackTrace();
			for(int j=0;j<stackTraceElements.length;j++){
				sb.append(stackTraceElements[j].toString());
			}
			threadDetail.setStackInfo(sb.toString());
			
			/**线程等待的次数WaitedCount */
			threadDetail.setWaitedCount(threadInfo.getWaitedCount());
			/**线程等待的时间*/
			threadDetail.setWaitedTime(threadInfo.getWaitedTime());
			/**阻塞次数*/
			threadDetail.setBlockedCount(threadInfo.getBlockedCount());
			/**阻塞的时间*/
			threadDetail.setBlockedTime(threadInfo.getBlockedTime());
			//添加到列表中
			threadDetails.add(threadDetail);
		}
		threadSummary.setThreadDetails(threadDetails);
		//threadInfo.setResetPeakThreadCount(threadMXBean.resetPeakThreadCount());
		return threadSummary;
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.montior.JvmMontiorService#getOperatingSystem()
	 */
	@Override
	public OperatingSystemInfo getOperatingSystem() {
		  
		 OperatingSystemMXBean  operatingSystemMXBean=(OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();
		 OperatingSystemInfo operatingSystemInfo=new OperatingSystemInfo();
		 operatingSystemInfo.setArch(operatingSystemMXBean.getArch());
		 operatingSystemInfo.setName(operatingSystemMXBean.getName());
		 operatingSystemInfo.setAvailableProcessors(operatingSystemMXBean.getAvailableProcessors());
		 operatingSystemInfo.setVersion(operatingSystemMXBean.getVersion());
		 operatingSystemInfo.setSystemLoadAverage(operatingSystemMXBean.getSystemLoadAverage());
		 //物理内存
		 operatingSystemInfo.setFreePhysicalMemorySize(operatingSystemMXBean.getFreePhysicalMemorySize());
		 operatingSystemInfo.setTotalPhysicalMemorySize(operatingSystemMXBean.getTotalPhysicalMemorySize());
		 //虚拟内存
		 operatingSystemInfo.setFreeSwapSpaceSize(operatingSystemMXBean.getFreeSwapSpaceSize());
		 operatingSystemInfo.setTotalSwapSpaceSize(operatingSystemMXBean.getTotalSwapSpaceSize());
		 //CPU
		 operatingSystemInfo.setSystemCpuLoad(operatingSystemMXBean.getSystemCpuLoad());
		 operatingSystemInfo.setProcessCpuLoad(operatingSystemMXBean.getProcessCpuLoad());
		 operatingSystemInfo.setProcessCpuTime(operatingSystemMXBean.getProcessCpuTime());
		 
		 return operatingSystemInfo;
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.montior.JvmMontiorService#getMemory()
	 */
	@Override
	public MemoryUsage getMemory() {
		MemoryMXBean bean=ManagementFactory.getMemoryMXBean();
		MemoryUsage memoryUsage=new MemoryUsage();
		memoryUsage.setCommitted(bean.getHeapMemoryUsage().getCommitted());
		memoryUsage.setInit(bean.getHeapMemoryUsage().getInit());
		memoryUsage.setMax(bean.getHeapMemoryUsage().getMax());
		memoryUsage.setUsed(bean.getHeapMemoryUsage().getUsed());
		memoryUsage.setVerbose(bean.isVerbose());
		memoryUsage.setObjectPendingFinalizationCount(bean.getObjectPendingFinalizationCount());
		return memoryUsage;
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.montior.JvmMontiorService#getJvmRuntimeInfo()
	 */
	@Override
	public RuntimeInfo getJvmRuntimeInfo() {
		RuntimeMXBean  bean=ManagementFactory.getRuntimeMXBean();
		RuntimeInfo model=new RuntimeInfo();
		model.setName(bean.getName());
		
		model.setVmName(bean.getVmName());
		model.setVmVendor(bean.getVmVendor());
		model.setVmVersion(bean.getVmVersion());
		
		model.setSpecName(bean.getSpecName());
		model.setSpecVendor(bean.getSpecVendor());
		model.setSpecVersion(bean.getSpecVersion());
		
		model.setStartTime(bean.getStartTime());
		model.setUpTime(bean.getUptime());
		
		model.setInputArguments(bean.getInputArguments());
		model.setClassPath(bean.getClassPath());
		model.setLibraryPath(bean.getLibraryPath());
		
		return model;
	}

}

	