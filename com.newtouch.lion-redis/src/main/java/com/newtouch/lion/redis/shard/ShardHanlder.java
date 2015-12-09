package com.newtouch.lion.redis.shard;

import com.newtouch.lion.redis.client.CallBack;
import com.newtouch.lion.redis.config.ConfigManager;
import com.newtouch.lion.redis.exception.RedisClientException;
import com.newtouch.lion.redis.pool.DefaultJedisPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisException;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * <p>
 * Title:处理一个分片中的所有操作的类
 * </p>
 * <p>
 * Description:处理一个分片中的所有操作的类
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
 *          2015-12-09 11:13.
 */
public class ShardHanlder {
    /**日志*/
    private static final Logger logger= LoggerFactory.getLogger(ShardHanlder.class);
    /**标记同一shard另一节点是否可用的key*/
    private static  final  String REDIS_UNAVAILABLE_SIGN = "REDIS_UNAVAILABLE_SIGN";

    /**
     * 该shard所有节点的pool，包含不可用的pool
     */
    private List<DefaultJedisPool> pools = new ArrayList<DefaultJedisPool>();
    /**
     * 该shard不可用的pool
     */
    private volatile Set<DefaultJedisPool> errorPools = new HashSet<DefaultJedisPool>();
    /**
     * shard的配置参数
     */
    private ShardInfoJedis shardInfoJedis;
    /**
     * 是否是master-master模式
     */
    private boolean isMM = false;

    /**
     * 线程执行器
     */
    private ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();

    /***
     *
     * @param pools 连接池
     * @param shardInfoJedis 分片信息
     */
    public ShardHanlder(List<DefaultJedisPool> pools, ShardInfoJedis shardInfoJedis) {
        super();
        if(!CollectionUtils.isEmpty(pools)){
            this.pools.addAll(pools);
            if(this.pools.size()==2){
                this.isMM=true;
            }
        }
        this.shardInfoJedis=shardInfoJedis;
        getError();
        sync();
    }

    /***
     * 同步
     */
    private void sync() {
        if(this.isMM){
                this.scheduledExecutor.schedule(new Runnable() {
                    @Override
                    public void run() {
                        getError();
                        sync();
                    }
                },1, TimeUnit.SECONDS);
        }
    }

    /***
     * 获取错误的连接池数据
     */
    private synchronized void getError() {
        if(this.isMM){
            if(errorPools.size()==2){
                //重置
                clearDisableFlags();
            }

            for(DefaultJedisPool pool:pools){
                if(!this.errorPools.contains(pool)&&!isAvailable(pool)){
                    errorPools.add(pool);
                }else if(this.errorPools.contains(pool)&&this.isAvailable(pool)){
                    errorPools.remove(pool);
                }
            }
        }

    }


    /**
     *
     * toString
     * @return string
     */
    @Override
    public String toString() {
        return "ShardHandler [shardInfo4Jedis=" + shardInfoJedis + "]";
    }

    /**
     *
     * 是否可用
     *
     * @param errorPool 参数说明 返回值: 类型 <说明>
     * @return boolean 返回值
     */
    private boolean isAvailable(DefaultJedisPool errorPool) {
        int recorder = pools.indexOf(errorPool) == 0 ? 1 : 0;
        DefaultJedisPool recorderPool = pools.get(recorder);
        return !invoke(recorderPool, new CallBack<Boolean>() {
            @Override
            public Boolean invoke(Jedis jedis) {
                return jedis.exists(REDIS_UNAVAILABLE_SIGN);
            }
        });
    }

    /**
     *
     * 功能描述：<br>
     * 随机返回一个可用的pool
     *
     * @return DefaultJedisPool 返回值
     */
    private DefaultJedisPool getPool() {
        DefaultJedisPool returnPool = null;
        if (isMM) {
            if (errorPools.isEmpty()) {
                int poolTag = new Random().nextInt(2);
                // 选中的pool
                DefaultJedisPool pool = pools.get(poolTag);
                if (errorPools.contains(pool)) {
                    returnPool = pools.get(poolTag == 0 ? 1 : 0);
                } else {
                    returnPool = pool;
                }
            } else {
                returnPool = errorPools.contains(pools.get(0)) ? pools.get(1) : pools.get(0);
            }
        } else {
            returnPool = pools.get(0);
        }
        return returnPool;
    }

    /**
     * 功能描述：标记不可用，并加入不可用列表
     *
     * @param errorPool 参数说明 返回值: 类型 <说明>
     */
    private void onError(final DefaultJedisPool errorPool) {
        if (isMM) {
            synchronized (this) {
                if (errorPools.isEmpty()) {
                    int recorder = pools.indexOf(errorPool) == 0 ? 1 : 0;
                    DefaultJedisPool recorderPool = pools.get(recorder);
                    try {
                        invoke(recorderPool, new CallBack<String>() {
                            @Override
                            public String invoke(Jedis jedis) {// 如果一台不可用，则在另一台redis里标识其为不可用。
                                return jedis.set(REDIS_UNAVAILABLE_SIGN, errorPool.toString());
                            }
                        });
                    } catch (RedisClientException e) {
                        logger.error(e.getMessage());
                    }
                }
            }
        }
    }

