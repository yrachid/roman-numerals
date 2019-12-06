#!/bin/bash -eux

readonly SCRIPTS_LOCATION="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
readonly DEFAULT_TEST_VALUES=$(cat "${SCRIPTS_LOCATION}/test-values")
readonly TEST_VALUES=${@:-"${DEFAULT_TEST_VALUES[@]}"}

docker build -t number-converter .

docker run number-converter "app.jar" ${TEST_VALUES[@]}
