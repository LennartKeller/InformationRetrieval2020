package de.uniwue.ir.example

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{sum, udf}

object SimpleSparkExample {
  
  def run(spark: SparkSession) = {
    // Automatically load encoders for most common types (String, Long, Int, etc.)
    import spark.implicits._

    // define data
    val data = Seq(1,2,3,4,5,6,7).toDS()


    // increase data by 1 using multiple methods

    // with built-in spark-native function:
    // List of built-in spark-native functions: https://spark.apache.org/docs/2.4.5/api/sql/index.html
    val dataInc1 = data.withColumn("value", $"value" + 1).as[Int]

    // with user-defined function:
    // Note: User-defined functions are typically less efficient compared to spark-native functions as they
    // are handled as a black-box by Spark
    val incByOne = udf { i: Int => i + 1 }
    val dataInc2 = data.withColumn("value", incByOne($"value")).as[Int]

    // with map:
    // Note: This is also often less efficient than spark-native functions.
    val dataInc3 = data.map(_ + 1)

    
    // calculate the sum of increased data using three different methods
    val sum1 = dataInc1.reduce(_ + _)
    val sum2 = dataInc1.groupBy().sum().first.getLong(0)
    val sum3 = dataInc1.agg(sum($"value")).first.getLong(0)

    // return sums
    (sum1, sum2, sum3)
    
  }
  
  def main(args : Array[String]) {
    
    // initialize spark session
    val spark = SparkSession.builder.master("local").appName("test").getOrCreate()

    // Lower number of partitions for faster execution on a single node
    spark.sqlContext.setConf("spark.sql.shuffle.partitions", "10")

    // run example
    val result = run(spark)
    
    // print results
    println(result)
  }

}
