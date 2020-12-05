name := """TweeterLytics"""
organization := "com.tweeterlytics"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.3"

libraryDependencies += guice

libraryDependencies += "org.twitter4j" % "twitter4j-core" % "4.0.7"

libraryDependencies ++= Seq(
  caffeine,
  "org.mockito" % "mockito-core" % "3.6.0"
)

val AkkaVersion = "2.6.5"

libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % AkkaVersion % Test

// Compile the project before generating Eclipse files, so
// that generated .scala or .class files for views and routes are present

EclipseKeys.preTasks := Seq(compile in Compile, compile in Test)

// Java project. Don't expect Scala IDE
EclipseKeys.projectFlavor := EclipseProjectFlavor.Java

// Use .class files instead of generated .scala files for views and routes
EclipseKeys.createSrc := EclipseCreateSrc.ValueSet(EclipseCreateSrc.ManagedClasses, EclipseCreateSrc.ManagedResources)