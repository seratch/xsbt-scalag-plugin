import sbt._
import Keys._

object AppBuild extends Build {

  lazy val rootProject = Project(id = "root", base = file("."), settings = Defaults.defaultSettings ++ Seq(
    sbtPlugin := false,
    organization := "${organization}",
    name := "${name}",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "${scalaVersion}",
    crossScalaVersions := Seq("${scalaVersion}"),
    resolvers ++= Seq(
      "sonatype releases" at "http://oss.sonatype.org/content/repositories/releases"
    ),
    libraryDependencies <++= (scalaVersion) { scalaVersion =>
    Seq(
      "org.scalatest" %% "scalatest" % "1.8" % "test"
    )},
    publishTo <<= version { (v: String) =>
      val nexus = "https://oss.sonatype.org/"
        if (v.trim.endsWith("SNAPSHOT")) Some("snapshots" at nexus + "content/repositories/snapshots")
        else Some("releases"  at nexus + "service/local/staging/deploy/maven2")
    },
    publishMavenStyle := true,
    publishArtifact in Test := false,
    pomIncludeRepository := { x => false },
    pomExtra := (
      <url>TODO</url>
      <licenses>
        <license>
          <name>Apache License, Version 2.0</name>
          <url>http://www.apache.org/licenses/LICENSE-2.0.html</url>
          <distribution>repo</distribution>
        </license>
      </licenses>
      <scm>
        <url></url>
        <connection></connection>
      </scm>
      <developers>
      </developers>
    ),
    scalacOptions ++= Seq("-deprecation", "-unchecked")
  ))

  // lazy val subProject = Project(id = "sub", base = file("sub"))

}

