enablePlugins(ScalaJSPlugin)

name := "web-basic"

scalaVersion := "2.11.11"

libraryDependencies ++= Seq(
  "scalajs-react-interface" %%% "core" % "2017.7.9-RC",
  "scalajs-css" %%% "core" % "2017.7.9-RC",
  "scalajs-css" %%% "autoprefixer" % "2017.7.9-RC",
  "scalajs-react-interface" %%% "web" % "2017.7.9-RC",
  "scalajs-react-interface" %%% "web-router" % "2017.7.9-RC",
  "scalajs-react-interface" %%% "vdom" % "2017.7.9-RC",
  "scalajs-react-interface" %%% "universal" % "2017.7.9-RC"
)

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked",
  "-feature",
  "-language:postfixOps",
  "-language:implicitConversions",
  "-language:higherKinds",
  "-language:existentials",
  "-Xmacro-settings:classNameShrink=components"
)

scalaJSUseMainModuleInitializer := true

scalaJSUseMainModuleInitializer in Test := true

scalaJSLinkerConfig ~= (_.withModuleKind(ModuleKind.CommonJSModule))

resolvers += Resolver.bintrayRepo("scalajs-react-interface", "maven")
resolvers += Resolver.bintrayRepo("scalajs-css", "maven")
resolvers += Resolver.bintrayRepo("scalajs-jest", "maven")

/** Custom tasks to generate launcher file in  CommonJSModule mode   */
val SJS_OUTPUT_PATH = "assets/js/scalajs-output.js"

artifactPath in Compile in fastOptJS :=
  baseDirectory.value / SJS_OUTPUT_PATH

artifactPath in Compile in fullOptJS :=
  baseDirectory.value / SJS_OUTPUT_PATH

val dev = Def.taskKey[Unit]("Generate web output file via fastOptJS")

val prod = Def.taskKey[Unit]("Generate web output file via fullOptJS")

lazy val web = config("web")

dev in web := {
  (fastOptJS in Compile).value.data

}

prod in web := {
  (fullOptJS in Compile).value.data
}
