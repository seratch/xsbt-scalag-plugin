<#if packageName != "">package ${packageName}</#if>

import org.scalatest._
import org.scalatest.matchers._

class ${name}Spec extends FlatSpec with ShouldMatchers {

  behavior of "${name}"

  it should "be available" in {
    pending
  }

}
