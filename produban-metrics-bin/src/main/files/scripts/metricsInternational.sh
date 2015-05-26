#!/bin/bash

export HADOOP_CONF_DIR=/etc/hadoop/conf
SPARK_CLASSPATH="file:/usr/metrics/conf/elasticSearch.properties,file:/usr/metrics/conf/redis.properties,/etc/spark/conf.cloudera.spark_on_yarn/yarn-conf/"
for lib in `ls /usr/metrics/lib/*.jar`
do
        if [ -z "$SPARK_CLASSPATH" ]; then
		SPARK_CLASSPATH=$lib
	else
		SPARK_CLASSPATH=$SPARK_CLASSPATH,$lib
	fi
done 

spark-submit --name "Metrics International Tranferences" --master yarn-client --class com.produban.metrics.MetricsTransfInternationalSpark --jars $SPARK_CLASSPATH --executor-memory 1g /usr/metrics/ex/produban-metrics-spark.jar
