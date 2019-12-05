#!/bin/bash -eu

docker_build() {
  docker build -t roman-number-converter .
}

execute() {
  local input=$1
  local result=$(docker run roman-number-converter app.jar "${input}")
  echo -e "${input}\t->\t${result}"
}

docker_build

execute 1
execute 7
execute 10
execute lizard
execute 0
execute 3001
