package com.javassist.test;

import javassist.NotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2016-08-04
 */
public class Main {
	public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		System.out.println("Hello World");
//		Base b = new Base();
//		Base s = new Sub();
//		b.say(2);
//		s.say(1);
		List<Base> list = new ArrayList<>();
		Class<?> aClass = new CustomClassLoader().loadClass(Base.class.getName());
		Object o = aClass.newInstance();
//		list.add((Base) o);
		System.out.println(list.getClass().getClassLoader());
		System.out.println(aClass.getClassLoader());
		System.out.println(Thread.currentThread().getContextClassLoader());
		Thread.currentThread().setContextClassLoader(new CustomClassLoader());
		System.out.println(Thread.currentThread().getContextClassLoader());
	}

	public static class MyClassLoader extends URLClassLoader {

		public MyClassLoader(URL[] urls, ClassLoader parent) {
			super(urls, parent);
		}
	}

	public static class CustomClassLoader extends ClassLoader {
		@Override
		public Class<?> loadClass(String name) throws ClassNotFoundException {
			if (name.equals(Base.class.getName())) {
				String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
				InputStream inputStream = getClass().getResourceAsStream(fileName);
				try {
					byte[] bytes = new byte[inputStream.available()];
					inputStream.read(bytes);
					return defineClass(name, bytes, 0, bytes.length);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return super.loadClass(name);
		}
	}

	public static class Base {
		public void say(int a) {
			System.out.println(a);
		}
	}

	public static class Sub extends Base {
		@Override
		public void say(int a) {
			System.out.println("sub " + a);
		}
	}
}
