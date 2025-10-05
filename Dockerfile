# syntax=docker/dockerfile:1.7

ARG JAVA_VERSION=24

############################
# 1. Build stage
############################
FROM eclipse-temurin:${JAVA_VERSION}-jdk-alpine AS build
WORKDIR /workspace

RUN apk add --no-cache bash

COPY gradlew .
COPY gradle gradle

COPY settings.gradle.kts build.gradle.kts ./

RUN chmod +x gradlew && \
    ./gradlew --no-daemon dependencies > /dev/null 2>&1 || true

COPY src src

RUN ./gradlew --no-daemon clean shadowJar || ./gradlew --no-daemon clean build

RUN set -e; \
    JAR_FILE="$(find build/libs -maxdepth 1 -type f \( -name '*-all.jar' -o -name '*-fat.jar' -o -name '*.jar' \) | head -n1)"; \
    echo "Using jar: $JAR_FILE"; \
    cp "$JAR_FILE" /workspace/app.jar

############################
# 2. Runtime stage
############################
FROM eclipse-temurin:${JAVA_VERSION}-jre-alpine AS runtime

LABEL org.opencontainers.image.authors="stepan" \
      org.opencontainers.image.source="(add your repo URL)" \
      org.opencontainers.image.title="ktor-jwt-sample" \
      org.opencontainers.image.description="Ktor Jwt App"

ENV APP_HOME=/app \
    JAVA_OPTS="-XX:MaxRAMPercentage=75 -XX:+UseContainerSupport -XX:+ExitOnOutOfMemoryError" \
    TZ=UTC

WORKDIR $APP_HOME

RUN addgroup -S app && adduser -S app -G app

COPY --from=build /workspace/app.jar ./app.jar

EXPOSE 8080

HEALTHCHECK --interval=30s --timeout=3s --start-period=20s --retries=3 \
  CMD wget -qO- http://127.0.0.1:8080/health || exit 1

USER app

ENTRYPOINT ["/bin/sh","-c","exec java $JAVA_OPTS -jar app.jar"]