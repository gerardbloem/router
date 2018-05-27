FROM ubuntu:16.04 as build-env

RUN apt-get update && apt-get install -y \
    openjdk-8-jdk \
    chromium-chromedriver

RUN mkdir /bld
COPY . /bld
WORKDIR /bld
RUN ./mvnw install
#ARG JAR_FILE
#ADD ${JAR_FILE} app.jar
FROM ubuntu:16.04
RUN apt-get update && apt-get install -y \
    openjdk-8-jre \
    chromium-chromedriver
COPY --from=build-env /bld/target/router-1.0-SNAPSHOT.jar ./app.jar
ENV ROUTERWACHTWOORD=defaultwachtwoord
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar","-DROUTERWACHTWOORD=${ROUTERWACHTWOORD}"]
