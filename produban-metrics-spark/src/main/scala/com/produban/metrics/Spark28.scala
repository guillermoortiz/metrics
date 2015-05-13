package com.produban.metrics

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._


object Spark28 {
  
  def main(args: Array[String]) {
      //4,6
	  val conf = new SparkConf().setAppName("SparkStreamingTest").setMaster("local[2]")
	  val sc = new SparkContext(conf)
	  println("aaa");
	
  }
}