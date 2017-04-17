enablePlugins(ScalaJSPlugin)

name := "sri-web-template"

scalaVersion := "2.12.1"

libraryDependencies ++= Seq(
  "scalajs-react-interface" %%% "core" % "2017.3.26-beta",
  "scalajs-react-interface" %%% "web" % "2017.3.26-beta",
  "scalajs-react-interface" %%% "vdom" % "2017.4.18-beta",
  "scalajs-react-interface" %%% "scalacss" % "2017.3.26-beta",
  "scalajs-react-interface" %%% "universal" % "2017.4.9-beta"
)

scalacOptions ++= Seq("-deprecation",
                      "-unchecked",
                      "-feature",
                      "-language:postfixOps",
                      "-language:implicitConversions",
                      "-language:higherKinds",
                      "-language:existentials")

scalaJSModuleKind := ModuleKind.CommonJSModule

scalaJSUseMainModuleInitializer := true

resolvers += Resolver.bintrayRepo("scalajs-react-interface", "maven")

/** Custom tasks to generate launcher file in  CommonJSModule mode   */
val SJS_OUTPUT_PATH = "assets/scalajs-output.js"

val fastOptWeb = Def.taskKey[Unit]("Generate web output file for fastOptJS")

artifactPath in Compile in fastOptJS :=
  baseDirectory.value / SJS_OUTPUT_PATH
fastOptWeb in Compile := {
  (fastOptJS in Compile).value
}

val fullOptWeb = Def.taskKey[Unit]("Generate web output file for fullOptJS")

artifactPath in Compile in fullOptJS :=
  baseDirectory.value / SJS_OUTPUT_PATH

fullOptWeb in Compile := {
  (fullOptJS in Compile).value
}
