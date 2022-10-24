# Origin Java Challenge

Java challenge for backend candidates

## Requirements

- [Java 17](https://docs.oracle.com/en/java/javase/17/docs/api/)

## Installation

For easy java version management, we recommend using SDKMAN, you can look at the installation guide [here](https://sdkman.io/install) (once installed you can download the java version used in this project)
```
# Install

$ sdk install java 17.0.4.1-tem

Installing: java 17.0.4.1-tem
Done installing!

# Select the version

$ sdk use java 17.0.4.1-tem

Using java version 17.0.4.1-tem in this shell.

# Use it!

$ java --version

openjdk 17.0.4.1 2022-08-12
OpenJDK Runtime Environment Temurin-17.0.4.1+1 (build 17.0.4.1+1)
OpenJDK 64-Bit Server VM Temurin-17.0.4.1+1 (build 17.0.4.1+1, mixed mode)
```
For easy env switch, you can use:
```
$ sdk env
```

## Running locally

- Building
```
$ ./gradlew clean build
```

- Running
```
$ ./gradlew bootRun
```

- Testing
```
$ ./gradlew test
```
