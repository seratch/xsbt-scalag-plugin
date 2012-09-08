package scalag

import org.scalatest._
import org.scalatest.matchers._
import org.apache.commons.io.FileUtils
import java.io.File

class FilePathSpec extends FlatSpec with ShouldMatchers {

  behavior of "FilePath"

  it should "be available" in {
    val path: String = "class.ftl"
    val file = new FilePath(path)
    file.path should equal(path)
  }

}
