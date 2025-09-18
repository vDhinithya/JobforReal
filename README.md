# JobforReal - Job Portal Backend

## Overview
**JobforReal** is a comprehensive backend solution for a job portal application. It allows job seekers and employers to interact seamlessly, with features like job postings, candidate applications, and user authentication. This backend is built using **Spring Boot** and **MongoDB**, making it scalable and easy to extend for microservices architecture.

---

## Features

- **User Management**
  - Registration and authentication
  - Profile management for candidates and employers

- **Job Management**
  - Create, read, update, and delete (CRUD) job listings
  - Search and filter jobs by criteria

- **Application Management**
  - Apply for jobs as a candidate
  - Track applications and statuses

- **Secure and Scalable**
  - JWT-based authentication
  - MongoDB for flexible document storage
  - RESTful APIs for easy frontend integration

---

## Technology Stack

| Layer          | Technology                |
|----------------|---------------------------|
| Backend        | Java, Spring Boot         |
| Database       | MongoDB                   |
| Build Tool     | Maven                     |
| API Format     | RESTful JSON              |
| Version Control| Git, GitHub               |

---

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven
- MongoDB running locally or on a server
- Git
- Docker (optional, if running with Docker)

## Running the Application

### **Option 1: Run Locally**

1. Clone the repository:

```bash
git clone https://github.com/vDhinithya/JobforReal.git
cd JobforReal
 ```

2. Install depedencies:

```bash 
mvn clean install

```
3. Configure MongoDB connection in ```application.properties``` or ```application.yml```:
```bash 
spring.data.mongodb.uri=mongodb://localhost:27017/jobportal

```
4. Run the application
```bash 
mvn spring-boot:run
 ```
The backend will be available at ``` http://localhost:5050 ```

### **Option 2: Run using Docker**

1. Build the Docker image
From the project root (Where the ```Dockerfile``` is):
 ```bash 
 docker build -t jobforreal-backend
  ```
2. Run Docker Container
 ```bash 
 docker run -d -p 5050:5050 --name jobforreal-backend jobforreal-backend
  ```
