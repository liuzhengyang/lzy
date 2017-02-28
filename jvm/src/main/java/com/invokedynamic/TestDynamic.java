package com.invokedynamic;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2017-02-21
 */
public class TestDynamic {
	public static void main(String[] args) {
		MethodHandles.Lookup lookup = MethodHandles.lookup();
		MethodType methodType = MethodType.methodType(String.class, String.class);
		try {
			MethodHandle getProperty = lookup.findStatic(System.class, "getProperty", methodType);
			Object invoke = getProperty.invoke("java.home");
			System.out.println(invoke);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
	}
}
