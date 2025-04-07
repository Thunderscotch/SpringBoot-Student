FROM eclipse-temurin:17-jdk

COPY target/demo-0.0.1-SNAPSHOT.jar demo-0.0.1-SNAPSHOT.jar

EXPOSE 8096

ENTRYPOINT ["java","-jar","demo-0.0.1-SNAPSHOT.jar"]