/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: JedisClusterConfig.java 9552 2015年7月11日 下午12:01:55 WangLijun$
*/
package com.newtouch.lion.redis.cluster;

import org.springframework.beans.factory.InitializingBean;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * Title: Redis 集群配置信息
 * </p>
 * <p>
 * Description:  主要配置连接池和连接超时间
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
public class JedisClusterConfig  implements InitializingBean {
	/***集群连接*/
	private  static Set<HostAndPort> jedisClusterNode = new HashSet<HostAndPort>();
	/**地址信息 格式为：127.0.0.1:6379,127.0.0.2:6379,127.0.0.3:6379 类似于：Zeekeeper连接地址*/
	private String address;
	/***连接超时*/
	private int timeout;
	/***/
	private int maxRedirections;
	/**Redis 连接池配置*/
	private JedisPoolConfig poolConfig;
	/**Redis Cluster*/
	/**默认*/
	public JedisClusterConfig() {
		super();
	}
	
	/**
	 * @param address
	 * @param timeout
	 * @param maxRedirections
	 * @param poolConfig
	 */
	public JedisClusterConfig(String address, int timeout, int maxRedirections,
			JedisPoolConfig poolConfig) {
		this.address = address;
		this.timeout = timeout;
		this.maxRedirections = maxRedirections;
		this.poolConfig = poolConfig;
	}
	
	/**初始化信息*/
	protected void initClusterNode(){
		this.paserAddress();
	}
	
	protected void paserAddress(){
	   String hostsList[] = this.address.split(",");
       for (String host : hostsList) {
            int port =Protocol.DEFAULT_PORT;
            int pidx = host.lastIndexOf(':');
            if (pidx >= 0) {
                if (pidx < host.length() - 1) {
                    port = Integer.parseInt(host.substring(pidx + 1));
                }
                host = host.substring(0, pidx);
            }
            jedisClusterNode.add(new HostAndPort(host,port));
        }
	}


	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}


	/**
	 * @return the timeout
	 */
	public int getTimeout() {
		return timeout;
	}


	/**
	 * @param timeout the timeout to set
	 */
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}


	/**
	 * @return the maxRedirections
	 */
	public int getMaxRedirections() {
		return maxRedirections;
	}


	/**
	 * @param maxRedirections the maxRedirections to set
	 */
	public void setMaxRedirections(int maxRedirections) {
		this.maxRedirections = maxRedirections;
	}


	/**
	 * @return the poolConfig
	 */
	public JedisPoolConfig getPoolConfig() {
		return poolConfig;
	}


	/**
	 * @param poolConfig the poolConfig to set
	 */
	public void setPoolConfig(JedisPoolConfig poolConfig) {
		this.poolConfig = poolConfig;
	}
	
	 

	/**
	 * @return the jedisClusterNode
	 */
	public  Set<HostAndPort> getJedisClusterNode() {
		return jedisClusterNode;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		//初始化节点信息
		this.initClusterNode();
	}


	 
}

	