FROM openjdk:8 as builder
WORKDIR /workspace
COPY . .

RUN ls -la && ./gradlew --no-daemon clean build

FROM gcr.io/distroless/java:8
WORKDIR /app
COPY --from=builder /workspace/app/build/libs/numeral-converter.jar app.jar
CMD ["app.jar"]
