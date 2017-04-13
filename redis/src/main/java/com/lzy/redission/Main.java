package com.lzy.redission;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RScheduledExecutorService;
import org.redisson.api.RedissonClient;
import org.redisson.api.annotation.RInject;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2017-04-06
 */
public class Main {
	public static void main(String[] args) throws InterruptedException, ClassNotFoundException {
		Class<String> stringClass = String.class;
		Class<?> aClass = Class.forName("java.lang.String");
		Class<? extends String> sClass = "hello".getClass();
		RedissonClient redissonClient = Redisson.create();
		RMap<Object, Object> mapMap = redissonClient.getMap("mapMap");
		RLock myLock = redissonClient.getLock("myLock");
		RScheduledExecutorService myExecutorService = redissonClient.getExecutorService("myExecutorService2");

		myLock.lock();

		try {
			System.out.println("Do wit distributed lock");
		} finally {
			myLock.unlock();
		}
		System.out.println("Done");

		myExecutorService.submit(new Task());
		myExecutorService.scheduleAsync(new Task(), 10, TimeUnit.SECONDS);
	}

}

class Task implements Runnable {
	@RInject
	private RedissonClient redissonClient;

	@Override
	public void run() {

		System.out.println("Running");
	}
}
