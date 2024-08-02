FROM openjdk:17.0.1-slim AS build


COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve


COPY src src
RUN ./mvnw package

FROM openjdk:17.0.1-slim
WORKDIR open-wheather
COPY --from=build target/*.jar open-wheather.jar
ENTRYPOINT ["java","-jar","open-management.system.jar"]
