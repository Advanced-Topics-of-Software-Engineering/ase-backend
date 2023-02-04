# Use openjdk:19 as the base image
FROM openjdk:19

# Copy the target directory (which contains the built application) to the container
#COPY target/ /app

# Set the working directory to the app directory
WORKDIR /app
CMD ["mvnw clean install"]
# Run the application (assuming the application is a Spring Boot application with a executable )
# -D skiptests
ENTRYPOINT ["java", "-jar", "ase23-0.0.1-SNAPSHOT.jar"]
