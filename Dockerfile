FROM openjdk:8-jdk-alpine3.9
ARG JAR_FILE=build/libs/gpsMicroservice-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} gpsMicroservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "java", "-jar", "gpsMicroservice-0.0.1-SNAPSHOT.jar" ]