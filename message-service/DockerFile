FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app

COPY pom.xml ./pom.xml
COPY common-lib ./common-lib
COPY message-service ./message-service
# Add empty user-service folder so root pom.xml doesn't complain
RUN mkdir -p user-service

RUN chmod +x ./message-service/mvnw && \
    ./message-service/mvnw -f pom.xml clean install \
    -pl common-lib,message-service \
    -am -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/message-service/target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar","--spring.profiles.active=prod"]