# Use the official OpenJDK image
FROM openjdk:21-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven wrapper and pom.xml file
COPY mvnw ./
COPY pom.xml ./

# Copy the .mvn directory (including wrapper files)
COPY .mvn ./

# Copy the src folder to the container
COPY src ./src

# Run the Maven build (this will also download dependencies)
RUN ./mvnw clean package

# Specify the command to run the application
CMD ["java", "-jar", "target/*.jar"]
