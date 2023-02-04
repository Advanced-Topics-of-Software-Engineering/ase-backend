#
# Build stage
#
FROM yannoff/maven:3-openjdk-19 AS build
COPY src /home/app/ase23/src
COPY pom.xml /home/app/ase23
RUN mvn -f /home/app/ase23/pom.xml clean package

#
# Package stage
#
FROM openjdk:19-jdk
COPY --from=build /home/app/ase23/target/ase23-0.0.1-SNAPSHOT.jar /app/ase23/ase23-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/ase23/ase23-0.0.1-SNAPSHOT.jar"]

