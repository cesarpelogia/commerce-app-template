FROM maven:3.9-openjdk-17-slim AS builder
WORKDIR /App

# Configuração do Maven
COPY apps/api/pom.xml ./
RUN mvn dependency:go-offiline

# Copia código e builda
COPY apps/api/src ./src
RUN mvn clean package -DskipTests

# Runtime - Produção
FROM openjdk:17-slim AS runner
WORKDIR /App

COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

# Copia JAR da build
COPY --from=builder /App/target/*.jar app.jar

# Configurações de produção
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "/wait-for-it.sh postgres:5432 -t 60 -- java -jar app.jar"]