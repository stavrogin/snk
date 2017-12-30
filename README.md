# snk

### Technical details
- This application is implemented in Java 8 storing data on a local H2 database

### Packaging
- Once installed Maven (tested on Maven 3.5.0), run the following command:

mvn clean compile assembly:single

This will package the source code and the project's dependencies in a single jar file
in the target folder

### Running
- Run the follwing command from the command line prompt:

java -jar snk-0.0.1-SNAPSHOT-jar-with-dependencies.jar
