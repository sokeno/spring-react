FROM openjdk:11
ADD target/fullstack.jar fullstack.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "fullstack.jar"]