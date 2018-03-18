# Internet Drum Pattern Queue Service

A web service for ingesting and serving [internet drum patterns][1].

This web API is an implementation of the [Internet Drum Pattern Queue Service Specification][2].

## Build status

[![Build Status](https://travis-ci.org/internetofdrums/internet-drum-pattern-queue-service.svg?branch=master)](https://travis-ci.org/internetofdrums/internet-drum-pattern-queue-service)
[![codecov](https://codecov.io/gh/internetofdrums/internet-drum-pattern-queue-service/branch/master/graph/badge.svg)](https://codecov.io/gh/internetofdrums/internet-drum-pattern-queue-service)
[![Docker Pulls](https://img.shields.io/docker/pulls/internetofdrums/internet-drum-pattern-queue-service.svg)](https://hub.docker.com/r/internetofdrums/internet-drum-pattern-queue-service/)

## Documentation website

You can [view the API documentation online][3].

## How to build

You can build the service locally.

### Requirements

To build the service locally, you will need:

- A [Java Development Kit (JDK)][4], version 1.9 or newer.
- [Apache Maven][5] itself, version 3.5.0 or newer.

Maven needs to use a Java version of 9 or higher. You can check which one it 
uses by running `mvn -v`.

### Building

Start the build by running the following command at the root of the project:

```bash
mvn package
```

## How to run

You can run the web service from the source directory of the 
`com.internetofdrums.api.web` module and then using the [Exec Maven Plugin][6]:

```bash
cd com.internetofdrums.api.web
mvn exec:exec
```

The web service should then be running at 
[https://localhost:8080/](https://localhost:8080/), which should return a 404.

You can then use the service [as defined in the specification][3].

[1]: https://github.com/internetofdrums/internet-drum-pattern-spec
[2]: https://github.com/internetofdrums/internet-drum-pattern-queue-service-spec
[3]: https://internetofdrums.github.io/internet-drum-pattern-queue-service-spec/
[4]: http://www.oracle.com/technetwork/java/javase/downloads/index.html
[5]: https://maven.apache.org/
[6]: http://www.mojohaus.org/exec-maven-plugin/
