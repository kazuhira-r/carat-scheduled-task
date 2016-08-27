name := "carat-scheduled-task"

version := "0.1.0"

organization := "org.littlewings"

scalaVersion := "2.11.8"

scalacOptions ++= Seq("-Xlint", "-deprecation", "-unchecked", "-feature")

updateOptions := updateOptions.value.withCachedResolution(true)

enablePlugins(JettyPlugin)

webappWebInfClasses := true

artifactName := {
  (scalaVersion: ScalaVersion, module: ModuleID, artifact: Artifact) =>
    // artifact.name + "." + artifact.extension
    "ROOT." + artifact.extension
}

fork in Test := true

val javaeeWebApiVersion = "7.0"
val deltaSpikeVersion = "1.7.1"
val quartzVersion = "2.2.3"
val weldVersion = "2.3.5.Final"
val jsoupVersion = "1.9.2"
val twitter4jVersion = "4.0.4"
val slf4jVersion = "1.7.21"
val scalaTestVersion = "3.0.0"
val junitVersion = "4.12"
val junitInterfaceVersion = "0.11"
val mockitoVersion = "1.10.19"

libraryDependencies ++= Seq(
  "javax" % "javaee-web-api" % javaeeWebApiVersion % Provided,
  "org.apache.deltaspike.core" % "deltaspike-core-api" % deltaSpikeVersion % Compile,
  "org.apache.deltaspike.core" % "deltaspike-core-impl" % deltaSpikeVersion % Runtime,
  "org.apache.deltaspike.modules" % "deltaspike-scheduler-module-api" % deltaSpikeVersion % Compile,
  "org.apache.deltaspike.modules" % "deltaspike-scheduler-module-impl" % deltaSpikeVersion % Runtime,
  "org.apache.deltaspike.modules" % "deltaspike-test-control-module-api" % deltaSpikeVersion % Test,
  "org.apache.deltaspike.modules" % "deltaspike-test-control-module-impl" % deltaSpikeVersion % Test,
  "org.apache.deltaspike.cdictrl" % "deltaspike-cdictrl-weld" % deltaSpikeVersion % Runtime,
  "org.quartz-scheduler" % "quartz" % quartzVersion % Compile,
  "org.jboss.weld.se" % "weld-se-core" % weldVersion % Test,
  "org.jsoup" % "jsoup" % jsoupVersion % Compile,
  "org.twitter4j" % "twitter4j-core" % twitter4jVersion % Compile,
  "org.slf4j" % "slf4j-api" % slf4jVersion % Compile,
  "org.slf4j" % "slf4j-simple" % slf4jVersion % Test,
  "org.scalatest" % "scalatest_2.11" % scalaTestVersion % Test,
  "junit" % "junit" % junitVersion % Test,
  "com.novocode" % "junit-interface" % junitInterfaceVersion % Test,
  "org.mockito" % "mockito-all" % mockitoVersion % Test
)
