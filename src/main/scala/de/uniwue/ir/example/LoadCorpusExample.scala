package de.uniwue.ir.example

import org.apache.spark.sql.{DataFrame, SparkSession}

object LoadingCorpusExample {
  
  def loadCorpus(path: String, spark: SparkSession) = {
    
  }

  def run(spark: SparkSession): DataFrame = {

    val corpus: DataFrame = spark.read.text("assets/corpora/reuters")
    //val size = corpus.count
    //println(size)
    corpus.printSchema()
    corpus.show(false)
    corpus

  }
  
  def main(args: Array[String]): Unit = {
        
//    System.setProperty("hadoop.home.dir", "assets\\winutils\\win10x64");

    // initialize spark session
    val spark = SparkSession.builder.master("local").appName("test").getOrCreate()

    // Lower number of partitions for faster execution on a single node
    spark.sqlContext.setConf("spark.sql.shuffle.partitions", "10")

    // run code
    val result = run(spark)
  }
}