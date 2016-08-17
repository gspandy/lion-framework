/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: DefaultSpringClusterClientImpl.java 9552 2015年7月11日 下午2:19:20 WangLijun$
 */
package com.newtouch.lion.redis.client.impl.spring;

import com.newtouch.lion.redis.cluster.JedisClusterConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisCluster;

/**
 * <p>
 * Title: Redis Cluster Client 默认访问
 * </p>
 * <p>
 * Description: Redis Cluster Client 默认访问
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
public class DefaultSpringClusterClientImpl extends JedisCluster {

	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(DefaultSpringClusterClientImpl.class);
	/** Cluster 连接配置 **/
	protected JedisClusterConfig jedisClusterConfig;
	
	public DefaultSpringClusterClientImpl(JedisClusterConfig jedisClusterConfig) {
		super(jedisClusterConfig.getJedisClusterNode(), jedisClusterConfig.getTimeout(), jedisClusterConfig.getMaxRedirections(),jedisClusterConfig.getPoolConfig());
		logger.info("init redis cluster client...");
		this.jedisClusterConfig=jedisClusterConfig;
	}
	/**
	 * @return the jedisClusterConfig
	 */
	public JedisClusterConfig getJedisClusterConfig() {
		return jedisClusterConfig;
	}

	/**
	 * @param jedisClusterConfig
	 *            the jedisClusterConfig to set
	 */
	public void setJedisClusterConfig(JedisClusterConfig jedisClusterConfig) {
		this.jedisClusterConfig = jedisClusterConfig;
	}
	
	

	/* (non-Javadoc)
	 * @see com.newtouch.lion.redis.client.IRedisClient#clearDisableFlags()
	 */
	public void clearDisableFlags() {
		
	}
	

}
