package com.lzy.bigdata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.function.Function;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2017-03-14
 */
public class BlockQueueTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(BlockQueueTest.class);

	public static void main(String[] args) throws Exception {
		Thread.sleep(10 * 10);
		BlockingQueue<Integer> blockingQueue = new LinkedBlockingDeque<>();
		int threadSize = 5;
		CountDownLatch startLatch = new CountDownLatch(1);
		CountDownLatch stopLatch = new CountDownLatch(threadSize);
		for (int i = 0; i < threadSize; i++) {
			new Thread(() -> {
				try {
					startLatch.await();
					System.out.println("start put");
					for (int j = 0; j < 10; j++) {
						blockingQueue.put(j);
						LOGGER.info("start put " + j);
					}
					stopLatch.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}).start();
		}
		startLatch.countDown();
		Thread.sleep(1000 * 2);
		if (System.out.printf("a") == null) {
			System.out.println("b");
		}
		if (new Object() {
			boolean valid() {
				System.out.println("a");
				return false;
			}
		}.valid()) {
			System.out.println("a");
		} else {
			System.out.println("b");
		}

		String s = new StringBuilder("x").append("b").toString();
		String s2 = new StringBuilder("tr").append("ue").toString();
		System.out.println(s == s.intern());
		System.out.println(s == "xb");
//		System.out.println(s2.intern() == "true");
		System.out.println(s2.intern() == s2);

	}
}
