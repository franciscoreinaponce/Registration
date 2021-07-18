# Technical Test using Spring Boot 2.5 and JUnit 5 compiling with Java 11

## How to install / run the app

### Install the necessary applications

Download JDK 11: https://www.oracle.com/java/technologies/javase-jdk11-downloads.html  
Configure JDK: https://docs.oracle.com/cd/E19182-01/820-7851/inst_cli_jdk_javahome_t/index.html  
Download Gradle: https://gradle.org/releases/  
Configure Gradle: https://gradle.org/install/

### Run the Registration app

Clone the *Registration.zip* repository which contains de project.  

Once you have configured the JDK and Maven, run the following commands to:

*gradle build* => Downloads all the dependencies found in build.gradle, compiles the project and also ***runs*** the tests.  
*gradle build -x test* => Same as the previous command ***without running*** the tests  
*gradle test* => Run only the tests  
*gradle bootRun* => Run Spring Boot app  

## How to test the app

### Unit and Integration tests

The project has unit and integration tests which can be executed by means of the above mentioned command *gradle test*.  

Currently, the project has a coverage of 80% with unit tests which increases to +95% with integration tests.

### Jacoco

The software metric Jacoco has also been implemented, allowing drill-down to method level.  

To generate this report, run the command *gradle jacocoTestReport* 
(*gradle test* will also internally execute the previously mentioned command).  

Link to access the report once it has been generated: 
http://localhost:63342/registration/build/reports/jacoco/test/html/index.html

### Postman

In order to test the api, I have provided a Postman collection (*Registration.postman_collection.json*),
which contains the existing endpoints and examples of calls for the different scenarios.

Download Postman: https://www.postman.com/downloads/

### Swagger

The project is also delivered with the Swagger tool.

Once the application is running, you can access it from http://localhost:8080/swagger-ui/#/

## Assumption

1. It is assumed that no field can be null, including the social security number.
   

2. Since the provided interface considers the date of birth as a *String* type, the same type has been kept in the provided model.
Otherwise, I would have opted to use the *LocalDate* type.


### Stack

- Java 11
- Spring Boot 2.5
- Swagger 3.0
- Lombok 1.18  
- JUnit 5.7
- Mockito 3.11
- Jacoco 0.8
- Gradle 7.1
