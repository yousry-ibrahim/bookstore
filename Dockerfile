FROM openjdk:8-jdk-alpine
ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    WAIT_TIME=0 \
    JAVA_OPTS=""
VOLUME /bookstore-service
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /bookstore-service/bookstore.jar
ENTRYPOINT ["java","-jar","/bookstore-service/bookstore.jar"]
EXPOSE 9090