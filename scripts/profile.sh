#!/usr/bin/env bash

function find_idle_profile() {
  RESPONSE_STATUS=$(curl -s -o /dev/null -w "%{http_code}" http://localhost/profile)

  if [ ${RESPONSE_STATUS} -ge 400 ]
  then
    CURRENT_PROFILE=real8082
  else
    CURRENT_PROFILE=$(curl -s http://localhost/profile)
  fi

  if [ ${CURRENT_PROFILE} == real8081 ]
  then
    IDLE_PROFILE=real8081
  else
    IDLE_PROFILE=real8082
  fi

  echo "${IDLE_PROFILE}"
}

function find_idle_port() {
  IDLE_PROFILE=$(find_idle_profile)

  if [ ${IDLE_PROFILE} == real8081 ]
  then
    echo "8081"
  else
    echo "8082"
  fi
}