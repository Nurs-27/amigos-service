FROM openjdk:17-alpine

#ENV APP_HOME="/app"
#ENV JAVA_OPTS=""
#
#RUN mkdir -p $APP_HOME
#RUN mkdir -p $APP_HOME/config
#RUN mkdir -p $APP_HOME/log
#
#WORKDIR $APP_HOME
#
#VOLUME $APP_HOME/config
#VOLUME $APP_HOME/log

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} customer.jar

ENTRYPOINT ["java","-jar","/customer.jar"]