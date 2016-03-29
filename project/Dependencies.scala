import sbt.Keys._
import sbt._

object Dependencies {
  val all = Seq(
    "com.twitter.finatra" %% "finatra-http" % Versions.finatra,
    "com.twitter.finatra" %% "finatra-httpclient" % Versions.finatra,
    "ch.qos.logback" % "logback-classic" % Versions.logback,

    "com.twitter.finatra" %% "finatra-http" % Versions.finatra % "test",
    "com.twitter.finatra" %% "finatra-jackson" % Versions.finatra % "test",
    "com.twitter.inject" %% "inject-server" % Versions.finatra % "test",
    "com.twitter.inject" %% "inject-app" % Versions.finatra % "test",
    "com.twitter.inject" %% "inject-core" % Versions.finatra % "test",
    "com.twitter.inject" %% "inject-modules" % Versions.finatra % "test",
    "com.google.inject.extensions" % "guice-testlib" % Versions.guice % "test",

    "org.webjars" % "swagger-ui" % "2.1.1",

    "com.twitter.finatra" %% "finatra-http" % Versions.finatra % "test" classifier "tests",
    "com.twitter.finatra" %% "finatra-jackson" % Versions.finatra % "test" classifier "tests",
    "com.twitter.inject" %% "inject-server" % Versions.finatra % "test" classifier "tests",
    "com.twitter.inject" %% "inject-app" % Versions.finatra % "test" classifier "tests",
    "com.twitter.inject" %% "inject-core" % Versions.finatra % "test" classifier "tests",
    "com.twitter.inject" %% "inject-modules" % Versions.finatra % "test" classifier "tests",

    "org.mockito" % "mockito-core" % "1.9.5" % "test",
    "org.scalatest" %% "scalatest" % "2.2.3" % "test",
    "org.specs2" %% "specs2" % "2.3.12" % "test")
}