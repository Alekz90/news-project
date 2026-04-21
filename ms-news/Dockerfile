
# Get the image
FROM gradle:9.4-jdk21-alpine AS build

# Create the directory
WORKDIR /project

# Copy the necesary files
COPY build.gradle settings.gradle ./
#COPY gradle ./gradle

# "-x test" skip test
# Download the dependencies
RUN gradle build -x test --no-daemon || true

# Copy the source
COPY src ./src

# Run tests (This step will only be done in the workflow actions)
# RUN gradle test --no-daemon

# Build the jar without tests
RUN gradle bootJar -x test --no-daemon

# Create the final image
FROM eclipse-temurin:21-jdk-alpine-3.20

# Create the directory
WORKDIR /project

# Copy the generated jar in the building
COPY --from=build /project/build/libs/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]

# Move to the directory
# cd C:/Projects/News/ms-news

# Construir la imagen # "--no-cache" without caching
# docker build -t "ms-news-img:1.0.0" .

# Ejecutar el contenedor
# docker run --name "ms-news-container" "ms-news-img:1.0.0"

# Delete the container
# docker container rm -f "ms-news-container"

# Delete the image
# docker image rm "ms-news-img:1.0.0"