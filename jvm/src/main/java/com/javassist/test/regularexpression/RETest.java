package com.javassist.test.regularexpression;

import java.util.regex.Pattern;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2017-02-07
 */
public class RETest {
	public static void main(String[] args) {
		Pattern compile = Pattern.compile("^\\(\\d+\\)$");
		boolean matches = compile.matcher("(123)").matches();
		System.out.println(matches);
	}
}
