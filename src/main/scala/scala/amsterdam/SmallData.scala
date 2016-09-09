package scala.amsterdam

//import better.files._

object SmallData extends App {

//  val tagsFile = resource"/tags.txt"
//  val tags = extractTags(tagsFile.getLines().toList)
//  println(tags)

  def extractTags(lines: Seq[String]) = lines.toList
    .map(_.split(":::"))
    .flatMap {
      case Array(topic, tagline) =>  tagline.split(",\\ ?")
    }
    .groupBy(identity)
    .mapValues(_.size)
    .toList.sortBy(_._2  * -1)
}
