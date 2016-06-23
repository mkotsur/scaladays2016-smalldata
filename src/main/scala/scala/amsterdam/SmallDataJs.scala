package scala.amsterdam

import org.scalajs.dom
import org.scalajs.dom._
import org.scalajs.dom.raw.File
import org.scalajs.jquery._
import paths.high.Bar

import scalatags.JsDom.all._
import scalatags.JsDom.svgTags.{g, path, svg, text}
import scala.scalajs.js.JSApp

object SmallDataJs extends JSApp {


  @scala.scalajs.js.annotation.JSExport
  override def main(): Unit = {
    val body = jQuery("body")

    val fileSelect = input(`type` := "file").render
    fileSelect.addEventListener("change", (e: dom.Event) => {
      e.stopPropagation()
      e.preventDefault()
      val file: File = fileSelect.files(0)
      val reader = new dom.FileReader()
      reader.readAsText(file)

      reader.onload = (e: UIEvent) => {
        val contents = reader.result.asInstanceOf[String]
        val tags = SmallData.extractTags(contents.split("\n"))

        val bar = Bar[(String, Int)](
          data = tags.map(Seq(_)),
          accessor = _._2,
          width = 900,
          height = 500,
          gutter = 30
        )

        val rectangles = bar.curves map { case curve =>
          g(
            path("d".attr := curve.line.path.print, "fill".attr := "rgb(130,140,210)"),
            text("transform".attr := s"translate(${curve.line.centroid}) rotate(270)", curve.item._1)
          )
        }

        body.append(svg("width".attr := "2000", "height".attr := "1000")(
          g("transform".attr := "translate(80,50)")(rectangles:_*)
        ).render)
      }

    })

    body.append(fileSelect)
  }

}
