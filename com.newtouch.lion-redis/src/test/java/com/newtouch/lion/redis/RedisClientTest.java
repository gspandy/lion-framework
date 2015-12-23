package com.newtouch.lion.redis;

import com.newtouch.lion.redis.client.RedisClient;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
 *          2015-12-09 17:34.
 */
public class RedisClientTest extends RedisAllTest {

    /* * 缓存接口. */
    @Autowired
    private RedisClient redisClient;


    /**
     * 设置了，返回OK
     * */
    @Test
    public void set() {
        String key = "watchmem:20150410";
        String result=this.redisClient.set(key, "00000000");
        logger.info("result:{}",result);
    }

    @Test
    public void set1() {
        String key = "watchmem:201504100";
       // this.redisClient.set(key, "00000000", "", "", 10000L);
    }


    @Test
    public void get() {
        String key = "watchmem:20150410";
        String result = this.redisClient.get(key);
        logger.info("result:{}", result);
    }

    //删除KEY 并返回删除个数
    @Test
    public void del() {
        String key = "watchmem:20150410";
        Long result = this.redisClient.del(key);
        logger.info("result:{}", result);
    }


    /**
     * 判断key是否已存在
     * */
    @Test
    public void exists() {
        String key = "watchmem:20150410";
        Boolean result = this.redisClient.exists(key);
        logger.info("result:{}", result);
    }

    @Test
    public void type(){
        String key = "watchmem:20150410";
        String result=this.redisClient.type(key);
        logger.info("result:{}",result);
    }

    /**
     * 设置key有效时间，单位秒
     * **/
    @Test
    public void expire(){
        String key="watchmem:20150410";
        Long result=this.redisClient.expire(key,60);
        logger.info("result:{}",result);
    }


    @Test
    public void getset(){
        String key="watchmem:20150410";
        String result=this.redisClient.getSet(key, "1234567890");
        logger.info("result:{}",result);
    }

    @Test
    public void setnx(){

        // 若key不存在，则存储,存储时返回1，已存储则返回0,可以用于分布式锁
        String key="watchmem:20150411";
        Long result=this.redisClient.setnx(key, "1234567890");
        logger.info("result:{}", result);
    }

    @Test
    public void expireAt(){
        //EXPIREAT EXPIREAT的作用和EXPIRE一样，都用于为key设置生存时间。不同在于EXPIREAT命令接受的时间参数是UNIX时间戳(unix timestamp)

        String key="watchmem:20150410";
        Long result=this.redisClient.expire(key,120);
        logger.info("result:{}",result);
    }

}
