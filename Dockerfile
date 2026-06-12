# Estágio de Build (Compila o projeto usando Maven)
# Estágio de Build (Compila o projeto usando Maven com Java 25)
FROM maven:3.9.6-eclipse-temurin-25 AS build
COPY . .
RUN mvn clean package -DskipTests

# Estágio de Execução (Roda a aplicação com o Java 25)
FROM eclipse-temurin:25-jdk
COPY --from=build /target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]