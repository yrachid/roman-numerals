FROM openjdk:8 as builder

WORKDIR /workspace

# This section keeps gradle wrapper from being downloaded at every build using Docker layer caching.
COPY *.gradle gradle.* gradlew /workspace/
COPY gradle /workspace/gradle
RUN ./gradlew --version

COPY . .
RUN ./gradlew --no-daemon clean build

FROM gcr.io/distroless/java:8
WORKDIR /app
COPY --from=builder /workspace/app/build/libs/app-1.0.jar app.jar
CMD ["app.jar"]
