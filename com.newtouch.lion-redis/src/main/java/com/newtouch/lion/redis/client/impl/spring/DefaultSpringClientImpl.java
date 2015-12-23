package com.newtouch.lion.redis.client.impl.spring;

import com.newtouch.lion.redis.config.ConfigManagerSpring;
import com.newtouch.lion.redis.shard.ShardHandler;
import com.newtouch.lion.redis.shard.ShardedJedis;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.concurrent.CountDownLatch;

/**
 * <p>
 * Title:
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
 * @author wanglijun
 * @version 1.0
 *          2015-12-09 17:18.
 */
public class DefaultSpringClientImpl {
    /** 字符串常量
    */
    public static final String UNSUPPORT = "Current configuration does not support this operation";
    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(DefaultSpringClientImpl.class);
    /**
     * 分片
     */
    protected ShardedJedis shardedJedis;

    /**
     * 配置管理器
     */
    protected ConfigManagerSpring configManagerSpring;

    /**
     * 返回配置管理器
     *
     * @return 配置管理器
     */
    public ConfigManagerSpring getConfigManagerSpring() {
        return configManagerSpring;
    }

    /**
     * 构造方法
     *
     * @param configManagerSpring
     *            spring配置
     */
    public void setConfigManagerSpring(
            ConfigManagerSpring configManagerSpring) {
        this.configManagerSpring = configManagerSpring;
    }

    /**
     * 构造方法
     */
    public DefaultSpringClientImpl() {

    }

    /**
     * 判断是否是分片
     *
     * @return 判断结果
     */
    protected boolean isSharding() {
        return configManagerSpring.isSharding();
    }

    /**
     * 功能描述：初始化
     */
    protected synchronized void init() {
        /** 加载配置 */
        configManagerSpring.loadConfig();
        shardedJedis = new ShardedJedis(
                configManagerSpring.getLstInfoJedis());
    }

    /**
     * 刷新配置
     *
     * @param config
     *            配置信息
     */
    public synchronized void refresh(String config) {
        ShardedJedis old = shardedJedis;
        if (!StringUtils.isBlank(config)) {
            configManagerSpring.setConfig(config);
        }
        this.init();
        // 销毁旧池
        Collection<ShardHandler> allShards = old.getAllShards();
        for (ShardHandler shardHandler : allShards) {
            shardHandler.destroy();
        }
    }

    /**
     *
     * 功能描述: <br>
     * 刷新所有数据
     *
     * @return 返回刷新结果
        */
        public String flushDB() {
            Collection<ShardHandler> allShards = shardedJedis.getAllShards();
            final CountDownLatch endSignal = new CountDownLatch(allShards.size());
            for (final ShardHandler shard : allShards) {
                // 多线程同时flushDB 提高效率
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        shard.flushShard();
                        endSignal.countDown();
                    }
                }).start();
            }
        try {
            endSignal.await();
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
        return "OK";
    }

    /**
     *
     * 功能描述: <br>
     * 清除节点不可用的标记
     *
     */
    public void clearDisableFlags() {
        // 所有shard中所有连接池
        Collection<ShardHandler> allShards = shardedJedis.getAllShards();
        for (ShardHandler shard : allShards) {
            shard.clearDisableFlags();
        }
    }
}
