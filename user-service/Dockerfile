FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app

COPY common-lib ./common-lib
COPY user-service ./user-service

RUN chmod +x ./user-service/mvnw && \
    cd common-lib && \
    ../user-service/mvnw install -DskipTests && \
    cd ../user-service && \
    ./mvnw package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/user-service/target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar","--spring.profiles.active=prod"]