package com.github.lzy.web;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2016-08-05
 */
@Component
@Aspect
public class LogAdvice {
	private static final Logger LOGGER = LoggerFactory.getLogger(LogAdvice.class);


	@Pointcut("execution(* com.github.lzy.web.*.*(..))")
	public void pointCut() {

	}

	@Around("pointCut()")
	public Object doProxy(ProceedingJoinPoint pjp) {
		LOGGER.info("start {}", pjp);
		try {
			return pjp.proceed();
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		} finally {
			LOGGER.info("end {}", pjp);
		}
		return null;
	}
}
