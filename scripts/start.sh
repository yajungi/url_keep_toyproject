#!/usr/bin/env bash

ABS_PATH=$(readlink -f $0)
ABS_DIR=$(dirname $ABS_PATH)

source ${ABS_DIR}/profile.sh

REPOSITORY=/home/ec2-user/app/nginx_deploy
PROJECT_NAME=toy

echo "> Build file copy"

cp $REPOSITORY/zip/target/*.war $REPOSITORY/

echo "> new application deploy"

WAR_PATH_NAME=$(ls -tr $REPOSITORY/*.war | tail -n 1)
WAR_NAME=$(ls -tr $REPOSITORY | grep .war | tail -n 1)

echo "> WAR Name: $WAR_NAME"

echo "> $WAR_NAME 실행 권한 추가"

chmod +x $WAR_PATH_NAME

echo "> $WAR_NAME 실행"

IDLE_PROFILE=$(find_idle_profile)

echo "> $WAR_NAME 의 profile=$IDLE_PROFILE 로 실행합니다."

nohup java -jar -Dspring.config.location=classpath:/application.properties,classpath:/application-$(IDLE_PROFILE).properties,/home/ec2-user/app/application-oauth.properties,/home/ec2-user/app/application-real-db.properties -Dspring.profiles.active=$(IDLE_PROFILE) $WAR_PATH_NAME > $REPOSITORY/nohup.out 2>&1 &