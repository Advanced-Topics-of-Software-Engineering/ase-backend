#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:19-jdk
COPY --from=build /home/app/target/ase23-0.0.1-SNAPSHOT.jar /app/ase23/ase23-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/ase23/ase23-0.0.1-SNAPSHOT.jar"]
