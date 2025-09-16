# syntax=docker/dockerfile:1.7

# ---------- Build stage (fast, cached) ----------
FROM maven:3.9-eclipse-temurin-24 AS build
WORKDIR /app

# Leverage Docker layer cache + BuildKit cache for Maven repo
COPY pom.xml .
RUN --mount=type=cache,target=/root/.m2 mvn -q -T 1C -DskipTests dependency:go-offline

# Copy sources and build
COPY src src
RUN --mount=type=cache,target=/root/.m2 mvn -q -o -T 1C -DskipTests package

# ---------- Runtime stage ----------
FROM eclipse-temurin:24-jre
WORKDIR /app

ENV JAVA_OPTS=""
ENV SPRING_PROFILES_ACTIVE=default

# Copy the fat jar
COPY --from=build /app/target/DTO-Pattern-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["/bin/sh","-c","java $JAVA_OPTS -jar app.jar"]


