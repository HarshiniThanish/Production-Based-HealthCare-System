# Healthcare Management System – Microservices Architecture

A scalable and modular Healthcare Management System built using **Spring Boot** and a **microservices architecture**. The application separates core healthcare operations into independent services, making the system easier to develop, maintain, scale, and deploy.

The project uses **REST APIs**, **gRPC**, **Apache Kafka**, **JWT-based authentication**, **Docker**, and **integration testing** to enable secure and reliable communication between services.

## Microservices

### Patient Management Service

Manages patient-related operations, including creating, retrieving, updating, and deleting patient records. It acts as the core service of the system and exposes REST APIs for handling patient information.

### Authentication Service

Handles user registration and login. It authenticates users and generates JWT tokens, helping secure access to protected healthcare APIs.

### Billing Service

Handles billing-related operations for patients. It uses **gRPC** to communicate efficiently with other services and supports fast, strongly typed service-to-service communication.

### Analytics Service

Consumes patient-related events through **Apache Kafka** and processes them asynchronously. This enables event-driven analytics without tightly coupling the analytics functionality to the Patient Management Service.

### API Gateway

Acts as the single entry point for client requests. It routes incoming requests to the appropriate microservice and simplifies communication between the frontend or external clients and the backend services.

## Communication Architecture

* **REST APIs** are used for client-to-service communication.
* **gRPC** enables efficient and strongly typed communication between internal services.
* **Apache Kafka** supports asynchronous, event-driven communication between services.
* **JWT authentication** protects secured endpoints and enables stateless authentication.
* **Docker** containerizes each microservice and its dependencies, making the application easier to run consistently across environments.

## Testing

Integration tests are included to verify that services, APIs, and external dependencies work together correctly. This helps ensure reliable communication and reduces issues caused by interactions between multiple services.

Tech Stack scalable healthcare management system built with Spring Boot and microservices. It includes Patient Management, Authentication, Billing, Analytics, and API Gateway services, using gRPC, Kafka, JWT authentication, Docker, and integration testing for secure, reliable, and efficient communication.

* Spring Data JPA
* PostgreSQL
* gRPC
* Apache Kafka
* JWT
* Docker
* Maven
* JUnit
* Integration Testing

## Key Highlights

* Designed using a modular microservices architecture
* Secure authentication using JWT
* Centralized request routing through an API Gateway
* Efficient synchronous communication using gRPC
* Event-driven analytics using Apache Kafka
* Dockerized services for consistent deployment
* Integration testing for improved reliability
