# News Project

A Java-based microservices project that aggregates and exposes news content through a resilient, containerized Spring Boot architecture.

## Overview

This project combines a news aggregation service with service discovery and infrastructure components to provide a clean, modular backend for consuming external news data. It is designed to show modern microservice patterns using Spring Boot 4, Java 21, Docker Compose, Redis, PostgreSQL, and Eureka.

The project is centered on:

- `ms-news`: main news API service that fetches and processes articles
- `ms-eureka`: Eureka service discovery server
- `ms-gateway`: optional gateway layer for routing and centralized access

## Architecture

```text
+--------------------+      +--------------------+
| Client / Browser   | ---> | API Gateway        |
+--------------------+      +--------------------+
                                     |
                                     v
                         +--------------------+
                         | Eureka Discovery   |
                         +--------------------+
                                     |
                                     v
                         +--------------------+
                         | ms-news            |
                         | - NewsAPI client   |
                         | - Redis cache      |
                         | - PostgreSQL       |
                         | - Actuator         |
                         +--------------------+
```

## Tech Stack

- Java 21
- Spring Boot 4.0.3
- Spring Cloud
- Springdoc OpenAPI / Swagger UI
- PostgreSQL
- Redis
- Resilience4j
- Docker / Docker Compose
- Gradle
- Eureka Service Discovery
- Prometheus / Actuator metrics

## Repository Structure

```text
news-project/
├── .github/
├── gradle/
├── logs/
├── ms-eureka/
│   └── src/main/resources/application.yaml
├── ms-gateway/
├── ms-news/
│   ├── src/main/java/
│   ├── src/main/resources/application.yaml
│   ├── src/test/
│   └── README.md
├── .env
├── build.gradle
├── compose.yaml
├── gradlew
├── gradlew.bat
├── LICENSE
├── README.md
├── settings.gradle
└── ms-news.dockerfile
```

## Main Features

- Fetches top headlines, article search results, and available sources from NewsAPI
- Uses Redis for response caching to reduce repeated external API calls
- Uses PostgreSQL for persistence and data storage
- Implements circuit breaking with Resilience4j for fault tolerance
- Exposes OpenAPI/Swagger documentation for testing the API
- Integrates health and metrics endpoints via Spring Boot Actuator
- Runs through Docker Compose for local development and deployment

## Services

### ms-news

The primary service of the project. It exposes endpoints for:

- retrieving top headlines
- searching all available news items
- fetching available news sources

It also includes configuration for:

- Redis cache
- PostgreSQL datasource
- Eureka registration
- external API communication with NewsAPI
- Swagger documentation

See `ms-news/README.md` for more service-specific details.

### ms-eureka

Acts as the service registry for the microservice ecosystem. It allows services to register and discover each other dynamically.

### ms-gateway

The gateway layer handles routing and centralized access to backend services.

## Prerequisites

Before running the project, make sure you have:

- Java 21 or newer
- Gradle
- Docker and Docker Compose
- A valid NewsAPI key
- PostgreSQL and Redis if running the services locally without Docker

## Environment Variables

Create or update the `.env` file in the project root with the following values:

```env
NEWS_API_KEY=your_newsapi_key
NEWS_API_SECRET=your_newsapi_secret
REDIS_HOST=localhost
REDIS_PORT=6379
POSTGRES_HOST=localhost
POSTGRES_PORT=5432
POSTGRES_DB=news
POSTGRES_USER=admin
POSTGRES_PASSWORD=admin123
SONAR_KEY=ms-news
SONAR_HOST=http://localhost:9000
SONAR_TOKEN=your_sonarqube_token
EUREKA_HOST=http://localhost:8761/eureka/
```

Important:

- `NEWS_API_KEY` is required for fetching external news content.
- Redis and PostgreSQL values are used by the application and Docker Compose configuration.
- The `EUREKA_HOST` value enables registration with the discovery service.

## Running the Project

### Option 1: Docker Compose

From the project root:

```bash
docker compose up --build -d
```

This will start:

- Eureka service on `http://localhost:8761`
- PostgreSQL on port `5432`
- Redis on port `6379`
- News service on port `8080`

To stop everything:

```bash
docker compose down
```

### Option 2: Run Services Locally

Start Eureka first:

```bash
./gradlew :ms-eureka:bootRun
```

Then start the news service:

```bash
./gradlew :ms-news:bootRun
```

If the gateway module is enabled, run it separately:

```bash
./gradlew :ms-gateway:bootRun
```

## API Documentation

The news service exposes Swagger/OpenAPI documentation once it is running.

- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

## Endpoints

The service includes the following main endpoints:

- `GET /api/v1/news/top-headlines`
- `GET /api/v1/news/everything`
- `GET /api/v1/news/sources`

For detailed request and response examples, see the Swagger UI or the service-specific README.

## Monitoring and Health

Spring Boot actuator exposes health and metrics endpoints:

- `GET /actuator/health`
- `GET /actuator/metrics`
- `GET /actuator/prometheus`

Monitoring is configured for Prometheus and general application metrics.

## Testing

Run the project tests with:

```bash
./gradlew test
```

For test coverage reports, if configured in the service module:

```bash
./gradlew jacocoTestReport
```

## Logging

Application logs are written to:

```text
logs/app.log
```

## Development Notes

This project is structured as a modular microservice system and is suitable for learning or demonstrating:

- service discovery
- API integration with external services
- distributed configuration patterns
- resilience and fault tolerance
- Redis caching strategies
- Docker-based local infrastructure

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

## Contributing

Contributions are welcome. If you want to improve the project, open a pull request and describe the change clearly.
