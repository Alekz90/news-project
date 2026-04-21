
# Get the image
FROM gradle:9.4-jdk21-alpine AS build

# Create the directory
WORKDIR /app

# Copy the necesary files
COPY build.gradle settings.gradle ./

# Copy child projects
COPY ms-eureka ./ms-eureka/
COPY ms-news ./ms-news/

# Download the dependencies and build the project
RUN gradle :ms-news:build -x test --no-daemon || true

# Create the final image
FROM eclipse-temurin:21-jdk-alpine-3.20

# Create the directory
WORKDIR /app

# Copy the generated jar in the building
COPY --from=build /app/ms-news/build/libs/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]

# Move to the directory
# cd C:/Projects/News/news-project

# Construir la imagen # "--no-cache" without caching
# docker build -t "ms-news-img:1.0.3" -f ms-news.dockerfile .

# Ejecutar el contenedor
# docker run --name "ms-news-container" "ms-news-img:1.0.3"

# Delete the container
# docker container rm -f "ms-news-container"

# Delete the image
# docker image rm "ms-news-img:1.0.3"