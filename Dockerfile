FROM adoptopenjdk:11-jre-hotspot
MAINTAINER Reski Arianto <reski.arianto@student.uty.ac.id>

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]