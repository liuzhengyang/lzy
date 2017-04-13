package com.reflect;

import com.google.common.hash.Hashing;

import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2017-03-21
 */
public class TestAllType {
	public static void main(String[] args) {
		System.out.println(Hashing.murmur3_32().hashString("4009812001201704116636234249", Charset.forName("UTF-8")).asInt());
		int[] arr = new int[]{};
		System.out.println(arr.getClass());
		Method[] methods = TestAllType.class.getMethods();
		for (Method method : methods) {
			System.out.println(Arrays.toString(method.getParameterTypes()));
		}
		List<Integer> list = new ArrayList<>();
		testGeneric(list);
	}

	private static void testGeneric(List<? extends Number> list) {
		System.out.println(list);
		Number number = list.get(0);
	}
}
