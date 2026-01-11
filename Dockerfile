FROM eclipse-temurin:17-jre

WORKDIR /app

COPY target/spring-boot1-0.0.1-SNAPSHOT app.jar

EXPOSE 8080

ENTRYPOINT ["java"
,
"-jar"
,
"app.jar"]