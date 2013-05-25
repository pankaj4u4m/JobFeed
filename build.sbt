name := "Job feed"

version := "0.0.1"

organization := "net.liftweb"

crossScalaVersions := Seq("2.10.1", "2.9.2")

scalaVersion := "2.9.2"

resolvers ++= Seq("snapshots"     at "http://oss.sonatype.org/content/repositories/snapshots",
                "releases"        at "http://oss.sonatype.org/content/repositories/releases",
                "Java.net Maven2 Repository"     at "http://download.java.net/maven/2/"
                )

seq(com.github.siasia.WebPlugin.webSettings :_*)

unmanagedResourceDirectories in Test <+= (baseDirectory) { _ / "src/main/webapp" }

scalacOptions ++= Seq("-deprecation", "-unchecked")

libraryDependencies ++= {
  val liftVersion = "2.5-RC5"
  Seq(
    "net.liftweb"       %% "lift-webkit"        % liftVersion        % "compile",
    "net.liftweb"       %% "lift-mapper"        % liftVersion        % "compile",
    "net.liftweb"       %% "lift-testkit"       % liftVersion        % "compile->default",
    "net.liftweb"       %% "lift-wizard"        % liftVersion        % "compile->default",
    "net.liftmodules"   %% "fobo_2.5"           % "0.9.6-SNAPSHOT",
    "org.eclipse.jetty" % "jetty-webapp"        % "8.1.7.v20120910"  % "container,test",
    "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container,test" artifacts Artifact("javax.servlet", "jar", "jar"),
    "ch.qos.logback"    % "logback-classic"     % "1.0.6",
    "org.specs2"        %% "specs2"             % "1.12.1"           % "test"
  )
}

libraryDependencies ++= Seq(
  "javax.servlet"            % "servlet-api"    % "2.5"              % "provided->default",
  "com.h2database"    % "h2"                  % "1.3.171"
)


