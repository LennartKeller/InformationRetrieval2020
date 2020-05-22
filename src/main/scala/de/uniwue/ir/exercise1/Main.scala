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
    //
    val token = df.withColumn(
      "value",
      tokenizer.tokenize($"value")).as[String]

    token.show(false)
    println(token.getClass)
    // count types
    val token_flattened = Array[String]()
    token("values").ae
    val types = Set(token_flattened)
    println("Number of types: " + types)
    println("Number of tokens: " + token_flattened.length)

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
