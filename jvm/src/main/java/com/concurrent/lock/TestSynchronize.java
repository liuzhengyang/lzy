package com.concurrent.lock;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2017-03-28
 */
public class TestSynchronize {
	private int count;

	private void inc() {
		synchronized (this) {
			count++;
		}
	}

	private synchronized void syn() {

	}

	public static void main(String[] args) {
		new TestSynchronize().inc();
	}
}
