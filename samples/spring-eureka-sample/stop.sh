#!/bin/bash

# 查找正在运行的服务的进程 ID
PID=$(ps aux | grep java | grep eureka-server.jar | grep -v grep | awk '{print $2}')

if [ -z "$PID" ]; then
    echo "Service is not running."
else
    # 终止服务进程
    kill $PID
    echo "Service stopped."
fi
