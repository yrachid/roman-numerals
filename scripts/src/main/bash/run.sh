#!/bin/bash -eux

readonly SOURCE_LOCATION="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
readonly RESOURCES_LOCATION="${SOURCE_LOCATION}/../resources"

readonly EXECUTION_MODE=${1}
readonly CONTEXT_PATH=${2}
readonly INPUT_OPTION=${3}

readonly ARABIC_TO_ROMAN_INPUT_OPTION=$(cat "${RESOURCES_LOCATION}/inputs/arabic")
readonly ROMAN_TO_ARABIC_INPUT_OPTION=$(cat "${RESOURCES_LOCATION}/inputs/roman")
readonly MISC_INPUT_OPTION=$(cat "${RESOURCES_LOCATION}/inputs/misc")

case $INPUT_OPTION in
arabic-to-roman)
  TEST_VALUES=$ARABIC_TO_ROMAN_INPUT_OPTION
  ;;
roman-to-arabic)
  TEST_VALUES=$ROMAN_TO_ARABIC_INPUT_OPTION
  ;;
misc)
  TEST_VALUES=$MISC_INPUT_OPTION
  ;;
*)
  echo "Select a valid input: arabic-to-roman, roman-to-arabic, misc"
  ;;
esac

case $EXECUTION_MODE in
docker)
  docker build -t numeral-converter "${CONTEXT_PATH}"
  docker run number-converter "app.jar" ${TEST_VALUES}
  ;;
bash)
  java -jar "${CONTEXT_PATH}" ${TEST_VALUES}
  ;;
*)
  echo "Select a valid execution mode: docker, bash"
  ;;
esac

