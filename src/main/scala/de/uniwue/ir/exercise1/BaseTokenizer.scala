package de.uniwue.ir.exercise1

import org.apache.spark.sql.expressions.UserDefinedFunction

trait BaseTokenizer {

  def tokenize: UserDefinedFunction

}
