package com.newtouch.lion.redis.client.impl;

import com.newtouch.lion.redis.client.CallBack;
import com.newtouch.lion.redis.client.RedisClient;
import redis.clients.jedis.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * Title:默认的String操作接口实现
 * </p>
 * <p>
 * Description:默认的String操作接口实现
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 *
 * @author wanglijun
 * @version 1.0
 *          2015-12-09 16:26.
 */
public class RedisClientImpl extends DefaultClientImpl implements RedisClient{


    /**
     * 构造方法
     */
    public RedisClientImpl() {
    }


    /**
     * 构造方法
     *
     * @param config 配置信息
     */
    public RedisClientImpl(String config) {
        super(config);
    }

    /**
     * 删除该key
     * @param key 指定的key
     * @return Long 被成功删除的个数
     */
    @Override
    public Long del(final String key) {
        return this.performFunction(key, new CallBack<Long>() {
            @Override
            public Long invoke(Jedis jedis) {
                return  jedis.del(key);
            }
        });
    }

    /***
     *
     * @param key KEY
     * @param callBack 回调函数
     * @param <R>
    * @return
            */
    protected  <R> R performFunction(String key,CallBack<R>   callBack){
        return this.shardedJedis.getShard(key).execute(callBack);
    }


    /**
     * 功能描述： 批量获取数据
     *
     * @param keys 指定的key
     * @return 数据集合
     */
    @Override
    public List<String> mget(String... keys) {
        return null;
    }

    /****
     * 功能描述：push
     *
     * @param key    指定的key
     * @param fields 被插入的元素
     * @return Long 被成功插入的个数
     */
    @Override
    public Long push(String key, String... fields) {
        return null;
    }

    /**
     * 功能描述： 批量set数据
     *
     * @param keyValues 键值对
     * @return set成功与否的状态码
     */
    @Override
    public String mset(Map<String, String> keyValues) {
        return null;
    }

    /**
     * 功能描述: <br>
     * 求多个集合的交集
     *
     * @param keys 指定的集合
     * @return 交集的集合，不存在时为空
     */
    @Override
    public Set<String> sinter(String... keys) {
        return null;
    }

    /**
     * 功能描述: 求多个集合的交集，并把结果存在dstkey中，dstkey已存在则覆盖
     *
     * @param dstkey 结果集
     * @param keys   目标集
     * @return 结果集中的成员数量。
     */
    @Override
    public Long sinterstore(String dstkey, String... keys) {
        return null;
    }

    /**
     * 功能描述: <br>
     * 求多个集合的并集
     *
     * @param keys 目标集
     * @return 并集的集合，不存在时为空
     */
    @Override
    public Set<String> sunion(String... keys) {
        return null;
    }

    /**
     * 功能描述: <br>
     * 求多个集合的并集，并把结果存在dstkey中，dstkey已存在则覆盖
     *
     * @param dstkey 结果及
     * @param keys   目标集
     * @return 结果集中的成员数量。
     */
    @Override
    public Long sunionstore(String dstkey, String... keys) {
        return null;
    }

    /**
     * 功能描述: <br>
     * 求参数里第一个set与其他set的差集
     *
     * @param keys 目标集
     * @return 差集的集合，不存在时为空
     */
    @Override
    public Set<String> sdiff(String... keys) {
        return null;
    }

    /**
     * 功能描述: <br>
     * 求参数里第一个set与其他set的差集，并把结果存在dstkey中，dstkey已存在则覆盖
     *
     * @param dstkey 结果集
     * @param keys   目标集
     * @return 结果集中的成员数量。
     */
    @Override
    public Long sdiffstore(String dstkey, String... keys) {
        return null;
    }

    /**
     * 功能描述: <br>
     * 求多个有序集合的交集，并把结果存在dstkey中，dstkey已存在则覆盖 score值默认按照权重为1，累加。
     *
     * @param dstkey 结果集
     * @param sets   目标集
     * @return 结果集中的成员数量。
     */
    @Override
    public Long zinterstore(String dstkey, String... sets) {
        return null;
    }

    /**
     * 功能描述: <br>
     * 求多个有序集合的交集，并把结果存在dstkey中，dstkey已存在则覆盖 score值按照params参数中设置的权重和算法计算
     *
     * @param dstkey 结果集
     * @param params 权重和算法参数
     * @param sets   目标集
     * @return 结果集中的成员数量
     */
    @Override
    public Long zinterstore(String dstkey, ZParams params, String... sets) {
        return null;
    }

    /**
     * 功能描述: <br>
     * 求多个有序集合的并集，并把结果存在dstkey中，dstkey已存在则覆盖 score值默认按照权重为1，累加。
     *
     * @param dstkey 结果集
     * @param sets   目标集
     * @return 结果集中的成员数量。
     */
    @Override
    public Long zunionstore(String dstkey, String... sets) {
        return null;
    }

