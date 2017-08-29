
inThisBuild(Seq(organization := "com.iheart.example"))

lazy val root = project.in(file("."))
  .enablePlugins(PlayScala, SwaggerPlugin)
  .settings(
    name := "meetup-demo",
    scalaVersion := "2.11.11",
    publish := {},
    publishLocal := {},
    swaggerDomainNameSpaces := Seq("models"),
    libraryDependencies ++= Seq(
      "org.webjars" % "swagger-ui" % "2.2.0",
      "com.iheart" %% "happy-path" % "0.7.4",
      "com.iheart" %% "ficus" % "1.2.6",
      "com.kailuowang" %% "henkan-convert" % "0.2.6",
      "com.kailuowang" %% "henkan-optional" % "0.2.6"
    ),
    scalacOptions ++= Seq(
      "-encoding",
      "UTF-8",
      "-deprecation",
      "-feature",
      "-unchecked",
      "-Xlint",
      "-Ywarn-adapted-args",
      "-Ywarn-inaccessible",
      "-Ywarn-dead-code",
      "-Ywarn-numeric-widen",
      "-Ypartial-unification"
    )
  )
