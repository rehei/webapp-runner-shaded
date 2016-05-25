name := "webapp-runner-shaded"

// set version to environment variable $tag 
version := sys.props.getOrElse("tag", default = "0.0.0")

organization := "com.github.rehei"

scalaVersion := "2.11.7"

resolvers += "snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
resolvers += "staging" at "https://oss.sonatype.org/content/repositories/staging"
resolvers += "releases" at "https://oss.sonatype.org/content/repositories/releases"
resolvers += Resolver.bintrayRepo("rehei", "maven")

scalacOptions ++= Seq("-deprecation", "-unchecked", "-feature")
scalacOptions += "-target:jvm-1.7"

javacOptions ++= Seq("-encoding", "UTF-8")
javacOptions ++= Seq("-source", "1.7", "-target", "1.7")

crossPaths := false
licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html"))

libraryDependencies ++= {
  val liftVersion = "2.6.2"
  Seq(
    "com.github.jsimone" % "webapp-runner" % "7.0.57.2"
  )
}

assemblyShadeRules in assembly := Seq(
  ShadeRule.rename(
	"org.apache.commons.**" -> "com.github.rehei.org.apache.commons.@1"
  ).inAll
)

assemblyJarName in assembly := name.value + "-" + version.value + ".jar"
assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)