    /**
     * 功能描述: <br>
     * 求多个有序集合的并集，并把结果存在dstkey中，dstkey已存在则覆盖 score值按照params参数中设置的权重和算法计算
     *
     * @param dstkey 结果集
     * @param params 权重和算法参数
     * @param sets   目标集
     * @return 结果集中的成员数量。
     */
    @Override
    public Long zunionstore(String dstkey, ZParams params, String... sets) {
        return null;
    }

    @Override
    public void clearDisableFlags() {
        super.clearDisableFlags();
    }

    @Override
    protected boolean isSharding() {
        return super.isSharding();
    }

    @Override
    public synchronized void refresh(String config) {
        super.refresh(config);
    }

    @Override
    public String flushDB() {
        return super.flushDB();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public Set<String> keys(String pattern) {
        return null;
    }

    @Override
    public String set(final String key,final String value) {
        return  this.performFunction(key, new CallBack<String>() {
            @Override
            public String invoke(Jedis jedis) {
                return jedis.set(key,value);
            }
        });
    }

    @Override
    public String set(String key, String value, String nxxx, String expx, long time) {
        return null;
    }

    @Override
    public String get(String key) {
        return null;
    }

    @Override
    public Boolean exists(String key) {
        return null;
    }

    @Override
    public Long persist(String key) {
        return null;
    }

    @Override
    public String type(String key) {
        return null;
    }

    @Override
    public Long expire(String key, int seconds) {
        return null;
    }

    @Override
    public Long pexpire(String key, long milliseconds) {
        return null;
    }

    @Override
    public Long expireAt(String key, long unixTime) {
        return null;
    }

    @Override
    public Long pexpireAt(String key, long millisecondsTimestamp) {
        return null;
    }

    @Override
    public Long ttl(String key) {
        return null;
    }

    @Override
    public Boolean setbit(String key, long offset, boolean value) {
        return null;
    }

    @Override
    public Boolean setbit(String key, long offset, String value) {
        return null;
    }

    @Override
    public Boolean getbit(String key, long offset) {
        return null;
    }

    @Override
    public Long setrange(String key, long offset, String value) {
        return null;
    }

    @Override
    public String getrange(String key, long startOffset, long endOffset) {
        return null;
    }

    @Override
    public String getSet(String key, String value) {
        return null;
    }

    @Override
    public Long setnx(String key, String value) {
        return null;
    }

    @Override
    public String setex(String key, int seconds, String value) {
        return null;
    }

    @Override
    public Long decrBy(String key, long integer) {
        return null;
    }

    @Override
    public Long decr(String key) {
        return null;
    }

    @Override
    public Long incrBy(String key, long integer) {
        return null;
    }

    @Override
    public Double incrByFloat(String key, double value) {
        return null;
    }

    @Override
    public Long incr(String key) {
        return null;
    }

    @Override
    public Long append(String key, String value) {
        return null;
    }

    @Override
    public String substr(String key, int start, int end) {
        return null;
    }

    @Override
    public Long hset(String key, String field, String value) {
        return null;
    }

    @Override
    public String hget(String key, String field) {
        return null;
    }

    @Override
    public Long hsetnx(String key, String field, String value) {
        return null;
    }

    @Override
    public String hmset(String key, Map<String, String> hash) {
        return null;
    }

    @Override
    public List<String> hmget(String key, String... fields) {
        return null;
    }

    @Override
    public Long hincrBy(String key, String field, long value) {
        return null;
    }

    @Override
    public Boolean hexists(String key, String field) {
        return null;
    }

    @Override
    public Long hdel(String key, String... field) {
        return null;
    }

    @Override
    public Long hlen(String key) {
        return null;
    }

    @Override
    public Set<String> hkeys(String key) {
        return null;
    }

    @Override
    public List<String> hvals(String key) {
        return null;
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        return null;
    }

    @Override
    public Long rpush(String key, String... string) {
        return null;
    }

    @Override
    public Long lpush(String key, String... string) {
        return null;
    }

    @Override
    public Long llen(String key) {
        return null;
    }

    @Override
    public List<String> lrange(String key, long start, long end) {
        return null;
    }

    @Override
    public String ltrim(String key, long start, long end) {
        return null;
    }

    @Override
    public String lindex(String key, long index) {
        return null;
    }

    @Override
    public String lset(String key, long index, String value) {
        return null;
    }

    @Override
    public Long lrem(String key, long count, String value) {
        return null;
    }

    @Override
    public String lpop(String key) {
        return null;
    }

    @Override
    public String rpop(String key) {
        return null;
    }

    @Override
    public Long sadd(String key, String... member) {
        return null;
    }

    @Override
    public Set<String> smembers(String key) {
        return null;
    }

    @Override
    public Long srem(String key, String... member) {
        return null;
    }

    @Override
    public String spop(String key) {
        return null;
    }

    @Override
    public Set<String> spop(String key, long count) {
        return null;
    }

    @Override
    public Long scard(String key) {
        return null;
    }

    @Override
    public Boolean sismember(String key, String member) {
        return null;
    }

    @Override
    public String srandmember(String key) {
        return null;
    }

    @Override
    public List<String> srandmember(String key, int count) {
        return null;
    }

    @Override
    public Long strlen(String key) {
        return null;
    }

    @Override
    public Long zadd(String key, double score, String member) {
        return null;
    }

    @Override
    public Long zadd(String key, Map<String, Double> scoreMembers) {
        return null;
    }

    @Override
    public Set<String> zrange(String key, long start, long end) {
        return null;
    }

    @Override
    public Long zrem(String key, String... member) {
        return null;
    }

    @Override
    public Double zincrby(String key, double score, String member) {
        return null;
    }

    @Override
    public Long zrank(String key, String member) {
        return null;
    }

    @Override
    public Long zrevrank(String key, String member) {
        return null;
    }

    @Override
    public Set<String> zrevrange(String key, long start, long end) {
        return null;
    }

    @Override
    public Set<Tuple> zrangeWithScores(String key, long start, long end) {
        return null;
    }

    @Override
    public Set<Tuple> zrevrangeWithScores(String key, long start, long end) {
        return null;
    }

    @Override
    public Long zcard(String key) {
        return null;
    }

    @Override
    public Double zscore(String key, String member) {
        return null;
    }

    @Override
    public List<String> sort(String key) {
        return null;
    }

    @Override
    public List<String> sort(String key, SortingParams sortingParameters) {
        return null;
    }

    @Override
    public Long zcount(String key, double min, double max) {
        return null;
    }

    @Override
    public Long zcount(String key, String min, String max) {
        return null;
    }

    @Override
    public Set<String> zrangeByScore(String key, double min, double max) {
        return null;
    }

    @Override
    public Set<String> zrangeByScore(String key, String min, String max) {
        return null;
    }

    @Override
    public Set<String> zrevrangeByScore(String key, double max, double min) {
        return null;
    }

    @Override
    public Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
        return null;
    }

