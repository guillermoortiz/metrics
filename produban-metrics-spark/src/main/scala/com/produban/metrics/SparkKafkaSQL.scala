package com.produban.metrics

import scala.collection.JavaConverters._
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import akka.dispatch.Foreach
import org.apache.spark.streaming.StreamingContext
import StreamingContext._
import org.apache.spark.streaming.Seconds
import org.apache.spark.rdd.PairRDDFunctions
import org.apache.spark.streaming.kafka.KafkaUtils
import kafka.serializer.StringDecoder
import org.apache.spark.streaming.kafka.KafkaRDD
import org.apache.spark.streaming.kafka.HasOffsetRanges
import org.apache.spark.streaming.kafka.OffsetRange
import com.produban.metrics.util.Factory
import com.produban.util.JsonUtil
import com.produban.api.manager.CacheManager
import com.produban.cache.redis.RedisCache
import com.produban.metrics.util.FactoryParser

object SparkKafkaSQL {

  def createObject(message: Array[String], topic: Array[String]): String = {
    var obj: Array[String] = new Array[String](message.length)
    var json : String = ""
          
    if (topic(3).equals("PL_EM_ORDEN")) {   
    	val messageFiltered = FactoryParser.parser(topic, message)
    	json = JsonUtil.write(Factory.createPL_EM_ORDEN(topic, messageFiltered))
    	println(json);
    }
    return json
  }

  def main(args: Array[String]) {

    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("Test")
    val ssc = new StreamingContext(sparkConf, Seconds(10))
    //val sc = new SparkContext(sparkConf)

    //TODO read file from HDFS and put in broadcast variable or use REDIS
    val kafkaParams = Map[String, String]("metadata.broker.list" -> "quickstart.cloudera:9092")
    val topics = Set("OB.MATRIX.HOST.PL_EM_ORDEN") //, "internalOpManager", "presOpManager")
    val directKafkaStream = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](ssc, kafkaParams, topics)

    directKafkaStream.foreachRDD { rdd =>
      val offsets = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
      val lines = rdd.mapPartitionsWithIndex { (i, iter) =>

        // index to get the correct offset range for the rdd partition we're working on
        val osr: OffsetRange = offsets(i)

        // get any needed data from the offset range
        val topic = osr.topic
        val splitTopic = topic.split("\\.")
        //Transform lines in 
        iter.map(kafkaEvent => kafkaEvent._2.split("\\|")).map(event => createObject(event, splitTopic))
      }
      val a = lines.map(lines => lines)
      val elem = a.take(1)
      println(elem)
    }

    ssc.start()
    ssc.awaitTermination()
    println("fin");

  }
}