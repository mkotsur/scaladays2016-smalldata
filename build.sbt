enablePlugins(ScalaJSPlugin)


name := "scaladays2016-smalldata"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies += "com.github.pathikrit" %% "better-files" % "2.16.0"

libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.9.1"
libraryDependencies += "be.doeraene" %%% "scalajs-jquery" % "0.9.0"
libraryDependencies += "eu.unicredit" %%% "paths-scala-js" % "0.4.4"
libraryDependencies += "com.lihaoyi" %%% "scalatags" % "0.5.5"

jsDependencies +=
  "org.webjars" % "jquery" % "2.1.4" / "2.1.4/jquery.js"
