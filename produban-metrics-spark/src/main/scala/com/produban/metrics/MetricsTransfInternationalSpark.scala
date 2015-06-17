package com.produban.metrics

import org.apache.spark.SparkConf
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.Seconds
import kafka.serializer.StringDecoder
import com.produban.metrics.util.FilterMetrics
import com.produban.metrics.util.KMetrics
import com.produban.metrics.util.FactoryParser
import scala.collection.mutable.ArrayBuffer
import com.produban.api.general.Factory
import com.produban.metrics.util.FactoryCreator
import com.produban.util.JsonUtil
import scala.collection.mutable.ListBuffer

object MetricsTransfInternationalSpark {
  val topicBank = "OB.MATRIX.HOST.HH_DATOS_BANCOS"
  val topicTransf = "OB.MATRIX.HOST.HH_TRANSF_EMIT"

  val topicBankSplit = "OB.MATRIX.HOST.HH_DATOS_BANCOS".split("\\.")
  val topicTransfSplit = "OB.MATRIX.HOST.HH_TRANSF_EMIT".split("\\.")

  /**
   * Create a Tuple (documentType,json)
   */
  def createObject(transf: Iterable[Array[String]], banks: Iterable[Array[String]]): (String, String) = {
    val bankData: ArrayBuffer[String] = ArrayBuffer();
    val transferData: ArrayBuffer[String] = ArrayBuffer();
    val bankRawData: ArrayBuffer[String] = ArrayBuffer();

    //create a new array[String] with the concatenation of all banks
    for (bankMessage <- banks) {
      val extraData = FactoryParser.parser(topicBankSplit, bankMessage)
      bankData ++= extraData
      bankRawData ++= bankMessage
    }
    
    //create just one array[String] with concatenation of transference and banks
    val banksLine = bankRawData.toArray
    transferData ++= FactoryParser.parser(topicTransfSplit, transf.head)
    transferData ++= bankData
    val finalLine = transf.head ++ banksLine

    //Generate json to ES
    val metrics = FactoryCreator.createMetric(topicTransfSplit, finalLine, transferData.toArray)
    val documentType = metrics.getDATOS_P().getTabla()
    val json = JsonUtil.write(metrics)

    return (documentType, json)
  }

  def main(args: Array[String]) {
	  if (args.length != 1){
      println("It's neccesary one parameter");
      println("First, kafka brokers paratemers separater by ,. Ex: quickstart01.cloudera:9092,quickstart02.cloudera:9092");      
      System.exit(-1);
    }
    
    
    
    //val sparkConf = new SparkConf().setMaster("local[4]").setAppName("app") 
    val sparkConf = new SparkConf()
    val ssc = new StreamingContext(sparkConf, Seconds(30))

    //val kafkaParams = Map[String, String]("metadata.broker.list" -> kafkaHosts)
    val kafkaParams = Map[String, String]("metadata.broker.list" -> args(0))

    val topic1 = Set(topicTransf)
    val topic2 = Set(topicBank)

    val stream1 = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](ssc, kafkaParams, topic1)
    val stream2 = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](ssc, kafkaParams, topic2)

    val stream1Windows = stream1.window(Seconds(60), Seconds(30))
    val stream2Windows = stream2.window(Seconds(60), Seconds(30))

    
    //generate an Array[Array[String]] for each message got from Kafka but it produce Array[String] because flatmap 
    val emitTransFlat = stream1Windows.flatMap (event =>  event._2.split("\\n"))
    val emitTrans = emitTransFlat.map{
    	event =>
    		val eventCleaned = event.replace("\"", "")    	 
    		eventCleaned.split("\\|")  
    }
            
         
    val emTransFiltered = emitTrans.filter(event => FilterMetrics.filter("HH_TRANSF_EMIT", event)) 

    //Generate tuple (idJoin, recordTransfer)
    val tupleEmTranf = emTransFiltered.map { register =>
      val id = register(KMetrics.HH_TRANSF_EMIT.INDEX_BANCO_ORIGEN) +
        register(KMetrics.HH_TRANSF_EMIT.INDEX_SUCURSAL_ORIGEN) +
        register(KMetrics.HH_TRANSF_EMIT.INDEX_CHECK1) +
        register(KMetrics.HH_TRANSF_EMIT.INDEX_CHECK2)
      (id, register)
    }
    
    val banksFlat = stream2Windows.flatMap (event =>  event._2.split("\\n"))
    val banks = banksFlat.map{
    	event =>
    		val eventCleaned = event.replace("\"", "")    	 
    		eventCleaned.split("\\|")  
    }
    
    
    //Check the mandatories fields for HH_TRANSF_EMIT.
    val banksFiltered = banks.filter(event => FilterMetrics.filter("HH_DATOS_BANCOS", event))     
    
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

      //log      
      tranf.foreach(s => println("T2:" + runtime.ScalaRunTime.stringOf(s)))
      banks.foreach(s => println("B2:" + runtime.ScalaRunTime.stringOf(s)))

      (id, createObject(tranf, banks))
    }

    //Index in Elastic
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
    ssc.awaitTermination();

  }
}