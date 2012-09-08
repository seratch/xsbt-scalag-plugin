package scalag

import java.io.File

/**
 * Scalag command
 * @param help command help
 * @param operation command operation
 */
case class ScalagCommand(help: ScalagHelp, operation: ScalagOperation)

object ScalagCommand {
  def apply(namespace: String, description: String, operation: ScalagOperation): ScalagCommand = {
    ScalagCommand.apply(
      help = ScalagHelp(namespace, description),
      operation = operation
    )
  }
}

