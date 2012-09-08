<#if packageName != "">package ${packageName}</#if>

import org.scalatest._
import org.scalatest.matchers._

class ${name}Spec extends Spec with ShouldMatchers {

  describe("${name}") {
    it("should be available") {
      pending
    }
  }

}
