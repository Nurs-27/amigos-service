FROM openjdk:17-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} notification.jar

ENTRYPOINT ["java","-jar","/notification.jar"]