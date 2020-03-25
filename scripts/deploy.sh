#!/bin/bash

REPOSITORY=/home/ec2-user/app/deploy
PROJECT_NAME=toy

echo "> Build file copy"

cp $REPOSITORY/zip/target/*.war $REPOSITORY/

echo "> Check current application pid"

CURRENT_PID=$(pgrep -f ${PROJECT_NAME}*.war)

echo "> Current application pid: $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
        echo "> 현재 구동중인 어플리케이션이 없으므로 종료하지 않습니다."
else
        echo "> kill -15 $CURRENT_PID"
        kill -15 $CURRENT_PID
        sleep 5
fi

echo "> new application deploy"

WAR_NAME=$(ls -tr $REPOSITORY/ | grep *.war | tail -n 1)

echo "> WAR Name: $WAR_NAME"

echo "> $WAR_NAME 실행 권한 추가"

chmod +x $WAR_NAME

echo "> $WAR_NAME 실행"

nohup java -jar -Dspring.config.location=classpath:/application.properties,classpath:/application-real.properties,/home/ec2-user/app/application-oauth.properties,/home/ec2-user/app/application-real-db.properties -Dspring.profiles.active=real $WAR_NAME > $REPOSITORY/nohup.out 2>&1 &