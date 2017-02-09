package com.github.lzy.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2016-08-05
 */
@RestController
@EnableAutoConfiguration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(basePackages = "com.github.lzy.web")
public class MainController {
	@Resource
	private IProxyService proxyService;

	@RequestMapping("/test")
	public Object test() {
		proxyService.methodOne();
		return "ok";
	}

	public static void main(String[] args) {
		SpringApplication.run(MainController.class);
	}
}
