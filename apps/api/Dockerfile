# Build stage - usa Maven para build
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app

# Copiar arquivos de configuração do Maven
COPY pom.xml ./apps/api/

# Baixar dependências (para cache)
WORKDIR /app/apps/api
RUN mvn dependency:go-offline

# Copiar código fonte e construir
COPY src ./src
RUN mvn clean package -DskipTests

# Runtime stage
FROM maven:3.9.6-eclipse-temurin-17 AS runner
WORKDIR /app

# Copiar o script wait-for-it
COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

# Criar usuário não-root para segurança
RUN groupadd -r appuser && useradd -r -g appuser appuser

# Copiar JAR construído
COPY --from=builder /app/apps/api/target/*.jar app.jar

# Definir permissões
RUN chown appuser:appuser app.jar

# Mudar para usuário não-root
USER appuser

# Configurações de produção
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

# Configurar JVM para container
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"

ENTRYPOINT ["sh", "-c", "/wait-for-it.sh postgres:5432 -t 60 -- java $JAVA_OPTS -jar app.jar"]