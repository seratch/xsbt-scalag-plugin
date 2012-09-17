# xsbt-scalag-plugin 

Scala code/resource Generator plugin for xsbt.

## Setup

### project/plugins.sbt

```scala
addSbtPlugin("com.github.seratch" % "xsbt-scalag-plugin" % "[0.2,)")
```

### project/MyScalagDef.scala

```scala
import sbt._
import sbt.Keys._
import scalag._

object MyScalagDef extends Plugin {

  ScalagPlugin.addCommands(builtin.all:_*)

}
```

### build.sbt

```scala
seq(scalagSettings: _*)
```

## How to use?

### g/generate command

Now g/generate command is available on xsbt.

```sh
$ sbt
> g
Usage: g [task-name] [args...] 

  project             Set up a new project
  class               Generates a new class file
  object              Generates a new object file
  specs2              Generates a new spec2 file for the specified class
  ScalaTest           Generates a new ScalaTest file for the specified class

>
```

When you execute "specs2" command,

```sh
sbt "g specs2 controllers.UserController"
```

the following file will be generated.

### src/test/scala/controllers/UserControllerSpec.scala

```scala
package controllers

import org.specs2.mutable._

class UserControllerSpec extends Specification {

  "UserController" should {
    "be available" in {
      todo
    }
  }

}
```

## How to extend?

### Create your own generator

Main usage of scalag is creating your own generators. Edit scalag.scala as follows.

```scala
object MyScalagDef extends Plugin {

  ScalagPlugin.addCommands(builtin.all:_*)

  ScalagPlugin.addCommand(
    namespace = "play-scaffold",
    args = Seq("class-name", "field-name:field-type ..."),
    description = "Generates a scaffold for Play Framework 2.x Scala",
    operation = { case ScalagInput(className :: fields, settings) =>
      // TODO Anyone?
    }
  )

}
```

See also:

https://github.com/seratch/xsbt-scalag-plugin/wiki/Scalag-Commands


## License

Apache License, Version 2.0

http://www.apache.org/licenses/LICENSE-2.0.html



