FROM openjdk:11
WORKDIR app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} drawingcourse.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","drawingcourse.jar"]