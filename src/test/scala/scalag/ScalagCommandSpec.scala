package scalag

import org.scalatest._
import org.scalatest.matchers._

class ScalagCommandSpec extends FlatSpec with ShouldMatchers {

  behavior of "ScalagCommand"

  it should "be available" in {
    val namespace = "class"
    val help: ScalagHelp = ScalagHelp(
      namespace = namespace,
      args = Seq("name"),
      description = "Generates a class"
    )
    val operation: ScalagOperation = {
      case ScalagInput(args, settings) =>
    }
    val cmd = new ScalagCommand(namespace, help, operation)
    cmd should not be null
  }

}
