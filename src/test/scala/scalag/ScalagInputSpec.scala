package scalag

import org.scalatest._
import org.scalatest.matchers._
import sbt._
import sbt.Keys._
import java.io.File

class ScalagInputSpec extends FlatSpec with ShouldMatchers {

  behavior of "ScalagInput"

  it should "be available" in {
    val args: Seq[String] = Seq("a", "b")
    val srcDir: File = new File("src/main/scala")
    val testDir: File = new File("src/test/scala")
    val resourceDir: File = new File("src/main/resources")
    val testResourceDir: File = new File("src/test/resources")
    val settings = new SbtSettings(srcDir, testDir, resourceDir, testResourceDir)

    val input = new ScalagInput(args, settings)

    input.args.size should equal(2)
    input.settings.srcDir should equal(srcDir)
    input.settings.testDir should equal(testDir)
    input.settings.resourceDir should equal(resourceDir)
    input.settings.testResourceDir should equal(testResourceDir)
  }

}
