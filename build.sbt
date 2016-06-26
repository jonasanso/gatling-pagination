import io.gatling.sbt.GatlingPlugin

scalaVersion := "2.11.8"

scalacOptions := Seq("-unchecked", "-deprecation", "-feature", "-encoding", "utf8")


libraryDependencies ++= Seq(
  "io.gatling.highcharts"    % "gatling-charts-highcharts"  % "2.2.2" % IntegrationTest,
  "io.gatling"               % "gatling-test-framework"     % "2.2.2" % IntegrationTest
)


lazy val loadTests = project.in(file("."))
  .configs(IntegrationTest)
  .configs(GatlingIt)
  .settings(Defaults.itSettings: _*).enablePlugins(GatlingPlugin)
