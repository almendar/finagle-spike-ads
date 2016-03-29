//name := "finagle-spike"

Settings.common

lazy val products = project.in(file("products"))
  .settings(Settings.common: _*)
  .settings(libraryDependencies ++= Dependencies.all)

lazy val userProfiles = project.in(file("user-profiles"))
  .settings(Settings.common: _*)
  .settings(libraryDependencies ++= Dependencies.all)

lazy val recommendation = project.in(file("recommendation"))
  .settings(Settings.common: _*)
  .settings(libraryDependencies ++= Dependencies.all)
//  .settings(mainClass in assembly := Some("pl.tk.finagle.recommendation.Recommendation.type"))

addCommandAlias("recommendation1",
  """recommendation/re-start pl.tk.finagle.recommendation.Server
    |-http.port=:8081
    |-admin.port=:9991""".stripMargin)

addCommandAlias("recommendation2",
  """recommendation/re-start pl.tk.finagle.recommendation.Server
    |-http.port=:8082
    |-admin.port=:9992""".stripMargin)