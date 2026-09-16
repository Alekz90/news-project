# News Microservice (ms-news)

## Description

This microservice is a news aggregator that consumes the NewsAPI to provide up-to-date news information. It is designed as part of a development portfolio and demonstrates the use of modern technologies in Spring Boot.

## Versions

| Version |                              Description                              |
|:--------|:---------------------------------------------------------------------:|
| 1.0.0   | Created first version with the endpoints that consume a news web api. |
| 1.2.0   |                 Added testing clases with cover 100%.                 |
| 1.3.0   |   Generated web application token and implemented spring security.    |

## Features

- **News Aggregation**: Obtains news from external sources through NewsAPI
- **Redis Caching**: Caches responses to improve performance
- **Resilience**: Implements Circuit Breaker with Resilience4j to handle external service failures
- **API Documentation**: Swagger/OpenAPI for interactive documentation
- **Monitoring**: Actuator endpoints with Prometheus metrics
- **Database**: PostgreSQL for data persistence
- **Containerization**: Docker and Docker Compose for deployment
- **Code Quality**: Integration with SonarQube and JaCoCo for test coverage

## Technologies Used

- **Java 21**
- **Spring Boot 4.0.3**
- **Spring Cloud (OpenFeign, CircuitBreaker)**
- **Redis** (cache)
- **PostgreSQL** (database)
- **Resilience4j** (resilience)
- **OpenAPI/Swagger** (documentation)
- **Docker** (containerization)
- **Gradle** (dependency management)
- **JaCoCo** (test coverage)
- **SonarQube** (code analysis)

## Architecture

The microservice follows a clean architecture with the following layers:

- **Controller**: Handles HTTP requests
- **Service**: Contains business logic
- **Remote**: Feign client for external API calls
- **Repository**: Data access with JPA
- **Configuration**: Spring bean and component configurations

## Main Endpoints

### GET /api/v1/news/top-headlines
Gets the top news headlines.

**Query Parameters:**
- `country` (optional): Country code (e.g., "us", "mx")
- `category` (optional): News category
- `pageSize` (optional): Number of results per page

### GET /api/v1/news/everything
Searches news by specific query.

**Query Parameters:**
- `q`: Search term
- `from` (optional): From date (YYYY-MM-DD)
- `to` (optional): To date (YYYY-MM-DD)
- `sortBy` (optional): Sorting

### GET /api/v1/news/sources
Gets available news sources.

## Configuration

### Required Environment Variables

```bash
# PostgreSQL Database
POSTGRES_DB=news_db
POSTGRES_USER=news_user
POSTGRES_PASSWORD=news_password

# Redis
REDIS_HOST=localhost
REDIS_PORT=6379

# External API
NEWS_API_KEY=your_newsapi_key_here

# SonarQube (optional)
SONAR_KEY=your_sonar_project_key
SONAR_HOST=https://sonarcloud.io
SONAR_TOKEN=your_sonar_token
```

### Circuit Breaker Configuration

```yaml
resilience4j:
  circuitbreaker:
    instances:
      news-instance:
        registerHealthIndicator: true
        slidingWindowSize: 10
        failureRateThreshold: 50
        waitDurationInOpenState: 10000ms
        permittedNumberOfCallsInHalfOpenState: 3
```

## Deployment

### With Docker Compose

1. Make sure Docker and Docker Compose are installed
2. Copy the `env.txt` file to `.env` and configure the variables
3. Run:

```bash
docker-compose up -d
```

### Manual Build

1. Install Java 21 and Gradle
2. Configure environment variables
3. Run:

```bash
./gradlew build
./gradlew bootRun
```

## Monitoring and Health

### Actuator Endpoints

- `GET /actuator/health` - Service health status
- `GET /actuator/metrics` - Application metrics
- `GET /actuator/prometheus` - Metrics in Prometheus format

### API Documentation

- `GET /swagger-ui.html` - Swagger UI interface
- `GET /v3/api-docs` - OpenAPI specification in JSON

## Testing

Run tests with:

```bash
./gradlew test
```

To view the coverage report:

```bash
./gradlew jacocoTestReport
```

The report will be available at `build/reports/jacoco/test/html/index.html`

## Logs

Logs are written to:
- Console (INFO level)
- File: `logs/app.log`

## Development

### Requirements

- Java 21
- Gradle 8+
- Docker (optional)

### Project Structure

```
src/
├── main/
│   ├── java/akz/news/
│   │   ├── MsNewsApplication.java
│   │   ├── configuration/     # Spring configurations
│   │   ├── exception/         # Exception handling
│   │   ├── remote/            # External API clients
│   │   ├── utils/             # Utilities
│   │   └── web/               # Web layer (controllers, services, dtos)
│   └── resources/
│       └── application.yaml   # Main configuration
└── test/                      # Unit and integration tests
```

## Contributing

This project is part of a personal portfolio. For contributions or improvements, please contact the developer.

## License

This project is private and intended solely for portfolio purposes.
