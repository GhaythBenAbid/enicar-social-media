FROM adoptopenjdk/openjdk17:jre-17.0.1_12-alpine

WORKDIR /app

COPY target/backend.jar /app/backend.jar

EXPOSE 9000

CMD ["java", "-jar", "backend.jar"]

