package scalag

import org.scalatest._
import org.scalatest.matchers._
import sbt._
import sbt.Keys._
import java.io.File

class ScalagPluginSpec extends FlatSpec with ShouldMatchers {

  behavior of "ScalagPlugin"

  it should "be available" in {
    val plugin = ScalagPlugin
    plugin should not be null
  }

}
