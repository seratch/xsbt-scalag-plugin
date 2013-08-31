resolvers += "sonatype releases" at "http://oss.sonatype.org/content/repositories/releases"

addSbtPlugin("com.typesafe.sbt" % "sbt-scalariform" % "1.1.0")
//addSbtPlugin("com.typesafe.sbt" % "sbt-scalariform" % "1.0.0")

addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.7.4")

libraryDependencies <+= (sbtVersion){ sv =>
  sv.split('.') match{
    case Array(_,a,b,_ @ _*) =>
      val i = a.toInt
      if((i <= 10) || (i <= 11) && (b.toInt <= 2))
        "org.scala-tools.sbt" %% "scripted-plugin" % sv
      else if(i == 11)
        "org.scala-sbt" %% "scripted-plugin" % sv
      else
        "org.scala-sbt" % "scripted-plugin" % sv
  }
}
