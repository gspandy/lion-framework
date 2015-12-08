package com.newtouch.lion.redis.shard;

/**
 * <p>
 * Title:分片工具类, 来自Jedis，为便于区分，重新取名
 * </p>
 * <p>
 * Description::分片工具类, 来自Jedis，为便于区分，重新取名
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
 *          2015-12-07 15:14.
 */
public abstract  class DefaultShardInfo<T> {
    /**share权重*/
    private int weight=1;

    public DefaultShardInfo() {
    }

    /**
     * 创建Resource
     * @return 返回值
     */
    protected  abstract  T createResource();

    /***
     * 获取分片名字
     */
    public  abstract String getName();

    /**
     *
     * @param weight 权重
     */
    public DefaultShardInfo(int weight) {
        this.weight = weight;
    }




    /**
     * Sets new share权重.
     *
     * @param weight New value of share权重.
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Gets share权重.
     *
     * @return Value of share权重.
     */
    public int getWeight() {
        return weight;
    }

}
