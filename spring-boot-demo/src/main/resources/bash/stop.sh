#!/usr/bin/env bash
# ps -ef查询所有的 spring-boot-demo.jar 进程 并获取到进程id
# shellcheck disable=SC2006
pid=`ps -ef | grep spring-boot-demo.jar | grep -v grep | awk '{print $2} '`
if [[ -z "${pid}" ]]
then
    echo application is already stoppend
else
  echo kill ${pid}
  kill -9 ${pid}
fi