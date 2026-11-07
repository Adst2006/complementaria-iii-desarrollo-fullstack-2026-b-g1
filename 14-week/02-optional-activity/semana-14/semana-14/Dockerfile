# ---------- Etapa 1: compilar el .jar con Maven ----------
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /build
COPY pom.xml .
COPY src ./src
RUN mvn -q -DskipTests clean package

# ---------- Etapa 2: imagen final, solo con el JRE ----------
FROM eclipse-temurin:17-jre
COPY --from=build /build/target/app.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
