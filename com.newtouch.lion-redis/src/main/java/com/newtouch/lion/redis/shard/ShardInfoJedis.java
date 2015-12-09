package com.newtouch.lion.redis.shard;

import com.newtouch.lion.redis.pool.DefaultJedisPool;
import org.apache.commons.lang3.ObjectUtils;

import java.util.*;

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
 *          2015-12-04 23:02.
 */
public class ShardInfoJedis extends DefaultShardInfo<ShardHandler>{
    /**
     * shard的唯一标识
     */
    private String shardName;

    /**
     * 一个分片的Redis节点所有信息
     */
    private Set<NodeInfoJedis> nodes = new HashSet<NodeInfoJedis>();

    /**
     *
     * ShardInfoJedis constructor
     */
    public ShardInfoJedis() {

    }

    /**
     * 构造方法
     *
     * @param shardName 分片名
     * @param nodes 节点
     */
    public ShardInfoJedis(String shardName, Collection<NodeInfoJedis> nodes) {
        this.shardName = shardName;
        this.nodes.addAll(nodes);
    }

    /**
     *
     * 返回分片名称
     *
     * @return String 返回分片名称
     */
    @Override
    public String getName() {
        return shardName;
    }

    /**
     *
     * toString
     *
     * @return String 分片信息 shardName + nodes
     */
    @Override
    public String toString() {
        return "ShardInfo [shardName=" + shardName + ", nodes=" + nodes + "]";
    }

    /**
     *
     * 返回hashCode
     *
     * @return int hash编码
     */
    @SuppressWarnings("deprecation")
    @Override
    public int hashCode() {
        return ObjectUtils.hashCodeMulti(shardName, nodes);
    }

    /**
     * 获取分片名称
     *
     * @return String 分片名称
     */
    public String getShardName() {
        return shardName;
    }

    /**
     * 获取节点
     *
     * @return Set<NodeInfo4Jedis> 节点
     */
    public Set<NodeInfoJedis> getNodes() {
        return nodes;
    }

    /**
     * 设置分片名称
     *
     * @param shardName 分片名称
     */
    public void setShardName(String shardName) {
        this.shardName = shardName;
    }

    /**
     * 设置节点
     *
     * @param nodes 节点
     */
    public void setNodes(Set<NodeInfoJedis> nodes) {
        this.nodes = nodes;
    }

    /**
     *
     * equals
     *
     * @param obj 比较对象
     * @return boolean 比较结果
     */
    @SuppressWarnings("deprecation")
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ShardInfoJedis) {
            ShardInfoJedis that = (ShardInfoJedis) obj;
            return ObjectUtils.equals(nodes, that.nodes) && ObjectUtils.equals(shardName, that.shardName);
        } else {
            return false;
        }
    }

    /**
     * 创建本shard的处理资源
*
        * @return ShardHandler 本shard的处理资源
        */
    protected ShardHandler createResource() {
        List<DefaultJedisPool> masterPools = new ArrayList<DefaultJedisPool>();
        for (NodeInfoJedis nodeInfo : nodes) {
        DefaultJedisPool jedisPool = new DefaultJedisPool(nodeInfo.getConfig(), nodeInfo.getIp(),
        nodeInfo.getPort(), nodeInfo.getTimeOut(), nodeInfo.getPassword(), nodeInfo.getDbIndex(), true);
        masterPools.add(jedisPool);
        }
        return new ShardHandler(masterPools, this);
        }
        }
