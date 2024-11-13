# Description
The RatingApp-Microservice is a distributed application designed to handle rating-related operations with a robust microservices architecture. 
It utilizes various databases for different services and is built with Spring Boot, Spring Cloud, and Kafka to ensure high availability and scalability. 
The application includes multiple services that work in coordination through service registration, configuration management, and API gateway routing.

# Architecture Overview
The application is composed of several microservices, each with a distinct responsibility:

1) User Service: Manages user data with a MySQL database.
2) Rating Service: Handles rating information and uses MongoDB for flexible and scalable data storage.
3) Hotel Service: Manages hotel data with a PostgreSQL database.

# Supporting Components
1) Service Registry: Provides a central registry where all services can register themselves to enable service discovery.
2) API Gateway: Acts as a unified entry point to route requests to the appropriate microservice.
3) Configuration Service: Centralizes configuration management for all services, sourced from a Git repository for versioned configurations.
4) Kafka and Zookeeper: Kafka is used for messaging and communication between services, while Zookeeper helps in managing Kafka brokers and coordinating distributed systems.

# Technologies Used
1) Spring Boot: For building and managing each microservice.
2) Spring Cloud Config: To centralize configuration management with a Git-backed configuration server.
3) Spring Cloud Gateway: For routing requests across the services.
4) Eureka: As a service registry for enabling service discovery.
5) Kafka & Zookeeper: For asynchronous message streaming and broker coordination.

MySQL, MongoDB, PostgreSQL: For persisting data in different services.
Services Overview
1. User Service
Database: MySQL
Purpose: Manages user profiles and related operations.

2. Rating Service
Database: MongoDB
Purpose: Manages ratings provided by users.

3. Hotel Service
Database: PostgreSQL
Purpose: Manages hotel information and related operations.

4. Service Registry
Purpose: Registers all services, allowing them to discover and communicate with each other.

5. Configuration Service
Purpose: Loads configuration properties for each service from a centralized Git repository.

6. API Gateway
Purpose: Routes incoming requests to the appropriate service based on the URL path.

# Configuration Sample (application.yml):
server:
  port: 8085
spring:
  application:
    name: CONFIGURATION-SERVER
  cloud:
    config:
      server:
        git:
          uri: https://github.com/Shubhtiwari29/microservice-configuration
          clone-on-start: true

# Prerequisites
1) Java 11+
2) Spring Boot and Spring Cloud dependencies
3) Kafka & Zookeeper setup
4) Databases used -> MySQL, MongoDB, PostgreSQL databases

# Configure Databases:
Set up MySQL, MongoDB, and PostgreSQL databases for the respective services.
Update each service's application.yml with database connection details.

# Kafka and Zookeeper:
Install and start Kafka and Zookeeper to enable message brokering.
Build and Run Each Service: Run each service individually using Maven:

# Note - Run the Configuration Service:
Ensure the configuration service is connected to the Git repository for configuration data.

Start the API Gateway and Service Registry:
Run the gateway and registry services to enable routing and service discovery.

# API Endpoints
User Service
1) Create User: POST /api/users
2) Get User by ID: GET /api/users/{userId}
3) Update User: PUT /api/users/{userId}
4) Delete User: DELETE /api/users/{userId}
Rating Service
1) Create Rating: POST /api/ratings
2) Get Rating by ID: GET /api/ratings/{ratingId}
3) Update Rating: PUT /api/ratings/{ratingId}
4) Delete Rating: DELETE /api/ratings/{ratingId}
Hotel Service
1) Create Hotel: POST /api/hotels
2) Get Hotel by ID: GET /api/hotels/{hotelId}
3) Update Hotel: PUT /api/hotels/{hotelId}
4) Delete Hotel: DELETE /api/hotels/{hotelId}

# To Test API End Points
Use an API client like Postman or curl to interact with the endpoints. 
The load balancer in the gateway will direct requests to the appropriate microservices, and Kafka enables communication across these services.
