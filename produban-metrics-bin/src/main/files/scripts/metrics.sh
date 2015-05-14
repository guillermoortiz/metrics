#!/bin/bash

export HADOOP_CONF_DIR=/etc/hadoop/conf
SPARK_CLASSPATH="/usr/metrics/conf/,"
for lib in `ls /usr/metrics/lib/*.jar`
do
        if [ -z "$SPARK_CLASSPATH" ]; then
		SPARK_CLASSPATH=$lib
	else
		SPARK_CLASSPATH=$lib,$SPARK_CLASSPATH
	fi
done

spark-submit --name "Metrics" --master yarn-client --class com.produban.metrics.MetricsSpark --jars $SPARK_CLASSPATH --executor-memory 1g /usr/metrics/ex/produban-metrics-spark.jar
