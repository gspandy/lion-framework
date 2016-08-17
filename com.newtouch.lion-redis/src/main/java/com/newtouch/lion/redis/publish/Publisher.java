package com.newtouch.lion.redis.publish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

public class Publisher {

	private static final Logger logger = LoggerFactory
			.getLogger(Publisher.class);

	private final Jedis publisherJedis;

	private final String channel;

	public Publisher(Jedis publisherJedis, String channel) {
		this.publisherJedis = publisherJedis;
		this.channel = channel;
	}

	public void lpublish(String msg) {
		try {
			publisherJedis.lpush(channel, msg);
		} catch (Exception e) {
			logger.error("IO failure while reading input, e");
		}
	}

	public void publish(final String msg){
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {  
				     Thread.sleep(1000);  
				    } catch (InterruptedException e) {  
				     e.printStackTrace();  
				    } 
				publisherJedis.publish(channel, msg);
			}
		}).start();
	}
}
