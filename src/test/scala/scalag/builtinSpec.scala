package scalag

import org.scalatest._
import org.scalatest.matchers._

class builtinSpec extends FlatSpec with ShouldMatchers {

  behavior of "builtin.classCommand"

  it should "be available" in {
    val cmd = builtin.classCommand
    cmd.help.namespace should equal("class")
    cmd.help.description should equal("Generates a new class file")
    cmd.operation should not be null
  }

}
