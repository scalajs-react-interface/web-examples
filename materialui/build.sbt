enablePlugins(SriPlatformPlugin)

name := "mui-example"

scalaVersion := "2.12.4"

resolvers += Resolver.bintrayRepo("scalajs-css", "maven")
resolvers += Resolver.bintrayRepo("scalajs-react-interface", "maven")

libraryDependencies ++= Seq(
  "scalajs-react-interface" %%% "core" % "2018.2.2-RC",
  "scalajs-react-interface" %%% "web" % "2018.2.2-RC",
  "scalajs-react-interface" %%% "material-ui" % "2018.3.22-RC",
  "scalajs-css" %%% "core" % "2018.2.2",
  "scalajs-css" %%% "autoprefixer" % "2018.3.22",
  "scalajs-react-interface" %%% "web-router" % "2018.2.2-RC",
  "scalajs-react-interface" %%% "vdom" % "2018.2.2-RC",
  "scalajs-react-interface" %%% "relay" % "2018.2.2-RC",
  "scalajs-react-interface" %%% "universal" % "2018.2.9-RC"
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
