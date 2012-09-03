import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "Playground"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      "LiquidAPIClient" % "LiquidAPIClient" % "0.0.1-SNAPSHOT"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA).settings(
    	resolvers += "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository"
    )

}
