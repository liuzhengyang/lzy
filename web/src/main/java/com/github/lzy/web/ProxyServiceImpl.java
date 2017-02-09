package com.github.lzy.web;

import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2016-08-05
 */
@Service
public class ProxyServiceImpl implements IProxyService, SelfAware {
	private ProxyServiceImpl meWithProxy;
	@Override
	public void methodOne() {
		meWithProxy.methodTwo();
		System.out.println("one");
		meWithProxy.test();
	}

	private void test() {
		System.out.println("test");
	}

	@Override
	public void methodTwo() {
		System.out.println("two");
	}

	@Override
	public void setMe(Object bean) {
		meWithProxy = (ProxyServiceImpl) bean;
	}
}
