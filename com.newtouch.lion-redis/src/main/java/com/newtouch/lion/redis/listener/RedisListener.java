package com.newtouch.lion.redis.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPubSub;

public class RedisListener extends JedisPubSub {
	
	 private static final Logger logger = LoggerFactory.getLogger(RedisListener.class);
	//取得订阅的消息后的处理
	 @Override
	public void onMessage(String channel, String message) {
		 logger.trace("channel:{}, msg:{}", channel, message);
	}

	// 初始化订阅时候的处理
	 @Override
	public void onSubscribe(String channel, int subscribedChannels) {
		 logger.info("RedisTopicListener onSubscribe, channel:{}, subscribedChannels:{}",new Object[]{channel, subscribedChannels});
	}

	// 取消订阅时候的处理
	 @Override
	public void onUnsubscribe(String channel, int subscribedChannels) {
		logger.info("RedisTopicListener onUnsubscribe, channel:{}, subscribedChannels:{}", new Object[]{channel, subscribedChannels});
	}

	// 初始化按表达式的方式订阅时候的处理
	 @Override
	public void onPSubscribe(String pattern, int subscribedChannels) {
		logger.info("RedisTopicListener onPUnsubscribe, pattern:{}, subscribedChannels:{}", new Object[]{pattern, subscribedChannels});
	}

	// 取消按表达式的方式订阅时候的处理
	 @Override
	public void onPUnsubscribe(String pattern, int subscribedChannels) {
		 logger.info("RedisTopicListener onPSubscribe, pattern:{}, subscribedChannels:{}",
	                new Object[]{pattern, subscribedChannels});
	}

	// 取得按表达式的方式订阅的消息后的处理
	 @Override
	public void onPMessage(String pattern, String channel, String message) {
		 logger.info("pattern:{}, channel:{}, msg:{}", new Object[]{pattern, channel, message});
	}


}
