package scala.amsterdam

import scala.scalajs.js.JSApp

object SmallDataJs extends JSApp {


  @scala.scalajs.js.annotation.JSExport
  override def main(): Unit = {
    val tags = getLines.toList
      .map(_.split(":::"))
      .flatMap {
        case Array(topic, tagline) =>  tagline.split(",\\ ?")
      }
      .groupBy(identity)
      .mapValues(_.size)
      .toList.sortBy(_._2  * -1)

    println(tags)
  }

  private def getLines: Seq[String] = {
    Seq("Line ::: tag")
  }


}
