package com.newtouch.lion.redis.client;
import redis.clients.jedis.Jedis;
/**
 * Created by wanglijun on 2015/12/3.
 */
public interface CallBack<R> {
    /***
     * 执行回调方法，调用jedis实例 ，返回相应操作的结果，R为返回值，可能为String,Object等
     * @param jedis
     * @return result
     */
    R invoke(Jedis jedis);
}
