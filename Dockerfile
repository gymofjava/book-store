FROM openjdk:17-jdk-alpine3.14
RUN apk add --no-cache tzdata
ENV TZ=Asia/Tehran

WORKDIR /app
COPY target/*.jar java_app.jar
ENTRYPOINT ["java", "-jar", "java_app.jar"]
