
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the entire project into the container
COPY . /app

# Install Maven inside the container
RUN apt-get update && apt-get install -y maven

# Build the Spring Boot application inside the container
RUN mvn clean package -DskipTests

# Expose the default Spring Boot port
EXPOSE 8888

# Run the Spring Boot application
CMD ["mvn", "spring-boot:run"]
