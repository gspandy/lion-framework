package com.newtouch.lion.redis.client.impl;

import com.newtouch.lion.redis.config.ConfigManager;
import com.newtouch.lion.redis.shard.ShardHandler;
import com.newtouch.lion.redis.shard.ShardedJedis;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.concurrent.CountDownLatch;

/**
 * 默认的客户端实现
 * 
 * 
 */
public class DefaultClientImpl {

    /**
     * 字符串常量
     */
    public static final String UNSUPPORT = "Current configuration does not support this operation";

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(DefaultClientImpl.class);

    /**
     * 分片
     */
     protected ShardedJedis shardedJedis;

     /**
     * 配置管理器
     */
    private ConfigManager configManager;

    /**
     * 构造方法
     */
    public DefaultClientImpl() {
        configManager = new ConfigManager();
        this.init();
    }

    /**
     * 构造方法
     *
     * @param config 配置信息
     */
    public DefaultClientImpl(String config) {
        configManager = new ConfigManager(config);
        this.init();
    }




    /**
     * 判别是否是分片
     * 
     * @return 返回值
     */
    protected boolean isSharding() {
        return configManager.isSharding();
    }

    /**
     * 初始化
     */
    private synchronized void init() {
        // 加载配置
       // configManager.loadConfig();
        shardedJedis = new ShardedJedis(configManager.getLstInfoJedis());
    }

    /**
     * 刷新配置
     *
     * @param config 配置参数
     */
    public synchronized void refresh(String config) {
        ShardedJedis old = shardedJedis;
        if (!StringUtils.isBlank(config)) {
            configManager.setConfig(config);
        }
        this.init();
        // 销毁旧池
        Collection<ShardHandler> allShards = old.getAllShards();
        for (ShardHandler shardHandler : allShards) {
            shardHandler.destroy();
        }
    }

    /**
     * flush数据
     * 
     * @return 返回"ok"表示成功
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
        for (ShardHandler shard:allShards) {
            shard.clearDisableFlags();
        }
    }
}
