# Estágio de Build: Maven + JDK 21 (Oficial e disponível)
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copia apenas o pom.xml para otimizar cache
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o código fonte e gera o build pulando os testes
COPY src ./src
RUN mvn clean package -DskipTests

# Estágio de Execução: JRE 21 leve e estável
FROM eclipse-temurin:21-jre-noble
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
