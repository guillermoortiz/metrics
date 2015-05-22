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
import com.produban.metrics.util.FilterMetrics
import com.produban.metrics.util.KMetrics
import scala.collection.mutable.ArrayBuffer

object MetricsInternationalVersion2 {
  val topicBank = "OB.MATRIX.HOST.HH_DATOS_BANCOS".split("\\.")
  val topicTransf = "OB.MATRIX.HOST.HH_TRANSF_EMIT".split("\\.")

  /**
   * Create a Tuple (documentType,json)
   */
  def createObject(transf: Iterable[Array[String]], banks: Iterable[Array[String]]): (String, String) = {
    val bankData: ArrayBuffer[String] = ArrayBuffer();

    for (bankMessage <- banks) {
      val extraData = FactoryParser.parser(topicBank, bankMessage)
      bankData ++= extraData
    }
    val metrics = FactoryCreator.createMetric(topicTransf, transf.head, bankData.toArray)
    val documentType = metrics.getDATOS_P().getTabla()
    val json = JsonUtil.write(metrics)

    return (documentType, json)
  }

  def main(args: Array[String]) {
    if (args.length != 1) {
      println("It's neccesary one parameter");
      println("First, zookeeper paratemers separater by ,. Ex: quickstart01.cloudera:9092,quickstart02.cloudera:9092");
      System.exit(-1);
    }

    val sparkConf = new SparkConf().setMaster("local[4]").setAppName("app")
    //val sparkConf = new SparkConf()
    val ssc = new StreamingContext(sparkConf, Seconds(5))

    val kafkaParams = Map[String, String]("metadata.broker.list" -> args(0))
    val interTransEmit = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](ssc, kafkaParams, topicTransf.toSet)
    val internationalBanks = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](ssc, kafkaParams, topicBank.toSet)

    val interTransEmitStream = interTransEmit.window(Seconds(10), Seconds(5))
    val internationalBanksStream = internationalBanks.window(Seconds(10), Seconds(5))

    //Clean and split message        
    val emitTrans = interTransEmitStream.map { event =>
      val eventCleaned = event._2.replace("\"", "")
      eventCleaned.split("\\|")
    }
    //Check the mandatories fields for HH_TRANSF_EMIT.
    val emTransFiltered = emitTrans.filter(event => FilterMetrics.filter("HH_TRANSF_EMIT", event))

    //Generate tuple (idJoin, recordTransfer)
    val tupleEmTranf = emTransFiltered.map { register =>
      val id = register(KMetrics.HH_TRANSF_EMIT.INDEX_BANCO_ORIGEN) +
        register(KMetrics.HH_TRANSF_EMIT.INDEX_SUCURSAL_ORIGEN) +
        register(KMetrics.HH_TRANSF_EMIT.INDEX_CHECK1) +
        register(KMetrics.HH_TRANSF_EMIT.INDEX_CHECK2)
      (id, register)
    }

    println("*************************EMITIDAS:");
    tupleEmTranf.print()
    println("*************************FIN EMITIDAS:");

    //generate tuples(documentType,jsonMessage) for each message got from Kafka        
    val banks = internationalBanksStream.map { event =>
      val eventCleaned = event._2.replace("\"", "")
      eventCleaned.split("\\|")
    }
    //Check the mandatories fields for HH_TRANSF_EMIT.
    val banksFiltered = emitTrans.filter(event => FilterMetrics.filter("HH_DATOS_BANCOS", event))

    //Generate tuple (idJoin, recordBank)
    val tupleBanks = banksFiltered.map { register =>
      val id = register(KMetrics.HH_DATOS_BANCOS.INDEX_CHECK1) +
        register(KMetrics.HH_DATOS_BANCOS.INDEX_CHECK2) +
        register(KMetrics.HH_DATOS_BANCOS.INDEX_CHECK3) +
        register(KMetrics.HH_DATOS_BANCOS.INDEX_CHECK4)
      (id, register)
    }

    //Cogroup tables and filter where I got the log from the tansfer table
    val joinTables = tupleEmTranf.cogroup(tupleBanks).filter(t => t._2._1.size > 0)
    
    //Create the JSON object (idElastic, json)
    val documents = joinTables.map { join =>
      val id = join._1
      val tranf = join._2._1
      val banks = join._2._2
      
      (id, createObject(tranf, banks))
    }

    //Indez in Elastic
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