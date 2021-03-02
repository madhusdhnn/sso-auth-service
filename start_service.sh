#!/usr/bin/env bash
set -e

bash ./seed.sh
./gradlew clean build -x test
java -Dspring.config.location=config/development/application.yml -jar ./build/libs/sso-auth-service.jar
