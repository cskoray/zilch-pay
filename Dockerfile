FROM openjdk:17-jdk-slim AS builder
ADD . /src
WORKDIR /src
RUN ./gradlew service:clean service:build  service:bootJar

FROM openjdk:17-jdk-slim
EXPOSE 8085
COPY --from=builder /src/service/build/libs/service-*.jar /usr/local/bin/service.jar
HEALTHCHECK --retries=12 --interval=10s CMD curl -s localhost:8085/health || exit 1
RUN chmod +x /usr/local/bin/service.jar
ENTRYPOINT ["java", "-jar", "/usr/local/bin/service.jar"]

VOLUME /usr/local/zilch-pay