    /**
     *
     * 功能描述: <br>
     * 清除不可用标记
     *
     */
    public void clearDisableFlags() {
        for (DefaultJedisPool jedisPool : pools) {
            try {
                invoke(jedisPool, new CallBack<Long>() {
                    @Override
                    public Long invoke(Jedis jedis) {
                        return jedis.del(REDIS_UNAVAILABLE_SIGN);
                    }
                });
            } catch (RedisClientException e) {
                logger.error(e.getMessage());
            }
        }
        errorPools.clear();
    }

    /**
     *
     * 功能描述: <br>
     * 清空shard中所有数据
     *
     */
    public void flushShard() {
        for (DefaultJedisPool jedisPool : pools) {
            try {
                invoke(jedisPool, new CallBack<String>() {
                    @Override
                    public String invoke(Jedis jedis) {
                        return jedis.flushDB();
                    }
                });
            } catch (RedisClientException e) {
                logger.error(e.getMessage());
            }
        }
    }

    /**
     *
     * 功能描述： 销毁所有池
     *
     */
    public void destroy() {
        for (DefaultJedisPool jedisPool : pools) {
            try {
                jedisPool.destroy();
            } catch (JedisException e) {
                logger.error(e.getMessage());
            }
        }
    }

    /**
     *
     * 功能描述：invoke
     *
     * @param jedisPool 参数说明 返回值: 类型 <说明>
     * @param callBack 参数说明 返回值: 类型 <说明>
     * @param <R> 泛型对象爱内阁
     * @return R 返回值
     */
    private <R> R invoke(DefaultJedisPool jedisPool, CallBack<R> callBack) {
        boolean isClosed = false;
        Jedis jedis = null;
        try {
            // 获取连接
            jedis = jedisPool.getResource();
            return callBack.invoke(jedis);
        } catch (Exception e) {
            if (e instanceof JedisConnectionException) {
                if (jedis != null) {
                    try {
                        // 处理无效连接
                        jedisPool.returnBrokenResource(jedis);
                        isClosed = true;
                    } catch (JedisException e1) {
                        logger.error(e1.getMessage(), e1);
                    }
                }
            }
            // 抛出异常
            throw new RedisClientException(e);
        } finally {
            if (!isClosed) {
                if (jedis != null) {
                    try {
                        // 归还连接
                        jedisPool.returnResource(jedis);
                    } catch (JedisException e1) {
                        logger.error(e1.getMessage(), e1);
                    }
                }
            }
        }
    }

    /**
     *
     * 功能描述：<br>
     * execute
     *
     * @param callBack 参数说明
     * @param <R> 结果泛型对象
     * @return R 返回值
     */
    public <R> R execute(CallBack<R> callBack) {
        // 获取上一方法名
        String preMethodName = new Exception().getStackTrace()[2].getMethodName();
        // 若是读方法
        if (ReadMethodTags.isReadMethod(preMethodName)) {
            // 错误次数
            int eCount = 0;
            DefaultJedisPool jedisPool = null;
            while (true) {
                jedisPool = getPool();
                try {
                    return invoke(jedisPool, callBack);
                } catch (RedisClientException e) {
                    // 错误次数加1
                    eCount++;
                    // 若错误数未到限制
                    if (eCount < ConfigManager.RETRYTIMES) {
                        continue;
                    }
                    // 加入不可用列表
                    onError(jedisPool);
                    // 抛出异常
                    throw e;
                }
            }
        } else {
            R r = null;
            // 错误的池的个数
            int eCount = 0;
            // 抛出的异常
            RedisClientException eThrow = null;
            List<DefaultJedisPool> pools = getAllAvailablePools();
            // 池的个数
            int poolsSize = pools.size();
            // 循环写
            for (DefaultJedisPool jedisPool : pools) {
                for (int i = 0; i < ConfigManager.RETRYTIMES; i++) {
                    try {
                        // 执行
                        r = invoke(jedisPool, callBack);
                    } catch (RedisClientException e) {
                        // 重试次数到限制
                        if (i == ConfigManager.RETRYTIMES - 1) {
                            // 错误的池的个数加1
                            eCount++;
                            // 抛出的异常
                            eThrow = e;
                            // 加入不可用列表
                            onError(jedisPool);
                        } else {
                            continue;
                        }
                    }
                    break;
                }
            }
            // 所有池抛异常了,告知调用端
            if (eCount == poolsSize) {
                throw eThrow;
            }
            return r;
        }
    }

    /**
     *
     * 功能描述：获取所有"可用的"连接池
     *
     * @return List<DefaultJedisPool> 返回值
     */
    private List<DefaultJedisPool> getAllAvailablePools() {
        if (isMM) {
            List<DefaultJedisPool> lst = new ArrayList<DefaultJedisPool>();
            for (DefaultJedisPool jedisPool : pools) {
                if (!errorPools.contains(jedisPool)) {
                    lst.add(jedisPool);
                }
            }
            return lst;
        } else {
            return pools;
        }
    }

}
