#!/bin/bash -eu

readonly DEFAULT_TEST_VALUES=("1" "0" "7" "10" "3000" "lizard" "1979" "10000" "   ")
readonly JAR_PATH="${PWD}/app/build/libs/app-1.0.jar"

docker_build() {
  docker build -t roman-number-converter .
}

execute() {
  java \
    -jar "${JAR_PATH}" "${DEFAULT_TEST_VALUES[@]}"
}

./gradlew clean build

execute
