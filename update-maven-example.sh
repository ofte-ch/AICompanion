#!/bin/bash

JAVA_HOME="/path/to/your/jdk/" # JDK >= 25 is recommended

cd ./RPCServer/Commons

./mvnw install

cd ../

./mvnw install

