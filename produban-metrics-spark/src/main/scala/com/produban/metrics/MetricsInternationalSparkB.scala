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

object MetricsInternationalSparkB {

  /**
   * Create a Tuple (documentType,json)
   */
  def createObject(transf: Iterable[Array[String]], banks: Iterable[Array[String]]): (String, String) = {
    var idElastic: String = ""
    var json: String = ""

    //I only have one interanational tranfer and N banks. Filter banks with "B" field.  
    val transObj = transf.toArray
    val banksArray = banks.filter(bank => bank(25).equals("B")).toArray

    //    val messageFiltered = FactoryParser.parser(topic, transObj, banksArray)
    //    val obj = FactoryCreator.create(topic, transObj, banksArray)
    //    documentType = obj.getDATOS_P().getTabla()
    //    json = JsonUtil.write(obj)  
    //         
    //    

    return (idElastic, json)
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
      val id = splitRegister(51) + splitRegister(52) + splitRegister(53) + splitRegister(54)
      (id, splitRegister)
    }
    println("*************************EMITIDAS:");
    jEmitTrans.print()
    println("*************************FIN EMITIDAS:");

    val jBanks = internationalBanksStream.map { register =>
      val splitRegister = register._2.split("\\|")
      val id = splitRegister(21) + splitRegister(22) + splitRegister(23) + splitRegister(24)
      (id, splitRegister)
    }
    println("*************************jBanks:");
    jBanks.print()
    println("*************************FIN jBanks:");

    val cogroup = jEmitTrans.cogroup(jBanks)
    val documents = cogroup.map { join => 
      val id = join._1
      val tranf = join._2._1
      val banks = join._2._2
      (id, createObject(tranf, banks))
    }

    documents.foreachRDD { rdd =>
      rdd.foreachPartition { rddPartition =>
        val indexer = Factory.getIndexerManager()

        rddPartition.foreach { record =>
          val id = record._1
          val json = record._2._2
          val docType = record._2._1
          indexer.indexWithId(json, id, docType)
        }
      }
    }

    ssc.start()
    ssc.awaitTermination()
  }
}