
FROM mcr.microsoft.com/playwright/java:v1.64.0-noble

WORKDIR /app

# Copy Maven configuration first
COPY pom.xml .

# Download project dependencies
RUN mvn -B dependency:go-offline

# Copy framework source code and resources
COPY src ./src

# Copy TestNG suite configuration
COPY testng.xml .

# Run the test suite
CMD ["mvn", "-B", "clean", "test", "-Denv=qa"]
