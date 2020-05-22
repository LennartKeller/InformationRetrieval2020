package de.uniwue.ir.exercise1

import org.apache.spark.sql.expressions.UserDefinedFunction
import org.apache.spark.sql.functions.udf

object ReutersTokenizer extends BaseTokenizer {
  override def tokenize: UserDefinedFunction = udf { text: String => text.split(" ")}
}
