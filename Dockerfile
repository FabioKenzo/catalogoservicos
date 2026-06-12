# Estágio de Build (Compila o projeto usando Maven oficial)
FROM maven:3-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Estágio de Execução (Roda a aplicação com o Java 25)
FROM openjdk:25-jdk-slim
COPY --from=build /target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]