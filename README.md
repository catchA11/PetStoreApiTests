# PetStore API Tests
Example PetStore API test using Cucumber JVM suite by Lucy Sheehy

## Introduction
This project runs a functional tests on the Swagger PetStore API:
https://petstore.swagger.io

The tests in the src/test/resources/features folder are described in BDD format as a set of scenarios of the format:
Given some precondition 
When a specific event occurs
Then this is the expected result

The glue code, or steps that tie the plain English BDD format scenarios to the Java code that drives the tests are 
located in the src/test/java/steps folder. 

The project uses REST Assured to interact with the API and AssertJ for test assertions.

In the event that the PetStore API is unavailable, the test will import dummy results from
resources/__files/pets.json

## Building and running the tests

This is a Maven project. Tests can be built with Maven and run in your IDE or
from the directory containing the pom file run the following command

```
mvn clean test
```

Tests can also be executed by running the CucumberRunner class

## Dependencies

* Java 8 or later
* Maven

This test suite has been verified  to run in on the following operating systems:
* Mac OS X (verified on Catalina)

## Test Report

A Cucumber test report is generated when the test suite is run from the command line or from the RunCucumberTest class. 
The report is in the /target/cucumber-report folder and can be viewed in a browser by opening the index.html file.

## Logging

Log4j logging is configured in the log4j.properties to output at INFO level to the console.

