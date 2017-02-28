package com.javassist.test.regularexpression;

import java.lang.ref.WeakReference;
import java.util.regex.Pattern;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2017-02-07
 */
public class RETest {
//	class System {
//
//	}

	public static void main(String[] args) {
		Pattern compile = Pattern.compile("^\\(\\d+\\)$");
		boolean matches = compile.matcher("(123)").matches();
//		System.out.println(matches);
		java.lang.System.out.println();


		Object o = new Object();
		WeakReference<Object> ref = new WeakReference<Object>(o);
		System.out.println(ref.get());
		o=null;
		System.out.println(ref.get());
		System.gc();
		System.out.println(ref.get());
	}
}
