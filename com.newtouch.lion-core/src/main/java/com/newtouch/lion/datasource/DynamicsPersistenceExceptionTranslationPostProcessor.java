/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: PersistenceExceptionTranslationPostProcessor.java 9552 2015年10月15日 下午3:50:38 WangLijun$
*/
package com.newtouch.lion.datasource; 

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.AopInfrastructureBean;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

/**
 * <p>
 * Title: 
 * </p>
 * <p>
 * Description: 
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
public class DynamicsPersistenceExceptionTranslationPostProcessor extends PersistenceExceptionTranslationPostProcessor {
	
	

	/** 日志 */
	private static final Logger logger = LoggerFactory.getLogger(DynamicsDataSourceProcessor.class);

	/**
	 * 当之前操作是写的时候，是否强制从从库读 默认（false） 当之前操作是写，默认强制从写库读
	 */
	private boolean force = false;
	/** 读操作匹配方法列表 */
	private Map<String, Boolean> slaveMethodMap = new ConcurrentHashMap<String, Boolean>();
	/**读操作匹配的方法列表－排序列表－降序排序*/
	private List<String>  sortMethods=null;

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 3589188614104388179L;
	
	public Object postProcessAfterInitialization(Object bean, String beanName) {
		if (bean instanceof AopInfrastructureBean) {
			// Ignore AOP infrastructure such as scoped proxies.
			return bean;
		}

		if (bean instanceof Advised) {
			Advised advised = (Advised) bean;
			if (!advised.isFrozen() && isEligible(AopUtils.getTargetClass(bean))) {
				// Add our local Advisor to the existing proxy's Advisor chain...
				if (this.beforeExistingAdvisors) {
					advised.addAdvisor(0, this.advisor);
				}
				else {
					advised.addAdvisor(this.advisor);
				}
				return bean;
			}
		}

		if (isEligible(bean, beanName)) {
			ProxyFactory proxyFactory = new ProxyFactory(bean);
			// Copy our properties (proxyTargetClass etc) inherited from ProxyConfig.
			proxyFactory.copyFrom(this);
			proxyFactory.addAdvisor(this.advisor);
			return proxyFactory.getProxy(this.beanClassLoader);
		}

		
		if (bean instanceof NameMatchTransactionAttributeSource){
			try{
				NameMatchTransactionAttributeSource transactionAttributeSource = (NameMatchTransactionAttributeSource) bean;
				Field nameMapField = ReflectionUtils.findField(NameMatchTransactionAttributeSource.class, "nameMap");
				nameMapField.setAccessible(true);
				@SuppressWarnings("unchecked")
				Map<String, TransactionAttribute> nameMap = (Map<String, TransactionAttribute>) nameMapField.get(transactionAttributeSource);
	
				for (Entry<String, TransactionAttribute> entry : nameMap.entrySet()) {
					RuleBasedTransactionAttribute attr = (RuleBasedTransactionAttribute) entry.getValue();
	
					// 仅对read-only的处理
					String methodName = entry.getKey();
					if (!attr.isReadOnly()) {
						 //仅处理只读数据库
						//this.slaveMethodMap.put(methodName,Boolean.TRUE);
						continue;
					}
			 
					Boolean isForceChoiceRead = Boolean.FALSE;
					if (this.force) {
						// 不管之前操作是写，默认强制从读库读 （设置为NOT_SUPPORTED即可）
						// NOT_SUPPORTED会挂起之前的事务
						attr.setPropagationBehavior(Propagation.NOT_SUPPORTED.value());
						isForceChoiceRead = Boolean.TRUE;
					} else {
						// 否则 设置为SUPPORTS（这样可以参与到写事务）
						attr.setPropagationBehavior(Propagation.SUPPORTS.value());
					}
					logger.debug("read/write transaction process  method:{} force read:{}",methodName, isForceChoiceRead);
					this.slaveMethodMap.put(methodName, isForceChoiceRead);
				}
			}catch(Exception e){
				 
			}
		}
		this.sortMethods=new ArrayList<String>(this.slaveMethodMap.keySet());
		Collections.sort(this.sortMethods,Collections.reverseOrder());
		// No async proxy needed.
		return bean;
	}
	
	public Object determineMasterOrSlave(ProceedingJoinPoint proceedingJoinPoint)
			throws Throwable {

		if (isChoiceSlave(proceedingJoinPoint.getSignature().getName())) {
			DataSourceContextHolder.setSlave();
			logger.info("走读库:{}",proceedingJoinPoint.getSignature().getName());
		} else {
			DataSourceContextHolder.setMaster();
		}

		try {
			return proceedingJoinPoint.proceed();
		} finally {
			DataSourceContextHolder.clear();
		}

	}

	private boolean isChoiceSlave(String methodName) {
		
		String bestNameMatch = null;
		
		for (String mappedName:this.sortMethods) {
			if (isMatch(methodName, mappedName)) {
				bestNameMatch = mappedName;
				break;
			}
		}
		logger.info("bestNameMatch:{}",bestNameMatch);
		//判断是否空，为表示从写数库
	   if(StringUtils.isEmpty(bestNameMatch)){
		   	return false;
	   }
	   //不存在也走写数库,即read-only!=true的情况
	   if(!this.slaveMethodMap.containsKey(bestNameMatch)){
		   return false;
	   }
	   
		Boolean isForceChoiceRead = this.slaveMethodMap.get(bestNameMatch);
		// 表示强制选择 读库
		if (isForceChoiceRead) {
			return true;
		}
		
		// 如果之前选择了写库 现在还选择 写库
		if (DataSourceContextHolder.isMaster()) {
			return false;
		}		
		// 表示应该选择读库
		if (isForceChoiceRead != null) {
			return true;
		}
		// 默认选择 写库
		return false;
	}

	protected boolean isMatch(String methodName, String mappedName) {
		return PatternMatchUtils.simpleMatch(mappedName, methodName);
	}

}

	