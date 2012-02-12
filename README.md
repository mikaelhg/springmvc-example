# Servlet 3.0 / Spring 3.1 MVC / JPA application skeleton

This skeleton contains:

* ExampleApplication.java: A Servlet 3.0 / Spring 3.1 application startup class
* ExampleConfiguration.java: A Spring 3.1 programmatic configuration class, with development and production profiles
* ExampleController.java: A simple, annotation-configured front end for all HTTP web requests
* ExampleService.java: A business service skeleton, demonstrating Google Guice and Joda-Time
* ExampleDao.java: A Spring Data JPA compatible DAO interface, CRUD with minimal boilerplate
* Example.java: A skeleton JPA entity class with Lombok autogenerating the getters and setters required by the spec
* index.scaml: A HAML/Scaml view template, for lean HTML generation

## If you develop with Eclipse...

If you develop with Eclipse, you'll need to install Lombok's Eclipse support
as described at http://projectlombok.org/download.html

## Set up for JRebel

For easy save-and-reload development, get a free social license of ZeroTurnaround
JRebel, or a commercial one, if your project pays for it: http://zeroturnaround.com/jrebel/

## Development mode
    mvn-jrebel jetty:run

## Production mode
    mvn-jrebel -Ddb.servername=localhost -Ddb.databasename=database \
      -Ddb.user=username -Ddb.password=password \
      -Dspring.profiles.active=production \
      jetty:run
