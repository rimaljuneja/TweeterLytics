# TweeterLytics-HGG02

## Pre-requisites
1) JDK 11 (Recommended) or JDK 8
2) SBT Tool ( Download it from :  https://www.scala-sbt.org/download.html)
3) Latest version of Eclipse EE supporting installed jdk. (Not required for running the project)
4) Twitter api credentials ( Apply from : https://developer.twitter.com)

## Steps to configure project in eclipse.
1) Clone the repository from git uri.
2) Open the checked out project in CMD.
3) Run the command : "sbt compile" (without quotes) in cmd
4) Run the command : "sbt eclipse" (without quotes) in cmd
5) Open the project in eclipse

## Steps to configure Twitter4J
1) Open the file named "twitter4j.properties" from the "conf" folder
2) Replace the **** with respective credentials and save the file

## Run the project in debug mode with eclipse
To debug, start your application with "sbt -jvm-debug 9999 run" and in Eclipse right-click on the project and select Debug As, Debug Configurations. In the Debug Configurations dialog, right-click on Remote Java Application and select New. Change Port to 9999 and click Apply. From now on you can click on Debug to connect to the running application. Stopping the debugging session will not stop the server.
