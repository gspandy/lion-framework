package com.newtouch.lion.redis.client;

import redis.clients.jedis.JedisCommands;

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


}
