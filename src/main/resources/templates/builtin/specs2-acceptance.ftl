<#if packageName != "">package ${packageName}</#if>

import org.specs2._

class ${name}Spec extends Specification { def is =

  "This is a specification to check 'Fooo'"                    ^
                                                               p^
    "'Fooo' should"                                            ^
      "be available"                                           ! available^
      "do something"                                           ! something^
                                                               end
    def available = todo
    def something = todo

}