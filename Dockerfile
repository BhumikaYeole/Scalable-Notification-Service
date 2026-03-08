FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY target/notification-0.0.1-SNAPSHOT.jar app.jar

ENV TZ=Asia/Kolkata

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]