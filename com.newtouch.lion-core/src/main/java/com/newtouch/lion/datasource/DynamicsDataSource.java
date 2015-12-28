/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: DynamicsDataSource.java 9552 2015年10月9日 下午12:05:54 WangLijun$
 */
package com.newtouch.lion.datasource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.util.CollectionUtils;

/**
 * <p>
 * Title: 动态数据源切换
 * </p>
 * <p>
 * Description: 动态数据源切换（实现动态分离）
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
public class DynamicsDataSource extends AbstractDataSource implements InitializingBean {
	/***
	 * 日志
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(DynamicsDataSource.class);
	/** 主数据源-写 */
	private DataSource masterDataSource;
	/** 从数据源-读(为多个) */
	private Map<String, DataSource> slaveDataSourceMap = new ConcurrentHashMap<String, DataSource>();
	/** 从数据源－名称 */
	private String[] slaveDataSourceNames;
	/** 从数据源-实例 */
	private DataSource[] slaveDataSources;
	/** 从数据源数量 */
	private int slaveDataSourceCount;
	/** 计数据器 */
	private AtomicInteger counter = new AtomicInteger(0);

	/**
	 * @param masterDataSource  the masterDataSource to set
	 */
	public void setMasterDataSource(DataSource masterDataSource) {
		this.masterDataSource = masterDataSource;
	}

	/**
	 * @param slaveDataSourceMap
	 *            the slaveDataSourceMap to set
	 */
	public void setSlaveDataSourceMap(Map<String, DataSource> slaveDataSourceMap) {
		this.slaveDataSourceMap = slaveDataSourceMap;
	}

	@Override
	public Connection getConnection() throws SQLException {
		return this.determineDataSource().getConnection();
	}

	@Override
	public Connection getConnection(String username, String password)throws SQLException {
		return this.determineDataSource().getConnection(username, password);
	}
	
	 private DataSource determineDataSource() {
	        if(DataSourceContextHolder.isMaster()) {
	            logger.debug("current determine write datasource");
	            return this.masterDataSource;
	        }
	        
	        if(DataSourceContextHolder.isNull()) {
	            logger.debug("no choice read/write, default determine write datasource");
	            return this.masterDataSource;
	        } 
	        return determineSlaveDataSource();
	    }
	    
    private DataSource determineSlaveDataSource() {
        //按照顺序选择读库 
        //算法改进  
        int index =this.counter.incrementAndGet() % this.slaveDataSourceCount;
        if(index < 0) {
            index = - index;
        }
        String dataSourceName = this.slaveDataSourceNames[index];
        logger.debug("current determine read datasource : {}", dataSourceName);
        return this.slaveDataSources[index];
    }

	@Override
	public void afterPropertiesSet() throws Exception {
		if (masterDataSource == null) {
			throw new IllegalArgumentException("property 'masterDataSource' is required");
		}
		if (CollectionUtils.isEmpty(slaveDataSourceMap)) {
			throw new IllegalArgumentException("property 'slaveDataSourceMap' is required");
		}
		this.slaveDataSourceCount = this.slaveDataSourceMap.size();

		this.slaveDataSources = new DataSource[this.slaveDataSourceCount];
		this.slaveDataSourceNames = new String[this.slaveDataSourceCount];

		int i = 0;
		for (Entry<String, DataSource> e : slaveDataSourceMap.entrySet()) {
			this.slaveDataSources[i] = e.getValue();
			this.slaveDataSourceNames[i] = e.getKey();
			i++;
		}
	}

}
