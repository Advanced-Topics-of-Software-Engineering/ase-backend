FROM openjdk:19
RUN mvn clean install
WORKDIR /app
COPY ./target/ase23-0.0.1-SNAPSHOT.jar /app/ase23-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "ase23-0.0.1-SNAPSHOT.jar"]
