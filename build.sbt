name := "Digital Scholarly Editions library"


crossScalaVersions in ThisBuild := Seq("2.12.4") //Seq("2.11.8", "2.12.4")
scalaVersion := (crossScalaVersions in ThisBuild).value.last


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
      version := "6.0.2",
      licenses += ("GPL-3.0",url("https://opensource.org/licenses/gpl-3.0.html")),
      resolvers += Resolver.jcenterRepo,
      resolvers += "beta" at "http://beta.hpcc.uh.edu/nexus/content/repositories/releases",
      resolvers += Resolver.bintrayRepo("neelsmith", "maven"),
      libraryDependencies ++= Seq(
        "org.scala-js" %% "scalajs-stubs" % scalaJSVersion % "provided",
        "org.scalatest" %%% "scalatest" % "3.0.1" % "test",
        "org.wvlet.airframe" %%% "airframe-log" % "19.8.10",

        "edu.holycross.shot.cite" %%% "xcite" % "4.2.0",
        "edu.holycross.shot" %%% "ohco2" % "10.18.0",
        "edu.holycross.shot" %%% "citeobj" % "7.4.0",
        "edu.holycross.shot" %%% "scm" % "7.2.0"

        // FOR DEBUGGING:
        //"edu.holycross.shot" %%% "cex" % "6.3.3"

      )
    ).
    jvmSettings(
      tutTargetDirectory := file("docs"),
      tutSourceDirectory := file("shared/src/main/tut")
    ).
    jsSettings(
      skip in packageJSDependencies := false,
      scalaJSUseMainModuleInitializer in Compile := true

    )



lazy val crossedJVM = crossed.jvm.enablePlugins(TutPlugin)
lazy val crossedJS = crossed.js
