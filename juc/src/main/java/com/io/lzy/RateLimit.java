package com.io.lzy;

import com.google.common.util.concurrent.RateLimiter;

import java.util.Date;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2016-07-24
 */
public class RateLimit {
	public static void main(String[] args) {
		main2(null);
		RateLimiter rateLimiter = RateLimiter.create(1);

		for (int i = 0; i < 100; i++) {
			boolean b = rateLimiter.tryAcquire(1);
			System.out.println("acquirer result " + b);

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main2(String[] args) {
		RateLimiter rateLimiter = RateLimiter.create(10);

		for (int i = 0; i < 100; i++) {
			rateLimiter.acquire(1);
			System.out.println("acquirer success " + new Date().getTime());

		}
	}
}
