import sbt._
import Keys._

object AppBuild extends Build {

  lazy val _organization = "com.github.seratch"
  lazy val _version = "0.2.1-SNAPSHOT"

  lazy val scalagPluginProject = Project(
    id = "xsbt-scalag-plugin", 
    base = file("."),
    settings = Defaults.defaultSettings ++ Seq(
      sbtPlugin := true,
      organization := _organization,
      name := "xsbt-scalag-plugin",
      version := _version,
      scalaVersion := "2.9.2",
      crossScalaVersions := Seq("2.9.2", "2.9.1"),
      resolvers ++= _resolvers,
      libraryDependencies <++= (scalaVersion) { scalaVersion => Seq(
        "commons-io"        %  "commons-io"        % "2.4",
        "commons-beanutils" %  "commons-beanutils" % "1.8.0",
        "org.freemarker"    %  "freemarker"        % "2.3.19",
        "org.scalatest"     %% "scalatest"         % "1.7.2"   % "test"
      )},
      publishTo <<= version { _publishTo },
      publishMavenStyle := true,
      publishArtifact in Test := false,
      pomIncludeRepository := { x => false },
      pomExtra := _pomExtra,
      scalacOptions ++= _scalaOptions
    )
  )

  lazy val _resolvers = Seq(
    "sonatype releases" at "http://oss.sonatype.org/content/repositories/releases"
  )

  lazy val _publishTo = { (v: String) =>
    val nexus = "https://oss.sonatype.org/"
    if (v.trim.endsWith("SNAPSHOT")) Some("snapshots" at nexus + "content/repositories/snapshots")
    else Some("releases"  at nexus + "service/local/staging/deploy/maven2")
  }

  lazy val _pomExtra = (
    <url>https://github.com/seratch/xsbt-scalag-plugin</url>
    <licenses>
      <license>
        <name>Apache License, Version 2.0</name>
        <url>http://www.apache.org/licenses/LICENSE-2.0.html</url>
        <distribution>repo</distribution>
      </license>
    </licenses>
    <scm>
      <url>git@github.com:seratch/xsbt-scalag-plugin.git</url>
      <connection>scm:git:git@github.com:seratch/xsbt-scalag-plugin.git</connection>
    </scm>
    <developers>
      <developer>
        <id>seratch</id>
        <name>Kazuhiro Sera</name>
        <url>https://github.com/seratch</url>
      </developer>
    </developers>
  )

  lazy val _scalaOptions = Seq("-deprecation", "-unchecked")

}


