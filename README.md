# SecureAccess

SecureAccess is a microservices-based project designed for secure user authentication and access management. It includes:
- **OTP Support:** One-time password functionality for enhanced security.
- **Two-Factor Authentication:** Implemented using SPI extensions for Keycloak.
- **API Gateway:** Centralized routing and authentication.
- **Eureka Service:** Service discovery for the microservices.
- **Consumer, Producer, and Product Services:** Modular microservices for specific business domains.

## Features
- OTP and Two-Factor Authentication (2FA) using Keycloak SPI
- Docker Compose setup for seamless deployment
- Microservices architecture with Spring Boot
- Keycloak integration for identity and access management
- Service discovery with Eureka

## Getting Started

### Prerequisites
- Docker and Docker Compose
- Java 17 or higher
- Maven

### Setup Instructions
1. Clone the repository:
   ```bash
   git clone https://github.com/username/SecureAccess.git
   
2. Navigate to the project directory:
   ```bash
   cd SecureAccess
   ```
3. Start the services using Docker Compose:
   ```bash
   docker-compose up --build
   ```
