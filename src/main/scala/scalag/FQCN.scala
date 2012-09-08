package scalag

/**
 * FQCN (Fully-Qualified Class Name)
 * @param fqcn FQCN
 */
case class FQCN(fqcn: String) {

  private[this] val elements = fqcn.split("\\.")

  /**
   * Returns package name
   * @return package name
   */
  def packageName: String = elements.init.mkString(".")

  /**
   * Returns class name
   * @return class name
   */
  def className: String = elements.last

  /**
   * Returns the file path from source directory
   * @return file path
   */
  def filepath: String = {
    val packagePart = packageName.replaceAll("\\.", "/")
    if (packagePart != "") packagePart + "/" + className + ".scala"
    else className + ".scala"
  }

  /**
   * Returns the spec file path from test source directory
   * @return file path
   */
  def specFilepath: String = {
    val packagePart = packageName.replaceAll("\\.", "/")
    if (packagePart != "") packagePart + "/" + className + "Spec.scala"
    else className + "Spec.scala"
  }

  def suiteFilepath: String = {
    val packagePart = packageName.replaceAll("\\.", "/")
    if (packagePart != "") packagePart + "/" + className + "Suite.scala"
    else className + "Suite.scala"
  }

}
