package scalag

import org.scalatest._
import org.scalatest.matchers._

class ScalagPluginSpec extends FlatSpec with ShouldMatchers {

  behavior of "ScalagPlugin"

  it should "be available" in {
    ScalagPlugin.g should not be null
    ScalagPlugin.generate should not be null
    ScalagPlugin.scalagSettings should not be null
  }

}
