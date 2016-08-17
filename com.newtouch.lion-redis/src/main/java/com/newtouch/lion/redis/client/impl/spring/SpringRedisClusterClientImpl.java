/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: SpringRedisClusterClientImpl.java 9552 2015年7月11日 下午12:11:52 WangLijun$
 */
package com.newtouch.lion.redis.client.impl.spring;

import com.newtouch.lion.redis.client.RedisClient;
import com.newtouch.lion.redis.cluster.JedisClusterConfig;
import redis.clients.jedis.ZParams;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * Title: Spring Redis Cluster 实现
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
public class SpringRedisClusterClientImpl extends DefaultSpringClusterClientImpl implements RedisClient {

	/***
	 * 实始化函数
	 * 
	 * @param jedisClusterConfig
	 */
	public SpringRedisClusterClientImpl(JedisClusterConfig jedisClusterConfig) {
		super(jedisClusterConfig);
		this.jedisClusterConfig = jedisClusterConfig;
	}

	@Override
	public Long push(String key, String... fields) {
		 
		return  null;
	}

	@Override
	public String mset(Map<String, String> keyValues) {
		// TODO Auto-generated method stub
		return  null;
	}

	@Override
	public Set<String> keys(String pattern) {
		return null;
	}

	@Override
	public List<String> mget(String... keys) {
		
		return null;
	}

	@Override
	public void refresh(String config) {
		
	}

	@Override
	public Set<String> sinter(String... keys) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long sinterstore(String dstkey, String... keys) {
		
		return null;
	}

	@Override
	public Set<String> sunion(String... keys) {
		
		return null;
	}

	@Override
	public Long sunionstore(String dstkey, String... keys) {
		
		return null;
	}

	@Override
	public Set<String> sdiff(String... keys) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long sdiffstore(String dstkey, String... keys) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long zinterstore(String dstkey, String... sets) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long zinterstore(String dstkey, ZParams params, String... sets) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long zunionstore(String dstkey, String... sets) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long zunionstore(String dstkey, ZParams params, String... sets) {
		// TODO Auto-generated method stub
		return null;
	}
}
