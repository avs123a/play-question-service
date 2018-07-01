name := """Java-Play-Question-REST-Service"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  javaJpa,
  "org.hibernate" % "hibernate-entitymanager" % "5.3.1.Final",
  "org.postgresql" % "postgresql" % "9.4-1206-jdbc42"
)

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its  actions statically.
routesGenerator := InjectedRoutesGenerator
