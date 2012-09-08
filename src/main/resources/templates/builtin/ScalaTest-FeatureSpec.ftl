<#if packageName != "">package ${packageName}</#if>

import org.scalatest._
import org.scalatest.matchers._
import org.scalatest.GivenWhenThen

class ${name}Spec extends FeatureSpec with GivenWhenThen with ShouldMatchers {

  feature("A feature of ${name} works correctly") {

    info("Information about the feature")

    scenario("scenario") {

      given("given")

      when("when")

      then("then")

      and("and")

    }

  }

}
