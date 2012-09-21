externalResolvers ~= (_.filter(_.name != "Scala-Tools Maven2 Repository"))

resolvers ++= Seq(
  "sonatype releases" at "http://oss.sonatype.org/content/repositories/releases",
  "sbt-idea repository" at "http://mpeltonen.github.com/maven/"
)

addSbtPlugin("com.github.seratch" % "testgenerator" % "1.1.0")

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.1.0")

addSbtPlugin("com.typesafe.sbtscalariform" % "sbtscalariform" % "0.5.1")

resolvers += Resolver.url("sbt-plugin-releases for Travis CI", new URL("http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases/"))(Resolver.ivyStylePatterns)

addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.6.0")

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
