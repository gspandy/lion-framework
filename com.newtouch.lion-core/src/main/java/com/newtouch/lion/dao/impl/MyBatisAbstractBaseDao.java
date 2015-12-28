/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: MyBatisBaseDaoImpl.java 9552 2015年9月28日 上午11:52:27 WangLijun$
*/
package com.newtouch.lion.dao.impl;

import java.lang.reflect.ParameterizedType;

import javax.annotation.PostConstruct;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * Title: MyBatis BaseDao 实现类
 * </p>
 * <p>
 * Description: MyBatis BaseDao 实现类，扩展Dao层功能
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
public abstract class MyBatisAbstractBaseDao<T> extends SqlSessionDaoSupport {
	/**日志*/
	protected final  Logger logger=LoggerFactory.getLogger(super.getClass());
	
	/**MyBatis的Mapper*/
	protected T mapper;
	
	 @Autowired
	 public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate){
		  super.setSqlSessionTemplate(sqlSessionTemplate);
	 }
	 
	 
	 
	@PostConstruct
	 public void init(){
		 @SuppressWarnings("unchecked")
		Class<T>  mapperType=(Class<T>)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		
		logger.info("init ... {}",mapperType.getName());
		 //初始化映射器属性
		this.mapper = this.getSqlSession().getMapper(mapperType);
	 }
 
}

	