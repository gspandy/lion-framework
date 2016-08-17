/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: SpringBinaryRedisClusterClientImpl.java 9552 2015年7月11日 下午12:23:00 WangLijun$
*/
package com.newtouch.lion.redis.client.impl.spring;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.newtouch.lion.redis.client.BinaryRedisClient;
import com.newtouch.lion.redis.cluster.BinaryJedisCluster;
import com.newtouch.lion.redis.cluster.JedisClusterConfig;
import com.newtouch.lion.redis.util.ByteUtils;
import com.newtouch.lion.redis.util.SessionStreamUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * <p>
 * Title: RedisCluster 的Binary操作接口实现
 * </p>
 * <p>
 * Description: RedisCluster 的Binary操作接口实现
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
public class SpringBinaryRedisClusterClientImpl<T> extends BinaryJedisCluster implements BinaryRedisClient {
	/**日志*/
	private static final Logger logger=LoggerFactory.getLogger(SpringBinaryRedisClusterClientImpl.class);
	
	
	public SpringBinaryRedisClusterClientImpl(JedisClusterConfig jedisClusterConfig) {
		super(jedisClusterConfig); 
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.redis.client.IBinaryRedisClient#get(java.lang.String, com.alibaba.fastjson.TypeReference)
	 */
	@SuppressWarnings("hiding")
	@Override
	public <T> T get(String key, TypeReference<T> type) {
		 return JSON.parseObject(super.get(key), type);
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.redis.client.IBinaryRedisClient#set(java.lang.String, java.lang.Object)
	 */
	@SuppressWarnings("hiding")
	@Override
	public <T> void set(String key, T value) {
		 super.set(key, JSON.toJSONString(value));
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.redis.client.IBinaryRedisClient#setObject(java.lang.String, java.lang.Object)
	 */
	@Override
	public String setObject(String key, Object value) {
 
		return super.set(key, JSON.toJSONString(value));
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.redis.client.IBinaryRedisClient#setex(java.lang.String, java.lang.Object, int)
	 */
	@SuppressWarnings("hiding")
	@Override
	public <T> void setex(String key, T value, int time) {
		 super.setex(key, time, JSON.toJSONString(value));
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.redis.client.IBinaryRedisClient#setexObject(java.lang.String, java.lang.Object, int)
	 */
	@Override
	public String setexObject(String key, Object value, int time) {
		
		try {
			byte[] obj = SessionStreamUtils.objectToByteArray(value);
			return this.setex(key, time, obj);
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.redis.client.IBinaryRedisClient#get(java.lang.String, java.lang.Class)
	 */
	@SuppressWarnings("hiding")
	@Override
	public <T> T get(String key, Class<T> value) {
		return JSON.parseObject(super.get(key), value);
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.redis.client.IBinaryRedisClient#getObject(java.lang.String)
	 */
	@Override
	public Object getObject(String key) {
		 try {
			return SessionStreamUtils.byteArrayToObject(super.getBytes(key));
		} catch (IOException e) {
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.redis.client.IBinaryRedisClient#remove(java.lang.String)
	 */
	@Override
	public void remove(String key) {
		 super.del(key);
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.redis.client.IBinaryRedisClient#removeObject(java.lang.String)
	 */
	@Override
	public void removeObject(String key) {
		super.del(key);
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.redis.client.IBinaryRedisClient#hsetObject(java.lang.String, java.lang.String, java.lang.Object)
	 */
	@Override
	public Long hsetObject(String key, String field, Object value) {
		
		return super.hset(key, field,ByteUtils.objectToBytes(value));
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.redis.client.IBinaryRedisClient#hgetObject(java.lang.String, java.lang.String)
	 */
	@Override
	public Object hgetObject(String key, String field) {
		return ByteUtils.bytesToObject(super.hgetBytes(key, field));
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.redis.client.IBinaryRedisClient#hdelObject(java.lang.String, java.lang.String)
	 */
	@Override
	public void hdelObject(String key, String field) {
		super.hdel(key, field);
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.redis.client.IBinaryRedisClient#hgetAllObjects(java.lang.String)
	 */
	@Override
	public Map<String, Object> hgetAllObjects(String key) {
		Map<byte[], byte[]> all=super.hgetAllBytes(key);
	    Map<String, Object> allObjs = new HashMap<String, Object>();
		if(!CollectionUtils.isEmpty(all)){
			 for (Entry<byte[], byte[]> item : all.entrySet()) {
                 if (item.getKey() != null && item.getValue() != null && item != null) {
                     String _key =new String(ByteUtils.objectToBytes(item.getKey()));
                     Object _value = ByteUtils.objectToBytes(item.getValue());
                     allObjs.put(_key, _value);
                 } else {
                     logger.debug(item.getKey() + ":" + item.getValue());
                 }
             }
		}
		return allObjs;
	}
}

	