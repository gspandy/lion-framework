package com.newtouch.lion.redis.config;


import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;

/**
 * <p>
 * Title: 配置信息管理类
 * </p>
 * <p>
 * Description: 配置信息管理类
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
 *          2015-12-03 23:49.
 */
public class ConfigManager {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ConfigManager.class);

    /**
     * 重试次数
     */
    public static final int RETRYTIMES = 3;

    /**
     * 默认ip
     */
    public static final String DEFAULT_HOST = "127.0.0.1";
    /**
     * 默认端口
     */
    public static final String DEFAULT_PORT = "6379";
    /**
     * 默认过期时间
     */
    public static final String DEFAULT_TIMEOUT_IN_MILL_SECONDS = "2000";
    /**
     * 默认索引初始值
     */
    public static final String DEFAULT_DB_INDEX = "0";

    /**
     * 默认配置文件
     */
    private static final String CONFIG = "caches.xml";


    /**
     * lstInfo4Jedis
     */
    protected List<ShardInfo4Jedis> lstInfo4Jedis;

    /**
     * config
     */
    private String config = CONFIG;

    /**
     * isSharding
     */
    private boolean isSharding;

    /**
     *
     *
     * ConfigManager constructor
     */
    public ConfigManager() {
    }

    /**
     * 构造方法
     *
     * @param config 配置
     */
    public ConfigManager(String config) {
        this.config = config;
    }

    /**
     * getLstInfo4Jedis
     *
     * @return List<ShardInfo4Jedis> 分片的Redis节点信息列表
     */
    public List<ShardInfo4Jedis> getLstInfo4Jedis() {
        return lstInfo4Jedis;
    }

    /**
     *
     * 功能描述：是否配置分片
     *
     * @return boolean返回值
     */
    public boolean isSharding() {
        return isSharding;
    }


    /**
     *
     * 功能描述：设置配置文件
     *
     * @param config 参数说明 返回值: 类型 <说明>
     */
    public synchronized void setConfig(String config) {
        this.config = config;
    }







    /**
     * 功能描述：验证连接可用性的参数设置
     *
     * @param config 参数说明 返回值: 类型 <说明>
     * @param whenExhaustedAction 参数说明 返回值: 类型 <说明>
     * @param testOnBorrow 参数说明 返回值: 类型 <说明>
     * @param testOnReturn 参数说明 返回值: 类型 <说明>
     * @param testWhileIdle 参数说明 返回值: 类型 <说明>
     * @param timeBetweenEvictionRunsMillis 参数说明 返回值: 类型 <说明>
     * @param numTestsPerEvictionRun 参数说明 返回值: 类型 <说明>
     * @param minEvictableIdleTimeMillis 参数说明 返回值: 类型 <说明>
     * @param softMinEvictableIdleTimeMillis 参数说明 返回值: 类型 <说明>
     */
    private void setTestParameters(JedisPoolConfig config, String whenExhaustedAction, String testOnBorrow, String testOnReturn,
                                   String testWhileIdle, String timeBetweenEvictionRunsMillis, String numTestsPerEvictionRun,
                                   String minEvictableIdleTimeMillis, String softMinEvictableIdleTimeMillis) {

        if (!StringUtils.isBlank(testOnBorrow)) {
            // 获取连接池是否检测可用性
            config.setTestOnBorrow(Boolean.valueOf(testOnBorrow));
        }

        if (!StringUtils.isBlank(testOnReturn)) {
            // 归还时是否检测可用性
            config.setTestOnReturn(Boolean.valueOf(testOnReturn));
        }
        if (!StringUtils.isBlank(testWhileIdle)) {
            // 空闲时是否检测可用性
            config.setTestWhileIdle(Boolean.valueOf(testWhileIdle));
        } else {
            config.setTestWhileIdle(true);
        }
        if (!StringUtils.isBlank(whenExhaustedAction)) {
            config.setBlockWhenExhausted(Boolean.valueOf(whenExhaustedAction));
        }
        if (!StringUtils.isBlank(timeBetweenEvictionRunsMillis)) {

            config.setTimeBetweenEvictionRunsMillis(Long.valueOf(timeBetweenEvictionRunsMillis));
        } else {
            config.setTimeBetweenEvictionRunsMillis(30000L);
        }
        if (!StringUtils.isBlank(numTestsPerEvictionRun)) {

            config.setNumTestsPerEvictionRun(Integer.valueOf(numTestsPerEvictionRun));
        } else {
            config.setNumTestsPerEvictionRun(-1);
        }
        if (!StringUtils.isBlank(minEvictableIdleTimeMillis)) {

            config.setMinEvictableIdleTimeMillis(Integer.valueOf(minEvictableIdleTimeMillis));
        } else {
            config.setMinEvictableIdleTimeMillis(60000);
        }
        if (!StringUtils.isBlank(softMinEvictableIdleTimeMillis)) {
            config.setSoftMinEvictableIdleTimeMillis( Integer.valueOf(softMinEvictableIdleTimeMillis));
        }
    }
}
