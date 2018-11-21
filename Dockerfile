FROM openjdk:10-jre-slim

VOLUME /tmp
ARG JAR_FILE=build/libs/notification-service-1.0.jar
ARG JSON_TEST_FILE=test.json

# Add the application's jar to the container
ADD ${JAR_FILE} /opt/notification-service/lib/notification-service.jar
ADD ${JSON_TEST_FILE} /opt/notification-service/lib/

# Run the entrypoint bash file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/opt/notification-service/lib/notification-service.jar"]
