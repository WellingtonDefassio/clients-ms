FROM openjdk:17-alpine
VOLUME /tmp 
COPY target/clients-ms-0.0.1-SNAPSHOT.jar clients-ms.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/clients-ms.jar"]