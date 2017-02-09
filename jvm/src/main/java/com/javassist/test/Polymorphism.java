package com.javassist.test;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2016-08-05
 */
public class Polymorphism {
	public static class Base {
		void test() {
			System.out.println("base");
		}
	}
	public static class Sub1 extends Base {
		@Override
		void test() {
			System.out.println("sub1");
		}
	}
	public static class Sub2 extends Base {
		@Override
		void test() {
			System.out.println("sub2");
		}
	}

	public static void main(String[] args) {
		Base sub1 = new Sub1();
		Base sub2 = new Sub2();
		sub1.test();
		sub2.test();

	}
}
