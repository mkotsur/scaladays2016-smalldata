package scala.amsterdam

import better.files._

import org.scalajs.dom
import dom.document

object SmallData extends App {


  val tagsFile = resource"/tags.txt"
  val tags = tagsFile.getLines().toList
    .map(_.split(":::"))
    .flatMap {
      case Array(topic, tagline) =>  tagline.split(",\\ ?")
    }
    .groupBy(identity)
    .mapValues(_.size)
    .toList.sortBy(_._2  * -1)

  println(tags)
}
