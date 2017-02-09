package com.github.lzy.web;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2016-08-09
 */
@Service
public class SelfAwareBeanPostProcessor implements BeanPostProcessor, Ordered {
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof SelfAware) {
			((SelfAware) bean).setMe(bean);
		}
		return bean;
	}


	@Override
	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE;
	}
}
