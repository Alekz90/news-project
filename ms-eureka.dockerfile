
# Get the image
FROM gradle:9.4-jdk21-alpine AS build

# Create the directory
WORKDIR /app

# Copy parent gradle configuration
COPY build.gradle settings.gradle ./

# Copy child projects
COPY ms-eureka ./ms-eureka/
COPY ms-news ./ms-news/

# Download the dependencies and build the project
RUN gradle :ms-eureka:build -x test --no-daemon || true

# Create the final image
FROM eclipse-temurin:21-jdk-alpine-3.20

# Create the directory
WORKDIR /app

# Copy the generated jar in the building
COPY --from=build /app/ms-eureka/build/libs/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]

# Move to the directory
# cd C:/Projects/News/news-project

# Construir la imagen # "--no-cache" without caching
# docker build -t "ms-eureka-img:1.0.0" -f Dockerfile.ms-eureka .

# Ejecutar el contenedor
# docker run --name "ms-eureka-container" "ms-eureka-img:1.0.0"

# Delete the container
# docker container rm -f "ms-eureka-container"

# Delete the image
# docker image rm "ms-eureka-img:1.0.0"
