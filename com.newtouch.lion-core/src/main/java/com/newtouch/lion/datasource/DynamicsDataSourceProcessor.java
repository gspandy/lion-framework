/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: MasterSlaveDataSourceProcessor.java 9552 2015年10月9日 下午2:52:55 WangLijun$
 */
package com.newtouch.lion.datasource;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.ReflectionUtils;

/**
 * <p>
 * Title: 数据源读写分离
 * </p>
 * <p>
 * Description: 数据源读写分离（所以读方法必须是read-only（必须，以此来判断是否是读方法）。）
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
public class DynamicsDataSourceProcessor implements BeanPostProcessor {

	/** 日志 */
	private static final Logger logger = LoggerFactory.getLogger(DynamicsDataSourceProcessor.class);

	/**
	 * 当之前操作是写的时候，是否强制从从库读 默认（false） 当之前操作是写，默认强制从写库读
	 */
	private boolean force = false;
	/** 读操作匹配方法列表 */
	private Map<String, Boolean> slaveMethodMap = new ConcurrentHashMap<String, Boolean>();

	/**
	 * 当之前操作是写的时候，是否强制从从库读 默认（false） 当之前操作是写，默认强制从写库读
	 * 
	 * @param force
	 *            the force to set
	 */
	public void setForce(boolean force) {
		this.force = force;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.config.BeanPostProcessor#
	 * postProcessBeforeInitialization(java.lang.Object, java.lang.String)
	 */
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.config.BeanPostProcessor#
	 * postProcessAfterInitialization(java.lang.Object, java.lang.String)
	 */
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		if (!(bean instanceof NameMatchTransactionAttributeSource)) {
			return bean;
		}

		try {
			NameMatchTransactionAttributeSource transactionAttributeSource = (NameMatchTransactionAttributeSource) bean;
			Field nameMapField = ReflectionUtils.findField(NameMatchTransactionAttributeSource.class, "nameMap");
			nameMapField.setAccessible(true);
			@SuppressWarnings("unchecked")
			Map<String, TransactionAttribute> nameMap = (Map<String, TransactionAttribute>) nameMapField
					.get(transactionAttributeSource);

			for (Entry<String, TransactionAttribute> entry : nameMap.entrySet()) {
				RuleBasedTransactionAttribute attr = (RuleBasedTransactionAttribute) entry.getValue();

				// 仅对read-only的处理
				String methodName = entry.getKey();
				if (!attr.isReadOnly()) {
					slaveMethodMap.put(methodName,Boolean.FALSE);
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
				slaveMethodMap.put(methodName, isForceChoiceRead);
			}

		} catch (Exception e) {
			throw new DynamicsDataSourceTransactionException("process read/write transaction error", e);
		}

		return bean;
	}

	public Object determineMasterOrSlave(ProceedingJoinPoint proceedingJoinPoint)
			throws Throwable {

		if (isChoiceSlave(proceedingJoinPoint.getSignature().getName())) {
			DataSourceContextHolder.setSlave();
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
		for (String mappedName : this.slaveMethodMap.keySet()) {
			if (isMatch(methodName, mappedName)) {
				bestNameMatch = mappedName;
				break;
			}
		}

		Boolean isForceChoiceRead = slaveMethodMap.get(bestNameMatch);
		// 表示强制选择 读 库
		if (isForceChoiceRead == Boolean.TRUE) {
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
