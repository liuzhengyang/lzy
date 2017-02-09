package com.lzy.redis;

import com.google.common.util.concurrent.*;

import java.util.Date;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2016-12-15
 */
public class GuavaRateLimiter {

	public static void main(String[] args) throws InterruptedException {
		new GuavaRateLimiter().limit("nihao");
	}

	private void limit(String ip) throws InterruptedException {
		RateLimiter rateLimiter = RateLimiter.create(10);
		for (int i = 0; i < 10; i++) {
//			rateLimiter.acquire();
			boolean b = rateLimiter.tryAcquire();
			System.out.println("Try Acquire Result " + b);
			if (b) {
				System.out.println(new Date() + " Run");
			} else {
				System.out.println("Rejected + " + i);
			}
			Thread.sleep(10);
		}
	}
}