    @Override
    public Set<String> zrevrangeByScore(String key, String max, String min) {
        return null;
    }

    @Override
    public Set<String> zrangeByScore(String key, String min, String max, int offset, int count) {
        return null;
    }

    @Override
    public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
        return null;
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
        return null;
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min) {
        return null;
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count) {
        return null;
    }

    @Override
    public Set<String> zrevrangeByScore(String key, String max, String min, int offset, int count) {
        return null;
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max) {
        return null;
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min) {
        return null;
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max, int offset, int count) {
        return null;
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count) {
        return null;
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min, int offset, int count) {
        return null;
    }

    @Override
    public Long zremrangeByRank(String key, long start, long end) {
        return null;
    }

    @Override
    public Long zremrangeByScore(String key, double start, double end) {
        return null;
    }

    @Override
    public Long zremrangeByScore(String key, String start, String end) {
        return null;
    }

    @Override
    public Long zlexcount(String key, String min, String max) {
        return null;
    }

    @Override
    public Set<String> zrangeByLex(String key, String min, String max) {
        return null;
    }

    @Override
    public Set<String> zrangeByLex(String key, String min, String max, int offset, int count) {
        return null;
    }

    @Override
    public Set<String> zrevrangeByLex(String key, String max, String min) {
        return null;
    }

    @Override
    public Set<String> zrevrangeByLex(String key, String max, String min, int offset, int count) {
        return null;
    }

    @Override
    public Long zremrangeByLex(String key, String min, String max) {
        return null;
    }

    @Override
    public Long linsert(String key, BinaryClient.LIST_POSITION where, String pivot, String value) {
        return null;
    }

    @Override
    public Long lpushx(String key, String... string) {
        return null;
    }

    @Override
    public Long rpushx(String key, String... string) {
        return null;
    }

    /**
     * @param arg
     * @deprecated unusable command, this will be removed in 3.0.0.
     */
    @Override
    public List<String> blpop(String arg) {
        return null;
    }

    @Override
    public List<String> blpop(int timeout, String key) {
        return null;
    }

    /**
     * @param arg
     * @deprecated unusable command, this will be removed in 3.0.0.
     */
    @Override
    public List<String> brpop(String arg) {
        return null;
    }

    @Override
    public List<String> brpop(int timeout, String key) {
        return null;
    }

    @Override
    public String echo(String string) {
        return null;
    }

    @Override
    public Long move(String key, int dbIndex) {
        return null;
    }

    @Override
    public Long bitcount(String key) {
        return null;
    }

    @Override
    public Long bitcount(String key, long start, long end) {
        return null;
    }

    @Override
    public ScanResult<Map.Entry<String, String>> hscan(String key, int cursor) {
        return null;
    }

    @Override
    public ScanResult<String> sscan(String key, int cursor) {
        return null;
    }

    @Override
    public ScanResult<Tuple> zscan(String key, int cursor) {
        return null;
    }

    @Override
    public ScanResult<Map.Entry<String, String>> hscan(String key, String cursor) {
        return null;
    }

    @Override
    public ScanResult<String> sscan(String key, String cursor) {
        return null;
    }

    @Override
    public ScanResult<Tuple> zscan(String key, String cursor) {
        return null;
    }

    @Override
    public Long pfadd(String key, String... elements) {
        return null;
    }

    @Override
    public long pfcount(String key) {
        return 0;
    }
}
