FROM openjdk:17-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} apigw.jar

ENTRYPOINT ["java","-jar","/apigw.jar"]