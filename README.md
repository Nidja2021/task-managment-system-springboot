## Task Managment System - Spring Boot 3, JPA, Spring Security with JWT and Docker

### Description

#### This is a sample project that demonstrates how to build a RESTful web service for a task managment system using Spring Boot 3, JPA, Spring Security with JWT, and Docker.

## Prerequisites
* JDK 17 or higher
* Maven 3.8.6 or higher
* PostgreSQL database
* Docker
* Docker compose

## Getting Started
1. #### Clone the repository:

`https://github.com/Nidja2021/task-managment-system-springboot.git`

2. #### Run the Docker containers using Docker Compose:

`docker-compose up`

The application will start at **http://localhost:8080**.

### Usage
You can use any REST client (e.g. Postman, Insomnia) to interact with the API. The following endpoints are available:

**/api/v1/auth/register** (POST): Create a new user account.

**/api/v1/auth/login** (POST): Authenticate a user and generate a JWT token.

**/api/v1/tasks** (GET): Get all tasks (requires authentication).

**/api/v1/tasks** (POST): Create a new task (requires authentication).

**/api/v1/tasks/{id}** (GET): Get a task by ID (requires authentication).

**/api/v1/tasks/{id}** (PUT): Update a task by ID (requires authentication).

**/api/v1/tasks/{id}** (DELETE): Delete a task by ID (requires authentication).

**/api/v1/tasks/assigned_to/{employeeId}** (GET): Get all tasks by employee ID (requires authentication).

**/api/v1/tasks/status/{status}** (GET): Get all tasks by task status (requires authentication).

**/api/v1/tasks/{id}/comments** (GET): Get all comments by task ID (requires authentication).

**/api/v1/task/{id}/comments** (POST): Create a comment by task ID (requires authentication).

**/api/v1/employees** (GET): Get all employees (requires authentication).

**/api/v1/employess/{id}** (GET): Get employee by ID (requires authentication).

To access the protected endpoints, include the JWT token in the Authorization header using the Bearer scheme.