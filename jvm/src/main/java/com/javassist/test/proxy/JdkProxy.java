package com.javassist.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2016-08-09
 */
public class JdkProxy {

	public static interface Targetable {
		void say(String world);
	}

	public static class Target implements Targetable{
		public void say(String world) {
			System.out.println(world);
		}
	}

	public static class MyInvocationHandler implements InvocationHandler {
		private Object proxyed;

		public MyInvocationHandler(Object proxyed) {
			this.proxyed = proxyed;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			System.out.println("Before");
//			System.out.println(proxy);
			return method.invoke(proxyed, args);
		}
	}

	public static void main(String[] args) {
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
		Target target = new Target();
		Targetable proxy = (Targetable) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{Targetable.class}, new MyInvocationHandler(target));
		System.out.println(proxy.toString());
		proxy.say("hello");
	}
}
