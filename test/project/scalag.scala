import sbt._
import sbt.Keys._
import scalag._

object MyScalagDef extends Plugin {

  ScalagPlugin.addCommand(builtin.classCommand)
  ScalagPlugin.addCommand(builtin.objectCommand)
  ScalagPlugin.addCommand(builtin.specs2Command)
  ScalagPlugin.addCommand(builtin.ScalaTestCommand)

  ScalagPlugin.addCommand(
    namespace = "touch",
    description = "Create the named file",
    operation = { 
      case ScalagInput("touch" :: filename :: _, settings) => FilePath(filename).writeIfNotExists("")
      case ScalagInput("touch" :: Nil, settings) => println("Usage: g touch [filename]")
    }
  )

}

