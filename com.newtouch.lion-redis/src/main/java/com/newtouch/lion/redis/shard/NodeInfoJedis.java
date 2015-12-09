package com.newtouch.lion.redis.shard;

import org.apache.commons.lang3.ObjectUtils;
import redis.clients.jedis.JedisPoolConfig;

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
 *          2015-12-07 13:10.
 */
public class NodeInfoJedis {

    /**
     * Redis服务器ip
     */
    private String ip;

    /**
     * Redis服务器端口号
     */
    private Integer port;

    /**
     * 　Redis服务器访问密码
     */
    private String password;

    /**
     * Redis服务器数据库标识，Redis服务器内部使用，相当于数据库的schema
     */
    private int dbIndex;

    /**
     * 连接超时时间
     */
    private int timeOut;

    /**
     * 配置信息
     */
    private JedisPoolConfig config;

    public NodeInfoJedis() {
        super();
    }

    /***
     *
     * @param ip
     * @param port
     * @param password
     * @param dbIndex
     * @param timeOut
     * @param config
     */
    public NodeInfoJedis(String ip, Integer port, String password, int dbIndex, int timeOut, JedisPoolConfig config) {
        this.ip = ip;
        this.port = port;
        this.password = password;
        this.dbIndex = dbIndex;
        this.timeOut = timeOut;
        this.config = config;
    }

    /**
     * Gets 配置信息.
     *
     * @return Value of 配置信息.
     */
    public JedisPoolConfig getConfig() {
        return config;
    }

    /**
     * Sets new 配置信息.
     *
     * @param config New value of 配置信息.
     */
    public void setConfig(JedisPoolConfig config) {
        this.config = config;
    }

    /**
     * Sets new Redis服务器ip.
     *
     * @param ip New value of Redis服务器ip.
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * Sets new 连接超时时间.
     *
     * @param timeOut New value of 连接超时时间.
     */
    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    /**
     * Gets Redis服务器端口号.
     *
     * @return Value of Redis服务器端口号.
     */
    public Integer getPort() {
        return port;
    }

    /**
     * Sets new Redis服务器数据库标识，Redis服务器内部使用，相当于数据库的schema.
     *
     * @param dbIndex New value of Redis服务器数据库标识，Redis服务器内部使用，相当于数据库的schema.
     */
    public void setDbIndex(int dbIndex) {
        this.dbIndex = dbIndex;
    }

    /**
     * Gets 　Redis服务器访问密码.
     *
     * @return Value of 　Redis服务器访问密码.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets 连接超时时间.
     *
     * @return Value of 连接超时时间.
     */
    public int getTimeOut() {
        return timeOut;
    }

    /**
     * Gets Redis服务器数据库标识，Redis服务器内部使用，相当于数据库的schema.
     *
     * @return Value of Redis服务器数据库标识，Redis服务器内部使用，相当于数据库的schema.
     */
    public int getDbIndex() {
        return dbIndex;
    }

    /**
     * Gets Redis服务器ip.
     *
     * @return Value of Redis服务器ip.
     */
    public String getIp() {
        return ip;
    }

    /**
     * Sets new Redis服务器端口号.
     *
     * @param port New value of Redis服务器端口号.
     */
    public void setPort(Integer port) {
        this.port = port;
    }

    /**
     * Sets new 　Redis服务器访问密码.
     *
     * @param password New value of 　Redis服务器访问密码.
     */
    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * 获取hashcode
     *
     * @return int hashcode
     */
    @SuppressWarnings("deprecation")
    @Override
    public int hashCode() {
        return ObjectUtils.hashCodeMulti(ip, port, dbIndex);
    }

    /**
     * 节点比较
     *
     * @param obj 比较对象
     * @return boolean 比较结果
     */
    @SuppressWarnings("deprecation")
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof NodeInfoJedis) {
            NodeInfoJedis that = (NodeInfoJedis) obj;
            return ObjectUtils.equals(ip, that.ip) && ObjectUtils.equals(port, that.port)
                    && ObjectUtils.equals(dbIndex, that.dbIndex);
        } else {
            return false;
        }
    }

    /**
     *
     * toString
     *
     * @return String ip + port + password + dbIndex + timeOut + config
     */
    @Override
    public String toString() {
        return "NodeInfo4Jedis [ip=" + ip + ", port=" + port + ", password=" + password + ", dbIndex=" + dbIndex
                + ", timeOut=" + timeOut + ", config=" + config + "]";
    }
}
