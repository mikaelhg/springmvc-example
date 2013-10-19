# Spring Boot / Spring MVC sample application

This skeleton contains:

Frontend:

* index.html: A Thymeleaf view template, for lean HTML generation
* app.js: AngularJS frontend application.

Backend:

* application.yml: The application configuration file
* ExampleController.java: Contains the entry point main method, and a simple, annotation-configured front end for all HTTP web requests.
* ExampleService.java: A business service skeleton, demonstrating Google Guava and Joda-Time.
* ExampleDao.java: A Spring Data JPA compatible DAO interface, CRUD with minimal boilerplate.
* Example.java: A skeleton JPA entity class with Lombok autogenerating the getters and setters required by the spec.
* pom.xml: Project build, dependency and packaging configuration file.

## This experimental branch of the sample application is not currently anywhere fit for production use.

The current snapshot versions of various libraries used in this project are not fit for production use,
as you can verify by starting the application, and running the Apache Benchmark utility against it:

    ab -c 5 -n 10000 http://localhost:8080/examples

## If you develop with IntelliJ IDEA...

I recommend IntelliJ IDEA.

You'll need to install the Lombok plugin from the IDEA plugin list.

To get the IDEA "Make module..." functionality to work with the Lombok plugin, in project settings,
enable Compiler -> Use External Build, as well as well as Compiler -> Annotation Processor -> Enable Annotation Processing.

## If you develop with Eclipse...

If you develop with Eclipse, you'll need to install Lombok's Eclipse support
as described at http://projectlombok.org/download.html

## If you develop with NetBeans...

NetBeans should support Lombok out of the box.

## Set up for JRebel

For easy save-and-reload development, get a free social license of ZeroTurnaround
JRebel, or a commercial one, if your project pays for it: http://zeroturnaround.com/jrebel/

## Run the example

### Development mode (H2 embedded database)

    mvn exec:java

## Broken: Production and Legacy modes

When Spring Boot matures out of alpha, I'll fix the Production and Legacy modes.
Currently, the embedded database autodetection is a bit screwy, or I haven't read enough code.
That's what sometimes happens when you start playing around with alpha projects :)

### Production mode (PostgreSQL)

    mvn -Dspring.profiles.active=postgresql \
      -Dspring.database.url=jdbc:postgresql://localhost/database \
      -Dspring.database.username=username \
      -Dspring.database.password=password \
      exec:java

### Legacy mode (MySQL)

    mvn -Dspring.profiles.active=mysql \
      -Dspring.database.url=jdbc:mysql://localhost/database \
      -Dspring.database.username=username \
      -Dspring.database.password=password \
      exec:java
