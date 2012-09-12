package scalag

/**
 * Scalag command
 * @param help command help
 * @param operation command operation
 */
case class ScalagCommand(help: ScalagHelp, operation: ScalagOperation)

object ScalagCommand {
  def apply(namespace: String, args: Seq[String], description: String, operation: ScalagOperation): ScalagCommand = {
    ScalagCommand.apply(
      help = ScalagHelp(namespace, args, description),
      operation = operation
    )
  }
}

