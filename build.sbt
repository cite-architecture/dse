name := "Digital Scholarly Editions library"


crossScalaVersions := Seq("2.11.8", "2.12.3")
scalaVersion := "2.12.3"

lazy val root = project.in(file(".")).
    aggregate(crossedJVM, crossedJS).
    settings(
      publish := {},
      publishLocal := {}

    )

lazy val crossed = crossProject.in(file(".")).
    settings(
      name := "dse",
      organization := "edu.holycross.shot",
      version := "2.0.0",
      licenses += ("GPL-3.0",url("https://opensource.org/licenses/gpl-3.0.html")),
      resolvers += Resolver.jcenterRepo,
      resolvers += "beta" at "http://beta.hpcc.uh.edu/nexus/content/repositories/releases",
      resolvers += Resolver.bintrayRepo("neelsmith", "maven"),
      libraryDependencies ++= Seq(
        "org.scala-js" %% "scalajs-stubs" % scalaJSVersion % "provided",
        "org.scalatest" %%% "scalatest" % "3.0.1" % "test",

        "edu.holycross.shot.cite" %%% "xcite" % "2.7.1",

        "edu.holycross.shot" %%% "ohco2" % "10.1.2",
        "edu.holycross.shot" %%% "citeobj" % "4.3.3",
        "edu.holycross.shot" %%% "citerelations" % "2.0.1",

        "edu.holycross.shot" %%% "scm" % "5.1.3"

      )
    ).
    jvmSettings(
    ).
    jsSettings(
      skip in packageJSDependencies := false,
      scalaJSUseMainModuleInitializer in Compile := true

    )

lazy val crossedJVM = crossed.jvm
lazy val crossedJS = crossed.js.enablePlugins(ScalaJSPlugin)
