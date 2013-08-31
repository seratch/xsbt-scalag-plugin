package scalag

/**
 * Scalag built-in commands
 */
object builtin {

  // -----------------
  // all commands
  // -----------------
  //
  // ScalagPlugin.addCommands(builtin.all:_*)

  lazy val all: Seq[ScalagCommand] = Seq(
    projectCommand,
    classCommand,
    objectCommand,
    specs2Command,
    ScalaTestCommand
  )

  // -----------------
  // project
  // -----------------

  private[this] def setupProject(settings: SbtSettings, org: String, name: String, scalaVersion: String): Unit = {
    val gitkeep = "/.gitkeep"
    FilePath(settings.srcDir + gitkeep).touch()
    FilePath(settings.resourceDir + gitkeep).touch()
    FilePath(settings.testDir + gitkeep).touch()
    FilePath(settings.testResourceDir + gitkeep).touch()
    FilePath("project/Build.scala").writeIfNotExists(
      ftl(path = "templates/builtin/Build.scala.ftl",
        values = Map("organization" -> org, "name" -> name, "scalaVersion" -> scalaVersion)))
  }

  val projectCommand: ScalagCommand = ScalagCommand(
    namespace = "project",
    args = Seq("organization", "name", "(scalaVersion)"),
    description = "Set up a new project",
    operation = {
      case ScalagInput(org :: name :: scalaVersion :: _, settings) => setupProject(settings, org, name, scalaVersion)
      case ScalagInput(org :: name :: _, settings) => setupProject(settings, org, name, "2.9.2")
    }
  )

  // -----------------
  // class
  // -----------------

  val classCommand: ScalagCommand = ScalagCommand(
    namespace = "class",
    args = Seq("FQCN"),
    description = "Generates a new class file",
    operation = {
      case ScalagInput(fqcn :: _, settings) =>
        val _fqcn = FQCN(fqcn)
        FilePath(settings.srcDir + "/" + _fqcn.filepath).writeIfNotExists(
          ftl(path = "templates/builtin/class.ftl",
            values = Map("packageName" -> _fqcn.packageName, "name" -> _fqcn.className))
        )
    }
  )

  // -----------------
  // object
  // -----------------

  val objectCommand: ScalagCommand = ScalagCommand(
    namespace = "object",
    args = Seq("FQCN"),
    description = "Generates a new object file",
    operation = {
      case ScalagInput(fqcn :: _, settings) =>
        val _fqcn = FQCN(fqcn)
        FilePath(settings.srcDir + "/" + _fqcn.filepath).writeIfNotExists(
          ftl(path = "templates/builtin/object.ftl",
            values = Map("packageName" -> _fqcn.packageName, "name" -> _fqcn.className))
        )
    }
  )

  // -----------------
  // specs2
  // -----------------

  private[this] def writeSpecs2IfNotExists(settings: SbtSettings, fqcn: FQCN, style: String): Unit = {
    try {
      FilePath(settings.testDir + "/" + fqcn.specFilepath).writeIfNotExists(
        ftl(path = "templates/builtin/specs2-" + style + ".ftl",
          values = Map("packageName" -> fqcn.packageName, "name" -> fqcn.className)
        ))
    } catch {
      case e: Exception => specs2Command.help.showUsage()
    }
  }

  val specs2Command: ScalagCommand = ScalagCommand(
    namespace = "specs2",
    args = Seq("FQCN", """("unit"/"acceptance")"""),
    description = "Generates a new spec2 file for the specified class",
    operation = {
      case ScalagInput(fqcn :: style :: _, settings) => writeSpecs2IfNotExists(settings, FQCN(fqcn), style)
      case ScalagInput(fqcn :: _, settings) => writeSpecs2IfNotExists(settings, FQCN(fqcn), "unit")
    }
  )

  // -----------------
  // ScalaTest
  // -----------------

  private[this] def writeScalaTestIfNotExists(settings: SbtSettings, fqcn: FQCN, style: String): Unit = {
    try {
      val content = ftl(path = "templates/builtin/ScalaTest-" + style + ".ftl",
        values = Map("packageName" -> fqcn.packageName, "name" -> fqcn.className)
      )
      style match {
        case "FunSuite" => FilePath(settings.testDir + "/" + fqcn.suiteFilepath).writeIfNotExists(content)
        case _ => FilePath(settings.testDir + "/" + fqcn.specFilepath).writeIfNotExists(content)
      }
    } catch {
      case e: Exception => ScalaTestCommand.help.showUsage()
    }
  }

  val ScalaTestCommand: ScalagCommand = ScalagCommand(
    namespace = "ScalaTest",
    args = Seq("FQCN", """("FunSuite"/"Spec"/"WordSpec"/"FlatSpec"/"FeatureSpec")"""),
    description = "Generates a new ScalaTest file for the specified class",
    operation = {
      case ScalagInput(fqcn :: style :: _, settings) => writeScalaTestIfNotExists(settings, FQCN(fqcn), style)
      case ScalagInput(fqcn :: _, settings) => writeScalaTestIfNotExists(settings, FQCN(fqcn), "FlatSpec")
    }
  )

}
