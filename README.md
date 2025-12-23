# 🌦 Weather Service

A Spring Boot–based Weather Service application that provides weather details for a given city.
The application uses **H2 in-memory database** for persistence and **Redis Cache** to improve
performance by reducing frequent database calls.

---

## 🛠 Tech Stack
- Java 8+
- Spring Boot
- Spring Data JPA
- H2 Database (In-memory)
- Redis Cache
- Maven
- REST APIs

---

## 🔄 Application Flow
Client → Controller → Service  
- If weather data exists in **Redis Cache**, return the cached response  
- If cache miss occurs, fetch data from **H2 Database**, store it in Redis, and return response  

This approach improves response time and reduces database load.

---

## ✨ Features
- Fetch weather details by city
- Store weather data in H2 database
- Redis caching for frequently accessed data
- Cache TTL (Time-To-Live) configured
- Reduced database load and improved performance

---

## 📡 API Endpoints

| HTTP Method | Endpoint | Description |
|------------|---------|-------------|
| GET | `/weather/{city}` | Get weather details by city |
| POST | `/weather` | Save weather information |
| PUT | `/weather/{id}` | Update weather details |
| DELETE | `/weather/{id}` | Delete weather record |

---

## 🚀 Redis Cache Implementation
- Redis is used as a distributed cache
- `@Cacheable` is used for fetching weather data
- `@CachePut` is used while updating weather details
- `@CacheEvict` is used when deleting weather records
- TTL (Time-To-Live) is configured using `RedisCacheManager`

Redis caching significantly improves application performance by minimizing repeated database access.

---

## 🗄 Database Configuration
- H2 in-memory database is used for development and testing
- JPA is used for ORM and database interaction
- H2 Console is enabled for easy data inspection

### H2 Console
http://localhost:8080/h2-console

---

## ⚙ Configuration Highlights
- Redis host, port, and TTL configured in `application.yml`
- Cache Manager configured for Redis
- H2 database auto-configured using Spring Boot

---

## ▶ How to Run the Application

### Prerequisites
- Java 8 or above
- Redis installed and running
- Maven

### Steps
1. Clone the repository
   ```bash
   git clone <repository-url>

