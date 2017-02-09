package com.lzy.redis;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.Date;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2016-07-26
 */
public class RedisLimitter {

	private static final Logger LOGGER = LoggerFactory.getLogger(RedisLimitter.class);
	public static long THRE_SHOLD = 2;
	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 100; i++) {
			limitAccessApproach2("ss33");
			Thread.sleep(20);
		}
		for (int i = 0; i < 100; i++) {
			limitAccess("10");
		}
	}

	private static void limitAccess(String ip) {
		String time = String.valueOf(new Date().getTime() / 1000);
		String key = ip + ":" + time;
		Jedis jedis = JedisFactory.getJedis();
		String s = jedis.get(key);
		if (StringUtils.isNotEmpty(s) && Long.parseLong(s) >= THRE_SHOLD) {
			LOGGER.info("exceed max number per interval");
		} else {
			Long incr = jedis.incr(key);
			Long expire = jedis.expire(key, 1);
			LOGGER.info("Run");
		}
	}

	private static void limitAccessApproach(String ip) {
		Jedis jedis = JedisFactory.getJedis();
		String script = "local current\n" +
				"current = redis.call(\"incr\",KEYS[1])\n" +
				"if tonumber(current) == 1 then\n" +
				"    redis.call(\"expire\",KEYS[1],1)\n" +
				"end";
		Object eval = jedis.eval(script, Lists.newArrayList("keys"), Lists.<String>newArrayList());
		Transaction multi = jedis.multi();
		multi.exec();
		System.out.println(eval);
	}
	private static void limitAccessApproach2(String ip) {
		Jedis jedis = JedisFactory.getJedis();
		String s = jedis.get(ip);
		if (StringUtils.isNotEmpty(s) && Long.parseLong(s) > 10) {
			System.out.println("Exceed max number");
		} else {
			long count = jedis.incr(ip);
			if (count == 1) {
				jedis.expire(ip, 1);
			}
			System.out.println("Run");
		}
	}

}
