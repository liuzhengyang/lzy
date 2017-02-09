package com.lzy.redis;

import redis.clients.jedis.Jedis;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2016-07-25
 */
public class Main {
	public static void main(String[] args) throws InterruptedException {
		Jedis jedis = new Jedis("10.4.242.114", 6379);
		String set = jedis.set("tes", "nihao");
		jedis.expire("tes", 1);
		System.out.println(set);
		Thread.sleep(1200);
		System.out.println(jedis.get("tes"));
	}

	public void limitCall(String ip) {

	}

	private void call() {
		System.out.println("call");
	}
}
