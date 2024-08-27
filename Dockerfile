FROM openjdk:19

ARG FILE_JAR=target/*.jar

ADD ${FILE_JAR} api-service-dev.jar

ENTRYPOINT ["java", "-jar", "api-service-dev.jar"]

EXPOSE 80
