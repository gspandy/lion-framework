package com.newtouch.lion.redis.client;

import redis.clients.jedis.JedisCommands;

import java.util.Set;

/**
 * Created by wanglijun on 2015/12/3.
 */
public interface RedisClient  extends JedisCommands {

    /**
     *  删除该key
     * @param key 指定的key
     * @return Long 被成功删除的个数
     */
    Long del(String key);

    /**
     * 功能描述：返回key集合
     *
     * @param pattern 模式
     * @return key集合
     */
    Set<String> keys(String pattern);


}
