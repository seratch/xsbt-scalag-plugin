import sbt._

object Dev extends Build {

  lazy val root = Project("plugins", file(".")).dependsOn(dev)

  lazy val dev = file("../../")

}
