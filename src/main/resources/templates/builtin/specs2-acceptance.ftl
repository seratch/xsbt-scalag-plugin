<#if packageName != "">package ${packageName}</#if>

import org.specs2._

class ${name}Spec extends Specification { def is =

  "This is a specification to check 'xxx'"                     ^
                                                               p^
    "'xxx' should"                                             ^
      "be available"                                           ! available^
      "do something"                                           ! something^
                                                               end
    def available = todo
    def something = todo

}
