FROM openjdk:8 as builder

WORKDIR /workspace

COPY . .

RUN ./gradlew --no-daemon clean build

FROM gcr.io/distroless/java:8

WORKDIR /app

COPY --from=builder /workspace/app/build/libs/app-1.0.jar app.jar

CMD ["app.jar"]
