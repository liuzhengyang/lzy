package com.juc.lzy.ch9;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class MyRunnableBuilder {
	private CountDownLatch startLatch;
	private CountDownLatch stopLatch;

	public MyRunnableBuilder setStartLatch(CountDownLatch startLatch) {
		this.startLatch = startLatch;
		return this;
	}

	public MyRunnableBuilder setStopLatch(CountDownLatch stopLatch) {
		this.stopLatch = stopLatch;
		return this;
	}

	public ThreadLocalTest.MyRunnable createMyRunnable() {
		return new ThreadLocalTest.MyRunnable(startLatch, stopLatch);
	}

	public static void main(String[] args) {
//		Map<String, String> originMap = new HashMap<String, String>();
//		for (int i = 0; i < 100; i++) {
//			originMap.put(String.valueOf(i), String.valueOf(i));
//		}
		Map<String, String> map = new HashMap<String, String>();
		System.out.println(map);
		map.put("1", "2");

//		map.putAll(originMap);

	}
}