FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /App

COPY apps/template/pom.xml ./
RUN mvn dependency:go-offline

COPY apps/template/src ./src
RUN mvn clean package -DskipTests

# Runtime - Produção
FROM eclipse-temurin:21-jre AS runner
WORKDIR /App

# Copia JAR da build
COPY --from=builder /App/target/*.jar app.jar

# Configurações de produção
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]