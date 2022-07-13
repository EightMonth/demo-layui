FROM openjdk:8-jre-slim
MAINTAINER github.com/eightmonth

ENV PARAMS=""

ADD target/demo-layui-0.0.1-SNAPSHOT.jar /app.jar

ENTRYPOINT ["sh","-c","java -jar $JAVA_OPTS /app.jar $PARAMS"]