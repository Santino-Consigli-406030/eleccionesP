FROM openjdk:17-jdk-alpine
COPY ./target/*.jar gateway-app.jar
ENTRYPOINT ["java","-jar","gateway-app.jar"]