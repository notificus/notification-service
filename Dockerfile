FROM openjdk:10-jre-slim

VOLUME /tmp
ARG JAR_FILE=build/libs/notification-service-1.0.jar

# Add the application's jar to the container
ADD ${JAR_FILE} notification-service.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/notification-service.jar"]
