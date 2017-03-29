name := "HomeIntelligence"

version := "1.0"

lazy val `homeintelligence` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq( jdbc , cache , ws   , specs2 % Test )

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

//resolvers += "xuggle repo" at "https://files.liferay.com/mirrors/xuggle.googlecode.com/svn/trunk/repo/share/java/"

libraryDependencies ++= Seq(
  "com.github.sarxos" % "webcam-capture" % "0.3.11"
//  ,"xuggle" % "xuggle-xuggler" % "5.4"
  ,"org.jcodec" % "jcodec" % "0.1.9"
)