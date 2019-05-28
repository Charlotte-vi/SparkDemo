package generator

import java.io.BufferedWriter

import scala.collection.mutable.ListBuffer
import scala.util.Random

object SinglePathGenerator {


  def generator(jsonPath: String,
                occurProbabilityMap: Map[String, String],
                randomBase: Int,
                startDay: String,
                endDay: String,writer:BufferedWriter): ListBuffer[String] = {

    assert(occurProbabilityMap.foldLeft(0f)((a, b) => a + b._2.toFloat) == 1.0000, "probability should be 1")
    val random = new Random()
    val daysList: List[String] = Helper.getBetweenDates(startDay, endDay)
    //JsonPath    OccurNumber     TimeStamp
    val resultList: ListBuffer[String] = new ListBuffer[String]
    var start = 0
    val dataScopeList = occurProbabilityMap.toList.map(data => {
      val end = start + (randomBase * data._2.toFloat).asInstanceOf[Int]
      val dataScope = (data._1, end)
      start = end
      dataScope
    })
    for (day <- daysList) {
      val seed = random.nextInt(randomBase)
      writer.write(jsonPath+"*"+dataScopeList.find(scope => seed < scope._2).get._1.toString+"*"+day)
      writer.newLine()
    }
    resultList
  }




}
