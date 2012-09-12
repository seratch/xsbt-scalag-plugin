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
    val args = Seq("name")
    val description: String = "yyy"
    val help = new ScalagHelp(namespace, args, description)
    help.namespace should equal("xxx")
    help.args.size should equal(1)
    help.description should equal("yyy")
    help.usageMessage should equal("Usage: g xxx [name]")
  }

}
