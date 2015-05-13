package com.produban.bin;


import com.produban.metrics.SparkKafkaSQL;



public class MetricsBin {
	public static void main(String[] args) {	
//		String appName = args[0];
//		String masterConf = args[1];
//		String[] topics = args[2].split("\\,");
//		String hosts = args[3];		
		
		String appName = "App Metrics";
		String masterConf = "local[2]";
		String[] topics = "OB.MATRIX.HOST.PL_EM_ORDEN".split("\\,");
		String hosts = "192.168.85.143:9092";
		
		System.out.println("Starting Metrics with Spark");
		SparkKafkaSQL.executeMetrics(topics, hosts, appName, masterConf);
		System.out.println("Ending Metrics with Spark");
	}
}
