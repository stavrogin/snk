# snk

### Technical details
- This application is implemented with Java 8 storing data on a local H2 database

### Packaging
- Install Apache Maven (tested with Maven 3.5.2) and Java JDK 1.8 (tested with update 151)
- Add maven installation directory (bin folder) to Path variable
- Set JAVA_HOME pointing to the JDK installation directory
- Run the following command from prompt:

mvn clean compile assembly:single

- This will compile and then package the source code and the project's dependencies in a single jar file in the target folder

### Running
- Move to the target folder and then run the follwing command from the command line prompt:

java -jar snk-1.0.0-SNAPSHOT-jar-with-dependencies.jar
