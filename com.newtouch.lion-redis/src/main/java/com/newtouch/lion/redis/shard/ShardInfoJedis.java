package com.newtouch.lion.redis.shard;

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
public class ShardInfoJedis extends DefaultShardInfo<ShardHanlder>{
    /**
     * 创建Resource
     *
     * @return 返回值
     */
    @Override
    protected ShardHanlder createResource() {
        return null;
    }

    /**
     * 获取分片名字
     */
    @Override
    public String getName() {
        return null;
    }
}
