package com.lzy.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;


/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2016-07-26
 */
public class JedisFactory {
	private static final Logger LOGGER = LoggerFactory.getLogger(JedisFactory.class);

	private static volatile Jedis jedis = null;

	public static Jedis getJedis() {
		if (jedis == null) {
			synchronized (JedisFactory.class) {
				if (jedis == null) {
					jedis = new Jedis("10.4.242.114", 6379);
					LOGGER.info("create jedis {}", jedis);
				}
			}
		}
		return jedis;
	}
}
