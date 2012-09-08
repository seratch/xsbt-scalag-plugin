package scalag

import org.scalatest._
import org.scalatest.matchers._

class FQCNSpec extends FlatSpec with ShouldMatchers {

  behavior of "FQCN"

  it should "be available" in {
    val fqcn = new FQCN("com.example.Foo")
    fqcn.packageName should equal("com.example")
    fqcn.className should equal("Foo")
    fqcn.filepath should equal("com/example/Foo.scala")
  }

  it should "be available without package" in {
    val fqcn = new FQCN("Foo")
    fqcn.packageName should equal("")
    fqcn.className should equal("Foo")
    fqcn.filepath should equal("Foo.scala")
  }

}
