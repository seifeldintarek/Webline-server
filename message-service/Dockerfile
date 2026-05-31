FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app

COPY common-lib ./common-lib
COPY message-service ./message-service

RUN chmod +x ./message-service/mvnw && \
    cd common-lib && \
    ../message-service/mvnw install -DskipTests && \
    cd ../message-service && \
    ./mvnw package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/message-service/target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar","--spring.profiles.active=prod"]