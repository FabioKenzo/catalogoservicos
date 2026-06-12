# Estágio de Build (Compila usando o Maven oficial com Java 17)
FROM maven:3.9-eclipse-temurin-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Estágio de Execução (Roda a aplicação usando o ambiente oficial do Eclipse Temurin para Java)
FROM eclipse-temurin:21-jre
COPY --from=build /target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]