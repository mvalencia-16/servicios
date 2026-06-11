# ── Etapa 1: compilar el JAR con Maven ──────────────────────────────────────
FROM amazoncorretto:17-alpine AS builder
WORKDIR /app

# Copiar descriptores de dependencias primero (cache de capas)
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn
RUN chmod +x mvnw && ./mvnw dependency:go-offline -q

# Copiar el código fuente y construir
COPY src src
RUN ./mvnw package -DskipTests -q

# ── Etapa 2: imagen final ligera ─────────────────────────────────────────────
FROM amazoncorretto:17-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]