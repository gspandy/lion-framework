package com.newtouch.lion.redis.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.util.Pool;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * Title:支持选择dbIndex的Jedis连接池
 * </p>
 * <p>
 * Description:支持选择dbIndex的Jedis连接池
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
 *          2015-12-07 13:44.
 */
public class DefaultJedisPool extends Pool<Jedis> {
    /**日志*/
    private static Logger logger = LoggerFactory.getLogger(DefaultJedisPool.class);

    /**
     * host
     */
    private String host;
    /**
     * port
     */
    private int port;
    /**
     * dbIndex
     */
    private int dbIndex;
    /**
     * errorCount
     */
    private AtomicInteger errorCount = new AtomicInteger(0);

    /**
     * 是否是master
     */
    private boolean isMaster = true;




    /**
     * 构造方法
     *
     * @param poolConfig 池配置
     * @param host 主机地址
     * @param port 端口
     * @param timeout 超时时间
     * @param password 密码
     * @param dbIndex 索引
     * @param isMaster 是否是master
     */
    public DefaultJedisPool(final JedisPoolConfig poolConfig, final String host, int port, int timeout, final String password,
                            int dbIndex, boolean isMaster) {
        super(poolConfig,new JedisFactory(host,port,timeout,password,dbIndex));
        this.host = host;
        this.port = port;
        this.dbIndex = dbIndex;
        this.isMaster = isMaster;
    }


    /**
     * Gets port.
     *
     * @return Value of port.
     */
    public int getPort() {
        return port;
    }

    /**
     * Gets host.
     *
     * @return Value of host.
     */
    public String getHost() {
        return host;
    }

    /**
     * Gets dbIndex.
     *
     * @return Value of dbIndex.
     */
    public int getDbIndex() {
        return dbIndex;
    }

    /**
     * Sets new 是否是master.
     *
     * @param isMaster New value of 是否是master.
     */
    public void setIsMaster(boolean isMaster) {
        this.isMaster = isMaster;
    }

    /**
     * Sets new errorCount.
     *
     * @param errorCount New value of errorCount.
     */
    public void setErrorCount(AtomicInteger errorCount) {
        this.errorCount = errorCount;
    }

    /**
     * Gets errorCount.
     *
     * @return Value of errorCount.
     */
    public AtomicInteger getErrorCount() {
        return errorCount;
    }

    /**
     * Sets new dbIndex.
     *
     * @param dbIndex New value of dbIndex.
     */
    public void setDbIndex(int dbIndex) {
        this.dbIndex = dbIndex;
    }

    /**
     * Sets new host.
     *
     * @param host New value of host.
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * Sets new port.
     *
     * @param port New value of port.
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * Gets 是否是master.
     *
     * @return Value of 是否是master.
     */
    public boolean isIsMaster() {
        return isMaster;
    }
}
