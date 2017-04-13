package com.concurrent;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2017-03-20
 */
public class TestHoisting {
	private static boolean stop = false;

	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(() -> {
			int i = 0;
			while (!stop) {
				i++;
				System.out.println(i);
			}
		});
		thread.start();
		Thread.sleep(1000);
		stop = true;
		thread.join();

	}
}
