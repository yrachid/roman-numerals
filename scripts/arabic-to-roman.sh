#!/bin/bash -eux

readonly SCRIPTS_LOCATION="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
readonly TEST_VALUES=$(seq -s ' ' 1 3000)

./gradlew clean build

java -jar ${PWD}/app/build/libs/*.jar ${TEST_VALUES}
