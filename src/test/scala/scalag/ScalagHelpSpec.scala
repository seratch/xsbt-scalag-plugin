package scalag

import org.scalatest._
import org.scalatest.matchers._
import sbt._
import sbt.Keys._
import java.io.File

class ScalagHelpSpec extends FlatSpec with ShouldMatchers {

  behavior of "ScalagHelp"

  it should "be available" in {
    val namespace: String = "xxx"
    val description: String = "yyy"
    val help = new ScalagHelp(namespace, description)
    help.namespace should equal("xxx")
    help.description should equal("yyy")
  }

}
