package com.lzy.bigdata;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2017-03-09
 */
public class SimpleApp {
	public static void main(String[] args) {
		String logFile = "/Users/liuzhengyang/Code/opensource/github/lzy/bigdata/src/main/resources/README.md";

		SparkConf sparkConf = new SparkConf().setAppName("Simple Application")
				.setMaster("spark://127.0.0.1:7077");
		JavaSparkContext sc = new JavaSparkContext(sparkConf);
		JavaRDD<String> logData = sc.textFile(logFile).cache();

		long numAs = logData.filter(new Function<String, Boolean>() {
			@Override
			public Boolean call(String log) throws Exception {
				return log.contains("a");
			}
		}).count();

		long numBs = logData.filter(new Function<String, Boolean>() {
			@Override
			public Boolean call(String log) throws Exception {
				return log.contains("b");
			}
		}).count();

		System.out.println("Lines with a: " + numAs + ", lines with b:" + numBs);

		sc.stop();
	}
}
