ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.10"

lazy val root = (project in file("."))
  .settings(
    name := "kafka-test-containers"
  )


libraryDependencies += "com.dimafeng" %% "testcontainers-scala" % "0.25.0" % "test"
libraryDependencies += "org.apache.kafka" % "kafka-clients" % "2.2.0"
// https://mvnrepository.com/artifact/com.dimafeng/testcontainers-scala-kafka
libraryDependencies += "com.dimafeng" %% "testcontainers-scala-kafka" % "0.40.12" % Test

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.15" % Test


