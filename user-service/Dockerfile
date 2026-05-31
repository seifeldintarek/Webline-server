FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app

COPY pom.xml ./pom.xml
COPY common-lib ./common-lib
COPY user-service ./user-service
# Add empty message-service folder so root pom.xml doesn't complain
RUN mkdir -p message-service

RUN chmod +x ./user-service/mvnw && \
    ./user-service/mvnw -f pom.xml clean install \
    -pl common-lib,user-service \
    -am -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/user-service/target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar","--spring.profiles.active=prod"]