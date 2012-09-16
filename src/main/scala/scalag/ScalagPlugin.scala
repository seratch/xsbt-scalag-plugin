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
            if (!isFrozen) {
              freeze()
            }
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
  private[this] var operations: ScalagOperation = {
    case ScalagInput(Nil, _) => showHelp()
  }

  /**
   * Is already frozen?
   */
  private[this] var isFrozen = false

  /**
   * Scalag help
   */
  private[this] val helps = new StringBuilder() ++= "Usage: g [task-name] [options...] \n\n"

  /**
   * Add new commands to Scalag
   * @param commands commands
   */
  def addCommands(commands: ScalagCommand*): Unit = {
    commands.foreach(cmd => addCommand(cmd))
  }

  /**
   * Add new command to Scalag
   * @param command scalag command
   */
  def addCommand(command: ScalagCommand): Unit = {
    Option(command).foreach(command =>
      addCommand(
        operation = command.operation,
        namespace = command.help.namespace,
        args = command.help.args,
        description = command.help.description
      )
    )
  }

  /**
   * Add new command to Scalag
   * @param operation scalag command operation
   * @param namespace scalag help namespace
   * @param description scalag help description
   */
  def addCommand(operation: ScalagOperation, namespace: String = "", args: Seq[String] = Nil, description: String = ""): Unit = {
    this.synchronized {
      if (isFrozen) {
        throw new ScalagStateException("Scalag is already frozen. You cannnot add commands any more.")
      } else {
        val help = ScalagHelp(namespace, args, description)
        if (namespace != "") {
          helps ++= help.toString
        }
        operations = operations orElse operation orElse {
          case ScalagInput(n :: _, _) if n == namespace => help.showUsage()
        }
      }
    }
  }

  /**
   * Freezes Scalag
   */
  def freeze(): Unit = {
    this.synchronized {
      operations = operations orElse {
        case _ => showHelp()
      }
      isFrozen = true
    }
  }

  /**
   * Shows help
   */
  def showHelp(): Unit = println(helps.toString)

}

