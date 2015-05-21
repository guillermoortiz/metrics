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

object MetricsInternationalSpark {

  /**
   * Create a Tuple (documentType,json)
   */
  def createObject(transEmit: Array[String], topic: Array[String]): (String, String) = {
    var documentType: String = ""
    var json: String = ""

   
    return (documentType, json)
  }

  def main(args: Array[String]) {
//    if (args.length != 2) {
//      println("It's neccesary one parameter");
//      println("First, zookeeper paratemers separater by ,. Ex: quickstart01.cloudera:9092,quickstart02.cloudera:9092");
//      System.exit(-1);
//    }

    val sparkConf = new SparkConf().setMaster("local[4]").setAppName("app")
    //val sparkConf = new SparkConf()
    val ssc = new StreamingContext(sparkConf, Seconds(1))

    val kafkaParams = Map[String, String]("metadata.broker.list" -> "quickstart.cloudera:9092")
    //    val topicBankData = Set("OB.MATRIX.HOST.HH_DATOS_BANCOS")
    //    val topicTransEmit = Set("OB.MATRIX.HOST.HH_TRANSF_EMIT")
    val topicBankData = Set("RDD2")
    val topicTransEmit = Set("RDD1")
    val interTransEmit = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](ssc, kafkaParams, topicTransEmit.toSet)
    val internationalBanks = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](ssc, kafkaParams, topicBankData.toSet)

    val interTransEmitStream = interTransEmit.window(Seconds(10), Seconds(5))
    val internationalBanksStream = internationalBanks.window(Seconds(10), Seconds(5))

    val jEmitTrans = interTransEmitStream.map { register =>
      var splitRegister = register._2.split("\\|")
      var newArray = new Array[String](splitRegister.length + 1)
      
      println("Primera vez:" + splitRegister.length);
      
      if (splitRegister.length == 2) {
        
        splitRegister.copyToArray(newArray)
        newArray(splitRegister.length) = "0"
      } else {
        splitRegister(splitRegister.length) = "1"
        splitRegister.copyToArray(newArray)
      }

      (splitRegister(1), newArray)
    }
    println("*************************EMITIDAS:");
    jEmitTrans.print()
    println("*************************FIN EMITIDAS:");

    val jBanks = internationalBanksStream.mapValues { register =>
      val splitRegister = register.split("\\|")
      (splitRegister(1), splitRegister)
    }
    println("*************************jBanks:");
    jBanks.print()
    println("*************************FIN jBanks:");
    
    val transFitered = jEmitTrans.filter{records => 
      records._2(records._2.length -1) == 1}
    println("*************************filtrados:");
    transFitered.print()
    println("*************************FIN filtrados:");
    
    val cogroup = transFitered.cogroup(jBanks)
    println("*************************Cogroup:");
    cogroup.print()
    println("FIN *************************Cogroup:");      

    ssc.start()
    ssc.awaitTermination()
  }
}