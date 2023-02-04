FROM openjdk:19
WORKDIR /app
RUN apt-get update && apt-get install -y maven
RUN mvn clean install
COPY ./target/ase23-0.0.1-SNAPSHOT.jar /app/ase23-0.0.1-SNAPSHOT.jar
EXPOSE 8080

CMD ["java", "-jar", "ase23-0.0.1-SNAPSHOT.jar"]
