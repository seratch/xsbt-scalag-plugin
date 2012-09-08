package scalag

/**
 * Package name
 * @param packageName package name
 */
case class PackageName(packageName: String) {

  /**
   * Returns fs path
   * @return path
   */
  def path: String = packageName.split("\\.").mkString("/")

  /**
   * Returns the parent package
   * @return the parent package
   */
  def parent: String = packageName.split("\\.").init.mkString(".")

  /**
   * Returns the last element of package name
   * @return the last element
   */
  def name: String = packageName.split("\\.").last

}
