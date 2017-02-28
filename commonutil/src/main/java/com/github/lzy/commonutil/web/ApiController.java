package com.github.lzy.commonutil.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2017-02-21
 */
@RestController
@RequestMapping("/api")
public class ApiController {

	@RequestMapping("/test")
	public String test() {
		return "ok";
	}
}
