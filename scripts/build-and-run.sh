#!/bin/bash -eux

readonly SCRIPTS_LOCATION="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
readonly DEFAULT_TEST_VALUES=$(cat "${SCRIPTS_LOCATION}/test-values")
readonly TEST_VALUES=${@:-"${DEFAULT_TEST_VALUES[@]}"}

./gradlew clean build

java -jar ${PWD}/app/build/libs/*.jar ${TEST_VALUES}
