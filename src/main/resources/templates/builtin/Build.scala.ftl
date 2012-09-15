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
        "org.scalatest" %% "scalatest" % "1.8"    % "test",
        "org.specs2"    %% "specs2"    % "1.12.1" % "test"
      )
    },
    scalacOptions ++= Seq("-deprecation", "-unchecked")
  ))

  // lazy val subProject = Project(id = "sub", base = file("sub"))

}

