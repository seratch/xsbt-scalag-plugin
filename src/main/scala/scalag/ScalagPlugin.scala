package scalag

import sbt._
import sbt.Keys._

/**
 * Scalag xsbt plugin
 *
 * {{{
 * import sbt._
 * import sbt.Keys._
 * import scalag._
 *
 * object MyScalagDef extends Plugin {
 *   ScalagPlugin.addCommand(builtin.classCommand)
 * }
 * }}}
 */
object ScalagPlugin extends Plugin {

  val scalagTask = inputTask {
    (task: TaskKey[Seq[String]]) =>
      (task, scalaSource in Compile, scalaSource in Test,
        resourceDirectory in Compile, resourceDirectory in Test) map {
          case (args, srcDir, testDir, resourceDir, testResourceDir) =>
            val settings = SbtSettings(
              srcDir = srcDir,
              testDir = testDir,
              resourceDir = resourceDir,
              testResourceDir = testResourceDir
            )
            operations.apply(ScalagInput(args, settings))
        }
  }

  lazy val g = InputKey[Unit]("g")
  lazy val generate = InputKey[Unit]("generate")

  val scalagSettings = inConfig(Compile)(Seq(
    g <<= scalagTask,
    generate <<= scalagTask
  ))

  /**
   * Scalag command operations
   */
  private[this] var operations: PartialFunction[ScalagInput, Unit] = {
    case ScalagInput(Nil, _) => showHelp()
  }

  /**
   * Scalag help
   */
  private[this] val help = new StringBuilder() ++= "Usage: g [task-name] [options...] \n\n"

  /**
   * Add new command to Scalag
   * @param command scalag command
   */
  def addCommand(command: ScalagCommand): Unit = addCommand(
    operation = command.operation,
    namespace = command.help.namespace,
    description = command.help.description
  )

  /**
   * Add new command to Scalag
   * @param operation scalag command operation
   * @param namespace scalag help namespace
   * @param description scalag help description
   */
  def addCommand(operation: ScalagOperation, namespace: String = "", description: String = ""): Unit = {
    this.synchronized {
      if (namespace != "") {
        help ++= ScalagHelp(namespace, description).toString
      }
      operations = operations orElse operation
    }
  }

  /**
   * Shows help
   */
  def showHelp(): Unit = println(help.toString)

}

