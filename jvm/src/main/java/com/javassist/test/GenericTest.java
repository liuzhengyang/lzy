package com.javassist.test;

import java.util.List;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2016-08-10
 */
public class GenericTest<T> {
	public int say(List<String> list) {
		return list.size();
	}

	public  T get(T input) {
		return null;
	}
}

