# Spring Boot / Spring MVC sample application

This skeleton contains:

* ExampleConfiguration.java: A Spring 3.2 programmatic configuration class, with development and production profiles
* ExampleController.java: A simple, annotation-configured front end for all HTTP web requests
* ExampleService.java: A business service skeleton, demonstrating Google Guava and Joda-Time
* ExampleDao.java: A Spring Data JPA compatible DAO interface, CRUD with minimal boilerplate
* Example.java: A skeleton JPA entity class with Lombok autogenerating the getters and setters required by the spec
* index.html: A Thymeleaf view template, for lean HTML generation

## If you develop with Eclipse...

If you develop with Eclipse, you'll need to install Lombok's Eclipse support
as described at http://projectlombok.org/download.html

## If you develop with IntelliJ IDEA...

You'll need to install the Lombok plugin from the IDEA plugin list.

To get the IDEA "Make module..." functionality to work with the Lombok plugin, in project settings,
enable Compiler -> Use External Build, as well as well as Compiler -> Annotation Processor -> Enable Annotation Processing.

## Set up for JRebel

For easy save-and-reload development, get a free social license of ZeroTurnaround
JRebel, or a commercial one, if your project pays for it: http://zeroturnaround.com/jrebel/

## Run the example

### Development mode (H2 embedded database)

    mvn exec:java

## Broken: Production and Legacy modes

When Spring Boot matures out of alpha, I'll fix the Production and Legacy modes. Currently, the autodetected

### Production mode (PostgreSQL)

    mvn -Dspring.profiles.active=postgresql \
      -Dspring.datasource.url=jdbc:postgresql://localhost/database \
      -Dspring.datasource.username=username \
      -Dspring.datasource.password=password \
      exec:java

### Legacy mode (MySQL)

    mvn -Dspring.profiles.active=mysql \
      -Dspring.datasource.url=jdbc:mysql://localhost/database \
      -Dspring.datasource.username=username \
      -Dspring.datasource.password=password \
      exec:java
