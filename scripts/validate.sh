#!/usr/bin/env bash

ABS_PATH=$(readlink -f $0)
ABS_DIR=$(dirname $ABS_PATH)

source ${ABS_DIR}/profile.sh
source ${ABS_DIR}/switch.sh

IDLE_PORT=$(find_idle_port)

echo "> Validate Check Start"
echo "> IDLE_PORT: $IDLE_PORT"
echo "> curl -s http://localhost:$IDLE_PORT/profile"

sleep 10

for RETRY_COUNT in {1..10}
do
  RESPONSE=$(curl -s http://localhost:${IDLE_PORT}/profile)
  COUNT=$(echo ${RESPONSE} | grep 'real' | wc -l)

  if [ ${COUNT} -ge 1 ]
  then
    echo "> Validate Check Success"
    switch_proxy
    break
  else
    echo "> 응답을 알 수 없거나 실행 상태가 아닙니다."
    echo "> Validate Check: ${RESPONSE}"
  fi

  if [ ${COUNT} -eq 10]
  then
    echo "> Validate Check 실패"
    echo "> Nginx에 연결하지 않고 배포를 종료합니다."
    exit 1
  fi

  echo "> Validate Check 연결 실패. 재시도 합니다."
  sleep 10

done