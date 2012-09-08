class ${name} {
<#list fields as f>
  val ${f.valueName}: ${f.typeName} = _
</#list>
}
