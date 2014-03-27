name := "scala-dropwizard-oauth-consumer"

version := "0.1"

scalaVersion := "2.10.0"

libraryDependencies ++= Seq(
  "io.dropwizard" % "dropwizard-core" % "0.7.0-rc2",
  "io.dropwizard" % "dropwizard-hibernate" % "0.7.0-rc2",
  "io.dropwizard" % "dropwizard-migrations" % "0.7.0-rc2",
  "com.massrelevance" %% "dropwizard-scala" % "0.7.0-rc1",
  "net.databinder.dispatch" %% "dispatch-core" % "0.11.0",
  "org.json4s" %% "json4s-jackson" % "3.2.7",
  "org.postgresql" % "postgresql" % "9.3-1101-jdbc41"
)

