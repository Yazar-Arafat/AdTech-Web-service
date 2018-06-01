FROM openjdk:8
ADD ./target/AdTech-0.0.1-SNAPSHOT.jar
EXPOSE 8100
ENTRYPOINT ["java","-jar","AdTech-0.0.1-SNAPSHOT.jar"]