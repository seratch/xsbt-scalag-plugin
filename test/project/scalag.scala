import sbt._
import sbt.Keys._
import scalag._

object MyScalagDef extends Plugin {

  ScalagPlugin.addCommands(builtin.all:_*)

  ScalagPlugin.addCommand(
    namespace = "touch",
    args = Seq("name"),
    description = "Create the named file",
    operation = { case ScalagInput(filename :: _, settings) => FilePath(filename).writeIfNotExists("") }
  )

}

