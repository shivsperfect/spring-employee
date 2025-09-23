FROM maven:3.9.5 AS build
WORKDIR /app
COPY pom.xml /app
COPY src ./src
RUN mvn dependency:resolve
COPY . /app
RUN mvn clean
RUN mvn package -DSkipTests -X

FROM openjdk
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080

USER app

# Entry point with overridable JAVA_OPTS and SERVER_PORT
CMD ["java", "jar", "app.jar"]
