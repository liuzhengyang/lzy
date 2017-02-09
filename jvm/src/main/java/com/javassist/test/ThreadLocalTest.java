package com.javassist.test;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2016-08-23
 */
public class ThreadLocalTest {
	private static final ThreadLocal<String> t1 = new ThreadLocal<>();
	private static final ThreadLocal<String> t2 = new ThreadLocal<>();
	private static ThreadLocal<String> t3 = new ThreadLocal<>();
	private static ThreadLocal<String> t4 = new ThreadLocal<>();

	public static void main(String[] args) throws InterruptedException {
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(t1.get());
			}
		});
		Thread.sleep(100);
	}
}
