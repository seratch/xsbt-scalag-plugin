package scalag

/**
 * Scalag command help information
 *
 * @param namespace command namespace
 * @param args args
 * @param description command description
 */
case class ScalagHelp(namespace: String, args: Seq[String], description: String) {

  def usageMessage = "Usage: g " + namespace + " " + args.map(a => "[" + a + "]").mkString(" ")

  def showUsage(): Unit = println(usageMessage)

  override def toString(): String = "  " + namespace + " " * (20 - namespace.size) + description + "\n"

}
