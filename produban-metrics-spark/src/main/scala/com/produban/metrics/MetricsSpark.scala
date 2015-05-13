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
import com.produban.util.JsonUtil
import com.produban.api.general.Factory
import com.produban.api.manager.CacheManager
import com.produban.cache.redis.RedisCache
import com.produban.metrics.util.FactoryParser
import com.produban.indexer.elastic.ElasticIndexer
import com.produban.metrics.util.FactoryCreator
import scala.collection.JavaConverters

object SparkKafkaSQL {

  def createObject(message: Array[String], topic: Array[String]): String = {
    var obj: Array[String] = new Array[String](message.length)
    var json: String = ""

    if (topic(3).equals("PL_EM_ORDEN")) {
      val messageFiltered = FactoryParser.parser(topic, message)
      json = JsonUtil.write(FactoryCreator.createPL_EM_ORDEN(topic, messageFiltered))
    }
    return json
  }

  
  def executeMetrics(topics : Array[String], kafkaHosts : String, appName : String, masterConf : String) {    
    
    
    
    val sparkConf = new SparkConf().setMaster(masterConf).setAppName(appName)
    val ssc = new StreamingContext(sparkConf, Seconds(5))

   
    val kafkaParams = Map[String, String]("metadata.broker.list" -> kafkaHosts)
    val topics = Set("OB.MATRIX.HOST.PL_EM_ORDEN") //, "internalOpManager", "presOpManager")
    val directKafkaStream = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](ssc, kafkaParams, topics.toSet)

    directKafkaStream.foreachRDD { rdd =>
      val offsets = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
      val documents = rdd.mapPartitionsWithIndex { (i, kafkaEvent) =>

        // index to get the correct offset range for the rdd partition we're working on        
        val osr: OffsetRange = offsets(i)
        val topic = osr.topic
        val splitTopic = topic.split("\\.")

        //generate tuples(topicName,jsonMessage) for each message got from Kafka
        kafkaEvent.map { event =>
          val splitMessage = event._2.split("\\|")
          (splitTopic(3), createObject(splitMessage, splitTopic))
        }
      }

      //Index each document in ElasticSearch
      documents.foreachPartition { it =>
        val indexer = Factory.getIndexerManager()
        it.foreach { document =>
          //TODO Change the code to do in a bulkload for performance
          indexer.indexDocument(document._2, document._1)
        }
      }
    }

    ssc.start()
    ssc.awaitTermination()
  }
}