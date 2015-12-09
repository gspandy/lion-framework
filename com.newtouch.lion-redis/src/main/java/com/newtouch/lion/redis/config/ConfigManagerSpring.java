package com.newtouch.lion.redis.config;

import java.util.ArrayList;
import java.util.List;

import com.newtouch.lion.redis.shard.NodeInfoJedis;
import com.newtouch.lion.redis.shard.ShardConfig;
import com.newtouch.lion.redis.shard.ShardInfoJedis;
import org.apache.commons.lang3.StringUtils;

import redis.clients.jedis.JedisPoolConfig;

/**
 * 
 * 
 * 功能描述： 配置信息管理类
 *
 * @version 1.0.0
 */
public class ConfigManagerSpring extends ConfigManager {
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
     * ShardInfo4Jedis列表
     */
    protected List<ShardInfoJedis> lstInfoJedis;

    /**
     * pool配置
     */
    private PoolConfig poolConfig;
    /**
     * shard配置
     */
    private ShardConfig shardConfig;

    /**
     * 
     * 功能描述: <br>
     * getShardConfig
     * 
     * @return ShardConfig 参数说明
     */
    public ShardConfig getShardConfig() {
        return shardConfig;
    }

    /**
     * 构造方法
     * 
     * @param shardConfig 配置
     */
    public void setShardConfig(ShardConfig shardConfig) {
        this.shardConfig = shardConfig;
    }

    /**
     * shard flag 是否分片
     */
    private boolean isSharding;

    /**
     * ConfigManager4Spring constructor
     */
    public ConfigManagerSpring() {

    }

    /**
     * 
     * 功能描述: <br>
     * getPoolConfig
     * 
     * @return PoolConfig 参数说明
     */
    public PoolConfig getPoolConfig() {
        return poolConfig;
    }

    /**
     * 设置池配置
     * 
     * @param poolConfig 配置
     */
    public void setPoolConfig(PoolConfig poolConfig) {
        this.poolConfig = poolConfig;
    }

    /**
     * 获取分片的Redis节点列表信息
     * 
     * @return List<ShardInfo4Jedis> 分片的Redis节点列表信息
     */
    public List<ShardInfoJedis> getLstInfo4Jedis() {
        return lstInfo4Jedis;
    }

    /**
     * 
     * 功能描述：是否配置分片
     * 
     * @return boolean 返回值
     */
    public boolean isSharding() {
        return isSharding;
    }

    /**
     * 
     * 功能描述： 加载配置文件
     * 
     */
    public synchronized void loadConfig() {
        parserWithDoc();
    }

    /**
     * 
     * 功能描述：解析配置文件
     * 
     * @param shardConfig 参数说明 返回值: 类型 <说明>
     */
    private void parseShardingConfig(ShardConfig shardConfig) {
        lstInfo4Jedis = new ArrayList<ShardInfoJedis>();
        JedisPoolConfig config = new JedisPoolConfig();
        setPoolParameters(poolConfig, config);
        shardConfig.setConfig(config);
        for (ShardInfoJedis shardInfo : shardConfig.getShards()) {
            lstInfo4Jedis.add(shardInfo);
            for (NodeInfoJedis node : shardInfo.getNodes()) {
                node.setConfig(shardConfig.getConfig());
            }
        }
        isSharding = lstInfo4Jedis.size() > 1 ? true : false;
    }

    /**
     * 
     * 功能描述： parserWithDoc
     * 
     */
    private void parserWithDoc() {
        parseShardingConfig(shardConfig);

    }

    /**
     * 处理连接池信息
     * 
     * @param poolConfig 参数说明
     * @param config 参数说明
     */
    private void setPoolParameters(PoolConfig poolConfig, JedisPoolConfig config) {
    	
    	if (!StringUtils.isBlank(poolConfig.getMaxWait())) {
            // 获取连接池是否检测可用性
            config.setMaxWaitMillis(Long.parseLong(poolConfig.getMaxWait()));
        }
    	
    	if (!StringUtils.isBlank(poolConfig.getMaxActive())) {
            // 获取连接池是否检测可用性
            config.setMaxTotal(Integer.parseInt(poolConfig.getMaxActive()));
        }
    	
    	if (!StringUtils.isBlank(poolConfig.getTestOnBorrow())) {
            // 获取连接池是否检测可用性
            config.setTestOnBorrow(Boolean.valueOf(poolConfig.getTestOnBorrow()));
        }

        if (!StringUtils.isBlank(poolConfig.getTestOnReturn())) {
            // 归还时是否检测可用性
            config.setTestOnReturn(Boolean.valueOf(poolConfig.getTestOnReturn()));
        }
        if (!StringUtils.isBlank(poolConfig.getTestWhileIdle())) {
            // 空闲时是否检测可用性
            config.setTestWhileIdle( Boolean.valueOf(poolConfig.getTestWhileIdle()));
        } else {
        	 config.setTestWhileIdle(true);
        }
        if (!StringUtils.isBlank(poolConfig.getWhenExhaustedAction())) {
            config.setBlockWhenExhausted(Boolean.valueOf(poolConfig.getWhenExhaustedAction()));
        }
        if (!StringUtils.isBlank(poolConfig.getTimeBetweenEvictionRunsMillis())) {
 
            config.setTimeBetweenEvictionRunsMillis(Long.valueOf(poolConfig.getTimeBetweenEvictionRunsMillis()));
        } else {
            config.setTimeBetweenEvictionRunsMillis(30000L);
        }
        if (!StringUtils.isBlank(poolConfig.getNumTestsPerEvictionRun())) {
        
            config.setNumTestsPerEvictionRun(Integer.valueOf(poolConfig.getNumTestsPerEvictionRun()));
        } else {
        	 config.setNumTestsPerEvictionRun(-1);
        }
        if (!StringUtils.isBlank(poolConfig.getMinEvictableIdleTimeMillis())) {
            
            config.setMinEvictableIdleTimeMillis(Integer.valueOf(poolConfig.getMinEvictableIdleTimeMillis()));
        } else {
        	  config.setMinEvictableIdleTimeMillis(60000);
        }
        if (!StringUtils.isBlank(poolConfig.getSoftMinEvictableIdleTimeMillis())) {
            config.setSoftMinEvictableIdleTimeMillis( Integer.valueOf(poolConfig.getSoftMinEvictableIdleTimeMillis()));
        }
    }

}
