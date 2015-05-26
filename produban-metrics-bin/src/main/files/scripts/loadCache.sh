#!/bin/bash

export HADOOP_CONF_DIR=/etc/hadoop/conf
CONF_CLASSPATH="/etc/hadoop/conf/:/usr/metrics/conf/"
for lib in `ls /usr/metrics/lib/*.jar`
do
        if [ -z "$SPARK_CLASSPATH" ]; then
		SPARK_CLASSPATH=CONF_CLASSPATH:$lib
	else
		SPARK_CLASSPATH=$CONF_CLASSPATH:$lib:$SPARK_CLASSPATH
	fi
done 

java -cp $SPARK_CLASSPATH com.produban.bin.LoadCache

