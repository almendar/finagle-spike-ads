import sbt.Keys._
import sbt.{Defaults, _}
import sbtassembly._
import sbtassembly.AssemblyKeys._

object Settings {

  val common = Seq(
    organization := "pl.tk",
    version := "1.0.0",
    scalaVersion := "2.11.7",
    scalacOptions := Seq("-unchecked", "-deprecation", "-feature", "-encoding", "utf8"),
    javacOptions ++= Seq("-source", "1.8", "-target", "1.8"),
    resolvers ++= Seq(
      Resolver.sonatypeRepo("releases"),
      "Twitter Maven" at "https://maven.twttr.com"
    ),
    assemblyMergeStrategy in assembly := {
      case "BUILD" => MergeStrategy.discard
      case other => MergeStrategy.defaultMergeStrategy(other)
    }
  )
}