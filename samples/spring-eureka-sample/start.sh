#!/bin/bash

# 设置 Java 虚拟机参数
JAVA_OPTS="-Xms512m -Xmx1024m -XX:NewRatio=2 -XX:+UseG1GC -Xloggc:/path/to/gc.log -XX:+PrintGCDetails"

# 设置 Eureka 服务注册中心的 JAR 包路径
EUREKA_JAR=/path/to/eureka-server.jar

# 启动 Eureka 服务注册中心
nohup java $JAVA_OPTS -jar $EUREKA_JAR > eureka.log 2>&1 &
# 不打印日志
nohup java $JAVA_OPTS -jar $EUREKA_JAR > /dev/null 2>&1