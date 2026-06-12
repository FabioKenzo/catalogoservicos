# Estágio de Build: Usa o Java 25 oficial e instala o Maven manualmente
FROM eclipse-temurin:25-jdk AS build
RUN apt-get update && apt-get install -y maven
COPY . .
RUN mvn clean package -DskipTests

# Estágio de Execução: Roda a aplicação com o ambiente leve do Java 25
FROM eclipse-temurin:25-jre
COPY --from=build /target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]