#!/bin/bash

# 停止服务
./stop.sh

# 等待一段时间确保服务已经停止
sleep 5

# 启动服务
./start.sh
echo "Service restarted."
