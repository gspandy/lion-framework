package com.newtouch.lion.redis.subscribe;

import com.newtouch.lion.redis.listener.RedisListener;
import redis.clients.jedis.Jedis;

public class Subscriber {
	
	private final Jedis publisherJedis;

	private final String channel;

	public Subscriber(Jedis publisherJedis, String channel) {
		this.publisherJedis = publisherJedis;
		this.channel = channel;
	}

	public void psub(final RedisListener listener) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				// 订阅得到信息在lister的onMessage(...)方法中进行处理
				// 订阅多个频道
				// redisClient.subscribe(listener, "news.share", "news.log");
				publisherJedis.psubscribe(listener, new String[] {channel });// 使用模式匹配的方式设置频道
			}
		}).start();
	}
}
