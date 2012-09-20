import testgenerator.SbtKeys._

seq(scalariformSettings: _*)

seq(testgeneratorSettings: _*)

net.virtualvoid.sbt.graph.Plugin.graphSettings

ScriptedPlugin.scriptedSettings

ScriptedPlugin.scriptedBufferLog := false
