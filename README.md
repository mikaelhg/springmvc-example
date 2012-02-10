Spring 3.1 MVC web profile application skeleton.

## Development mode
    mvn-jrebel jetty:run

## Production mode
    mvn-jrebel -Ddb.servername=localhost -Ddb.databasename=database \
      -Ddb.user=username -Ddb.password=password \
      -Dspring.profiles.active=production \
      jetty:run
