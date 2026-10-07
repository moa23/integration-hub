# Integration Hub

A backend integration service built with Java and Spring Boot.

The project demonstrates how an application can integrate with an external REST API, transform and persist data in PostgreSQL, and publish events through RabbitMQ.

## Architecture

```text
External REST API
       |
       v
Spring Boot
       |
       +----> PostgreSQL
       |
       +----> RabbitMQ
                  |
                  v
              Event Consumer
```

## What the project demonstrates

* REST API development with Spring Boot
* Integration with an external REST API
* DTOs and data mapping
* PostgreSQL and Spring Data JPA
* Event-driven communication with RabbitMQ
* Message producer and consumer
* Error handling for external API failures
* Unit testing with JUnit and Mockito
* API documentation with Swagger/OpenAPI
* Containerized development with Docker and Docker Compose

## Technologies

* Java 21
* Spring Boot
* Spring Data JPA
* PostgreSQL
* RabbitMQ
* Docker
* Maven
* JUnit
* Mockito
* Swagger / OpenAPI

## Integration flow

The main integration flow is:

1. The application receives a request to import a customer.
2. Spring Boot calls an external REST API.
3. The external customer data is mapped to the application's `Customer` model.
4. The customer is persisted in PostgreSQL.
5. A `customer.imported` event is published to RabbitMQ.
6. A RabbitMQ consumer receives the event.

## API examples

### Get external customer

```http
GET /api/integrations/customers/{id}
```

### Import customer

```http
POST /api/integrations/customers/import/{id}
```

### Get customers

```http
GET /api/customers
```

### Create customer

```http
POST /api/customers
```

## Running the project

The project uses Docker Compose for PostgreSQL and RabbitMQ.

Start the services with:

```bash
docker compose up -d
```

Build the application:

```bash
./mvnw clean package
```

Build the Docker image:

```bash
docker build -t integration-hub .
```

Start the application and supporting services:

```bash
docker compose up -d
```

## Testing

Run the test suite with:

```bash
./mvnw clean test
```

## API documentation

Swagger UI is available at:

```text
http://localhost:8080/swagger-ui/index.html
```

## Project purpose

This project was built as a practical demonstration of backend development and system integration using Java and Spring Boot.

The focus is on REST APIs, integration between systems, persistence, messaging, testing, and containerization.
