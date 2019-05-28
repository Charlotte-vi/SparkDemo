import scala.util.matching.Regex

/**
  * @author zyp
  */
object Test {
 val file =  """file:/C:/Users/zyp/ali/spark/spark-warehouse/huge2/part-00000-10675214-778f-4838-866b-72a0813588a3-c000"""
 val pattern =  new Regex("""(?s)part-(\d+)-""")

 val x = pattern.findFirstMatchIn(file).get.group(1)

  def main(args: Array[String]): Unit = {
    println(x)
  }
}
