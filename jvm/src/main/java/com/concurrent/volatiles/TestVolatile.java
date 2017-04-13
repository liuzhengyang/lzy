package com.concurrent.volatiles;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2017-03-27
 */
public class TestVolatile {
	private static volatile boolean stop = false;

	public static void main(String[] args) {
		stop = true;
		boolean b = stop;
	}
}
