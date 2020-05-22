package de.uniwue.ir.exercise1

import org.apache.spark.sql.{Column, DataFrame, SparkSession}


object Main {

  def load_corpus(path: String, sparkSession: SparkSession): DataFrame = {
    val corpus = sparkSession.read.text(path)
    corpus
  }

  def run(corpus_path: String, tokenizer: BaseTokenizer, sparkSession: SparkSession) = {
    import sparkSession.implicits._

    // Load reuters
    val df = load_corpus(corpus_path, sparkSession)
    // tokenize reuters
    val token = df.withColumn(
      "value",
      tokenizer.tokenize($"value")).as[String]

    // count types
    token.reduce((a, b) => if (a != b) a else null)


  }
  def main(args: Array[String]): Unit = {
    // initialize spark session
    val spark = SparkSession.builder.master("local").appName("test").getOrCreate()

    // Lower number of partitions for faster execution on a single node
    spark.sqlContext.setConf("spark.sql.shuffle.partitions", "10")

    // run for reuters dataset
    run("assets/corpora/reuters", ReutersTokenizer, spark)

    // run for wiki-a dataset
    //run("assets/corpora/wiki-a", WikiATokenizer, spark)

  }
}
