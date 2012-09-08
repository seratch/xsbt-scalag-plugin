package scalag

/**
 * Scalag command help information
 *
 * @param namespace command namespace
 * @param description command description
 */
case class ScalagHelp(namespace: String = "", description: String = "") {
  override def toString(): String = "  " + namespace + " " * (20 - namespace.size) + description + "\n"
}
