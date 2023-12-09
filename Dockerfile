FROM openjdk:17
ADD target/kimyo-uz.jar app.jar
VOLUME /simple.app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]