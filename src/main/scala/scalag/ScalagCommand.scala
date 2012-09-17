package scalag

/**
 * Scalag command
 * @param help command help
 * @param operation command operation
 * @param namespace namespace
 */
case class ScalagCommand(namespace: String, help: ScalagHelp, operation: ScalagOperation)

object ScalagCommand {
  def apply(namespace: String, args: Seq[String], description: String, operation: ScalagOperation): ScalagCommand = {
    ScalagCommand.apply(
      namespace = namespace,
      help = ScalagHelp(namespace, args, description),
      operation = operation
    )
  }
}

