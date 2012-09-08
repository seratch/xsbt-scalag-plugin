<#if packageName != "">package ${packageName}</#if>

import org.scalatest._
import org.scalatest.matchers._

class ${name}Spec extends WordSpec with ShouldMatchers {

  "${name}" should {
    "be available" in {
      pending
    }
  }

}
