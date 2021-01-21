#!/usr/bin/env bash
set -e

./gradlew clean build -x test
java -Dspring.config.location=config/development/application.yml -jar ./build/libs/auth-service.jar
