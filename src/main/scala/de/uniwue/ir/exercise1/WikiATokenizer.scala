package de.uniwue.ir.exercise1

import org.apache.spark.sql.expressions.UserDefinedFunction
import org.apache.spark.sql.functions.udf

import scala.util.matching.Regex

object WikiATokenizer extends BaseTokenizer {

  private def _remove_html(text: String): String = {
    val tags = new Regex("<.+?>")
    tags.replaceAllIn(text.toLowerCase(), "")
  }

  private def _split(text: String): Array[String] = {
    val splitter = new Regex("""\W+""")
    splitter.split(text)
  }
  override def tokenize: UserDefinedFunction = udf { text: String => _split(_remove_html(text))}

}
