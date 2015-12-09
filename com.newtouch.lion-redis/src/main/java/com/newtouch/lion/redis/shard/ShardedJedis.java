package com.newtouch.lion.redis.shard;

import java.util.List;

/**
 * <p>
 * Title:包括所有分片的连接池
 * </p>
 * <p>
 * Description:包括所有分片的连接池
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
 *          2015-12-09 15:49.
 */
public class ShardedJedis  extends DefaultSharded<ShardHandler, ShardInfoJedis>{
    /**
     * 构造方法
     *
     * @param shards 分片列表
     */
    public ShardedJedis(List<ShardInfoJedis> shards) {
        super(shards);
    }
}
