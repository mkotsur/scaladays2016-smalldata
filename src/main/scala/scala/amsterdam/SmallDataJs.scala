package scala.amsterdam

import org.scalajs.dom
import org.scalajs.dom._
import org.scalajs.dom.html.Input
import org.scalajs.dom.raw.File
import org.scalajs.jquery._
import paths.high.Bar
import scala.scalajs.js.annotation.JSExport

import scala.scalajs.js.JSApp
import scalatags.JsDom.all._
import scalatags.JsDom.svgTags.{g, path, svg, text}

object SmallDataJs extends JSApp {

  @JSExport
  override def main(): Unit = {
    val body: JQuery = jQuery("body")
    val fileSelect: Input = input("type".attr := "file").render
    fileSelect.addEventListener("change", (e: dom.Event) => {
      val file: File = fileSelect.files(0)
      val reader = new dom.FileReader()
      reader.readAsText(file)
      reader.onload = (fileLoaded: UIEvent) => {
        val file: String = reader.result.asInstanceOf[String]
        val tags = SmallData.extractTags(file.split("\n"))

        val bar = Bar[(String, Int)](
          data = tags.map(Seq(_)),
          accessor = _._2,
          width = 900,
          height = 500,
          gutter = 30
        )
        val bars = bar.curves map { curve =>
            g(
              path("d".attr := curve.line.path.print(), "fill".attr := "green"),
              text("transform".attr := s"translate(${curve.line.centroid}) rotate(270)", curve.item.toString())
            )
        }

        body.append(svg("width".attr := 2000, "height".attr := 1000)(
          g("transform".attr := "translate(80, 50)")(bars:_*)
        ).render)
      }
    })

    body.append(fileSelect)
  }

}
