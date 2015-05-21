package com.produban.metrics

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
import org.apache.commons.math3.util.Pair

object MetricsSpark {

  /**
   * Create a Tuple (documentType,json)
   */
  def createObject(message: Array[String], topic: Array[String]): (String,String) = {
//    var documentType: String = ""
//    var json: String = ""
//
//    if (topic(3).equals("PL_EM_ORDEN")) {
//      val messageFiltered = FactoryParser.parser(topic, message)
//      //topico, mensaje, parametrosExtra
//      val obj = FactoryCreator.createPL_EM_ORDEN(topic, messageFiltered)
//      documentType = obj.getDATOS_P().getTabla()
//      json = JsonUtil.write(obj)
//    }
//    
    val extraData = FactoryParser.parser(topic, message)
    val obj = FactoryCreator.create(topic, message, extraData)
    val documentType = obj.getDATOS_P().getTabla()
    val json = JsonUtil.write(obj)
     
    return (documentType,json)
  }
  
  def main(args: Array[String]) {
    if (args.length != 2){
      println("It's neccesary two parameter");
      println("First, zookeeper paratemers separater by ,. Ex: quickstart01.cloudera:9092,quickstart02.cloudera:9092");
      println("Second, name of the topics to read separater by ,. Ex: topic1,topic2");
      System.exit(-1);
    }
    
       
    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("app")
    //val sparkConf = new SparkConf()
    val ssc = new StreamingContext(sparkConf, Seconds(5))
    
    
    //val kafkaParams = Map[String, String]("metadata.broker.list" -> kafkaHosts)
    val kafkaParams = Map[String, String]("metadata.broker.list" -> args(0))
    val topics = args(1).split("\\,")
    //val topics = Set("OB.MATRIX.HOST.PL_EM_ORDEN") //, "internalOpManager", "presOpManager")
    val directKafkaStream = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](ssc, kafkaParams, topics.toSet)

    directKafkaStream.foreachRDD { rdd =>
      val offsets = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
      val documents = rdd.mapPartitionsWithIndex { (i, kafkaEvent) =>

        // index to get the correct offset range for the rdd partition we're working on        
        val osr: OffsetRange = offsets(i)
        val topic = osr.topic
        val splitTopic = topic.split("\\.")

        //generate tuples(documentType,jsonMessage) for each message got from Kafka
        kafkaEvent.map { event =>
          val splitMessage = event._2.split("\\|")
          createObject(splitMessage, splitTopic)
        }
      }

      //Index each document in ElasticSearch
      documents.foreachPartition { it =>
        val indexer = Factory.getIndexerManager()
        
        //TODO It could be done with Tuple2 directly, it should include scala-library dependency in produban-manager module
        val documentsPair = it.map(document => new Pair(document._1,document._2))
        indexer.indexDocuments(documentsPair.toArray)

        //Less efficient.
//        it.foreach { document =>         
//          indexer.indexDocument(document._2, document._1)
//        }
      }
    }

    ssc.start()
    ssc.awaitTermination()
  }
}