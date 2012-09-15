import sbt._
import sbt.Keys._
import scalag._

object MyScalagDef extends Plugin {

  ScalagPlugin.addCommands(
    builtin.projectCommand,
    builtin.classCommand,
    builtin.objectCommand,
    builtin.specs2Command,
    builtin.ScalaTestCommand
  )

  ScalagPlugin.addCommand(
    namespace = "touch",
    args = Seq("name"),
    description = "Create the named file",
    operation = { case ScalagInput("touch" :: filename :: _, settings) => FilePath(filename).writeIfNotExists("") }
  )

}

