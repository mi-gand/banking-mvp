#!/bin/bash

#mvn clean package -DskipTests
#mvn clean package -Dmaven.test.skip=true

mvn clean verify -pl !integration-tests -am