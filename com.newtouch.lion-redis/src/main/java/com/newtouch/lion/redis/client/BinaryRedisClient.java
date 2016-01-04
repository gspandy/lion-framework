package com.newtouch.lion.redis.client;

import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * Created by wanglijun on 2015/12/3.
 */
public interface BinaryRedisClient {
    /*
     * 功能描述: <br> 刷新配置，重建连接池
     * @param config void refresh(String config);
     */

    /*
     * 功能描述: <br> 将字符串值 value 关联到 key 。 如果 key 已经持有其他值， SET 就覆写旧值，无视类型。 对于某个原本带有生存时间的键来说， 当 SET 命令成功在这个键上执行时， 这个键原有的 TTL
     * 将被清除。
     * @param key
     * @param value
     * @return

     * @since [产品/模块版本](可选) String set(final Serializable key, final Serializable value);
     */



    /**
     * Set the string value as value of the key. The string can't be longer than
     * 1073741824 bytes (1 GB).
     * @param key
     * @param value
     * @param nxxx NX|XX, NX -- Only set the key if it does not already exist.
     *                    XX -- Only set the key if it already exist.
     * @param expx EX|PX, expire time units: EX = seconds; PX = milliseconds
     * @param time expire time in the units of {@param #expx}
     * @return Status code reply
     */
    String set(final String key, final String value, final String nxxx, final String expx, final long time);



    /**
     *
     * 功能描述: 此API用于存取集合类型的数据，比如：Map<String,Object>, List<Object> 客户端的使用方式（示例）为：redisClient.get("user",new
     * TypeReference<Map<String, long[]>>(){}); 对应的set方式为：public <T> void set(final String key, final T value)
     *
     * @param key 键值
     * @param type 集合类型
     * @param <T> 泛型对象
     * @return 集合类型

     * @since [产品/模块版本](可选)
     */
    <T> T get(String key, TypeReference<T> type);

    /**
     *
     * 功能描述: 用于存放JAVA简单的类对象的数据，如:put(userId,user) 〈功能详细描述〉
     *
     * @param key 键值
     * @param value 简单的类对象
     * @param <T> 泛型对象

     * @since [产品/模块版本](可选)
     */
    <T> void set(String key, T value);

    String setObject(final String key, final Object value);

    /**
     * 设置集合对象
     *
     * @param key 键值
     * @param value 集合对象
     * @param time 过期时间
     * @param <T> 泛型对象
     */
    <T> void setex(String key, T value, int time);

    String setexObject(final String key, final Object value, final int time);

    /**
     *
     * 功能描述: 用于访问JAVA简单的类对象的数据，如:get(userId,User.class) 〈功能详细描述〉
     *
     * @param key 键值
     * @param value 简单的类对象
     * @param <T> 泛型对象
     * @return JAVA简单的类对象

     * @since [产品/模块版本](可选)
     */
    <T> T get(String key, Class<T> value);

    Object getObject(final String key);

    /**
     * 移除对象
     *
     * @param key 移出指定的对象
     */
    void remove(String key);

    void removeObject(final String key);

    Long hsetObject(final String key, final String field, final Object value);

    Object hgetObject(final String key, final String field);

    void hdelObject(final String key, final String field);

    Map<String, Object> hgetAllObjects(final String key);

    Long expire(final String id, final int seconds);
}
