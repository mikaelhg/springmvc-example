# Spring Boot sample application

This skeleton contains:

Frontend:

* index.html: A Thymeleaf view template, for lean HTML generation
* app.js: AngularJS frontend application.

Backend:

* application.yml: The application configuration file
* ExampleController.java: Contains the entry point main method, and a simple, annotation-configured front end for all HTTP web requests.
* ExampleDao.java: A Spring Data JPA compatible DAO interface, CRUD with minimal boilerplate.
* Example.java: A skeleton JPA entity class with Lombok autogenerating the getters and setters required by the spec.
* pom.xml: Project build, dependency and packaging configuration file.

## If you develop with IntelliJ IDEA...

I recommend IntelliJ IDEA 13+.

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

    mvn spring-boot:run

### Production mode (PostgreSQL)

    mvn -Dspring.profiles.active=postgresql \
      -Dspring.datasource.url=jdbc:postgresql://localhost/database \
      -Dspring.datasource.username=username \
      -Dspring.datasource.password=password \
      spring-boot:run

    mvn clean package

    java -jar target/springmvc-example-*.war \
      --spring.profiles.active=postgresql \
      --spring.datasource.url=jdbc:postgresql://localhost/database \
      --spring.datasource.username=username \
      --spring.datasource.password=password

### Legacy mode (MySQL)

    mvn -Dspring.profiles.active=mysql \
      -Dspring.datasource.url=jdbc:mysql://localhost/database \
      -Dspring.datasource.username=username \
      -Dspring.datasource.password=password \
      spring-boot:run

    mvn clean package

    java -jar target/springmvc-example-*.war \
      --spring.profiles.active=mysql \
      --spring.datasource.url=jdbc:mysql://localhost/database \
      --spring.datasource.username=username \
      --spring.datasource.password=password
