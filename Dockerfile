FROM ubuntu:16.04

RUN apt-get update && apt-get install -y \
    openjdk-8-jdk \
    chromium-chromedriver

VOLUME /tmp
ARG JAR_FILE
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]