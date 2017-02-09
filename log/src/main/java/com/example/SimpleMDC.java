package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2016-12-05
 */
public class SimpleMDC {

	public static void main(String[] args) {
		MDC.put("first", "Dorothy");

		MDC.put("last", "Parker");

		Logger logger = LoggerFactory.getLogger(SimpleMDC.class);

		logger.info("Check enclosed.");
		logger.debug("The most beautiful two words in English.");

		MDC.put("first", "Richard");
		MDC.put("last", "Nixon");
		logger.info("I am not a crook.");
		logger.info("Attributed to the former US president. 17 Nov 1973.");

	}
}
