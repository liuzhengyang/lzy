package com.javassist.test;

import java.util.List;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2016-08-10
 */
public class TestGeneric extends GenericTest<String> {
	@Override
	public int say(List<String> list) {
		return super.say(list);
	}

	@Override
	public String get(String input) {
		return super.get(input);
	}
}
