package com.reflect;

import java.lang.reflect.Method;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2017-03-27
 */
public class TestBaseRefelct {
	public static void main(String[] args) {
		try {
			Class<?> aClass = Class.forName("java.lang.String");
			String str = (String) aClass.newInstance();
			Method[] declaredMethods = aClass.getDeclaredMethods();
			Method[] declaredMethods2 = aClass.getDeclaredMethods();
			System.out.println(str);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
	}
}
