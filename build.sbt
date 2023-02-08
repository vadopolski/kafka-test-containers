ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.10"

lazy val root = (project in file("."))
  .settings(
    name := "kafka-test-containers"
  )


val sparkVersion = "3.1.0"
val scalaTestVersion = "3.2.1"



libraryDependencies ++= Seq(

  "org.apache.spark" %% "spark-sql" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion % Test,
  "org.apache.spark" %% "spark-sql" % sparkVersion % Test classifier "tests",
  "org.apache.spark" %% "spark-catalyst" % sparkVersion % Test,
  "org.apache.spark" %% "spark-catalyst" % sparkVersion % Test classifier "tests",
  "org.apache.spark" %% "spark-hive" % sparkVersion % Test,
  "org.apache.spark" %% "spark-hive" % sparkVersion % Test classifier "tests",
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-core" % sparkVersion % Test classifier "tests",
  // logging
  "log4j" % "log4j" % "1.2.17",
  "org.slf4j" % "slf4j-log4j12" % "1.7.30",

  "com.dimafeng" %% "testcontainers-scala" % "0.25.0" % "test",
  "org.apache.kafka" % "kafka-clients" % "2.2.0",
  "com.dimafeng" %% "testcontainers-scala-kafka" % "0.40.12" % Test,
//  "org.scalatest" %% "scalatest" % "3.2.15" % Test,


  "org.scalatest" %% "scalatest" % scalaTestVersion % Test,
  "org.scalacheck" %% "scalacheck" % "1.14.3" ,
  "org.scalatestplus" %% "scalacheck-1-14" % "3.2.2.0",

  "org.scalikejdbc"         %% "scalikejdbc"                          % "3.5.0"   % Test,
  "org.scalikejdbc"         %% "scalikejdbc-test"                     % "3.5.0"   % Test,
  "com.dimafeng"            %% "testcontainers-scala-postgresql"      % "0.40.11"  % Test,
  "com.dimafeng"            %% "testcontainers-scala-scalatest"       % "0.40.11"  % Test,
  "org.flywaydb"            % "flyway-core"                         % "9.8.3",
  "org.postgresql"          % "postgresql"                            % "42.5.1" ,
)


