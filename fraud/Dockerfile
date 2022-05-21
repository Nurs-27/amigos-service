FROM openjdk:17-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} fraud.jar

ENTRYPOINT ["java","-jar","/fraud.jar"]