package com.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2017-03-27
 */
public class UnsafeTest {
	private long time;
	private static Unsafe unsafe = null;

	private static long timeFieldOffset;
	static {
		try {
			Field theUnsafe = null;
			theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
			theUnsafe.setAccessible(true);
			unsafe = (Unsafe) theUnsafe.get(null);
			System.out.println(unsafe);
			timeFieldOffset = unsafe.objectFieldOffset(UnsafeTest.class.getDeclaredField("time"));
			System.out.println(timeFieldOffset);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InterruptedException {
		System.out.println(timeFieldOffset);
		UnsafeTest unsafeTest = new UnsafeTest();
		long aLong = unsafe.getLong(unsafeTest, timeFieldOffset);
		System.out.println(aLong);
		boolean b = unsafe.compareAndSwapLong(unsafeTest, timeFieldOffset, 0, 1);
		System.out.println(b);
		System.out.println(-1 << 29);
		System.out.println(~2);
		System.out.println(~-2);
		System.out.println(~-100);
		int x = 0;
		System.out.println(unsafeTest.time = 1);
		System.out.println(~x == -(x + 1));
		System.out.println(-1 << 29);
		System.out.println(0 << 29);
		System.out.println(0b100000000000000000000000000000);
		System.out.println(0b1000000000000000000000000000000);
		System.out.println(0b1100000000000000000000000000000);
		System.out.println(0b11100000000000000000000000000000);
		System.out.println(0b11100000000000000000000000000000);
		ThreadPoolExecutor t = new ThreadPoolExecutor(10, 20, 1, TimeUnit.SECONDS, new SynchronousQueue<>());
		t.submit(() -> {
			System.out.println("hello");
		});
		t.awaitTermination(1, TimeUnit.MINUTES);
	}
}
