# Servlet 3.0 / Spring 3.2 MVC / JPA application skeleton

This skeleton contains:

* ExampleApplication.java: A Servlet 3.0 / Spring 3.2 application startup class
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

## Set up for JRebel

For easy save-and-reload development, get a free social license of ZeroTurnaround
JRebel, or a commercial one, if your project pays for it: http://zeroturnaround.com/jrebel/

## Development mode
    mvn-jrebel jetty:run

## Production mode
    mvn-jrebel -P postgresql -Dspring.profiles.active=postgresql \
      -Ddb.servername=localhost -Ddb.databasename=database \
      -Ddb.user=username -Ddb.password=password \
      jetty:run

## MySQL mode
    mvn-jrebel -P mysql -Dspring.profiles.active=mysql \
      -Ddb.url=jdbc:mysql://localhost/database  \
      -Ddb.username=username -Ddb.password=password \
      jetty:run
