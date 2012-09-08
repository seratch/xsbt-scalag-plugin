package scalag

import org.scalatest._
import org.scalatest.matchers._
import sbt._
import sbt.Keys._
import java.io.File

class ScalagCommandSpec extends FlatSpec with ShouldMatchers {

  behavior of "ScalagCommand"

  it should "be available" in {
    val help: ScalagHelp = ScalagHelp(
      namespace = "class",
      description = "Generates a class"
    )
    val operation: ScalagOperation = {
      case ScalagInput(args, settings) =>
    }
    val cmd = new ScalagCommand(help, operation)
    cmd should not be null
  }

}
