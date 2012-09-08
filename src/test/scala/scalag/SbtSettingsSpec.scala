package scalag

import org.scalatest._
import org.scalatest.matchers._
import sbt._
import sbt.Keys._
import java.io.File

class SbtSettingsSpec extends FlatSpec with ShouldMatchers {

  behavior of "SbtSettings"

  it should "be available" in {
    val srcDir: File = new File("src/main/scala")
    val testDir: File = new File("src/test/scala")
    val resourceDir: File = new File("src/main/resources")
    val testResourceDir: File = new File("src/test/resources")
    val settings = new SbtSettings(srcDir, testDir, resourceDir, testResourceDir)

    settings.srcDir should equal(srcDir)
    settings.testDir should equal(testDir)
    settings.resourceDir should equal(resourceDir)
    settings.testResourceDir should equal(testResourceDir)
  }

}
