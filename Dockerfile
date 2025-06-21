FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY build/libs/usuarioEntity-0.0.1-SNAPSHOT.jar /app/usuarioEntity.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/usuarioEntity.jar"]