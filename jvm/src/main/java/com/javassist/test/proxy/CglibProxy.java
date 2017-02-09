package com.javassist.test.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2017-02-06
 */
public class CglibProxy {
	private static class Hello {
		public Hello() {
		}

		@TimeElapsed
		public void say() {
			System.out.println("Hello");
		}
	}

	private static class AroundIntercept implements MethodInterceptor {

		@Override
		public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
			boolean annotationPresent = method.isAnnotationPresent(TimeElapsed.class);
			if (annotationPresent) {
				System.out.println("Before");
				long start = System.currentTimeMillis();
				Object invoke = proxy.invokeSuper(obj, args);
				System.out.println("After");
				long end = System.currentTimeMillis();
				System.out.println("Cost " + (end - start));
				return invoke;
			}
			return method.invoke(obj, args);
		}
	}

	public static <T> T enhanced(Object obj) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(obj.getClass());
		enhancer.setCallback(new AroundIntercept());
		return (T)enhancer.create();
	}

	public static void main(String[] args) {
		Hello hello = enhanced(new Hello());
		hello.say();
	}
}
