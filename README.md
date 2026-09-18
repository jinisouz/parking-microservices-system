#  Smart Parking Management System

A robust, full-stack microservices application built with **Java**, **Spring Boot**, and **Maven** to manage parking logistics, user registrations, and spot reservations.

---

## System Architecture
The application consists of 5 decoupled microservices:
1. **`discovery-server`** (Eureka Server) — Central service registry for network discovery.
2. **`api-gateway`** (Spring Cloud Gateway) — Single entry point handling application routing.
3. **`user-service1`** — Manages user profiles and authentication data.
4. **`parking-service`** — Handles parking lot logistics and spot availability.
5. **`booking-service`** — Processes real-time reservations and scheduling.

---

## Setup & Execution (Eclipse IDE)

### Clone and Import Project
1. **Clone the repository** to your local machine using your preferred Git client or terminal.
2. Open **Eclipse IDE for Enterprise Java Developers**.
3. Go to **File** , **Import...** , **Maven** , **Existing Maven Projects** and click **Next**.
4. Click **Browse...** and select the newly cloned repository folder (the root folder containing all 5 services).
5. Ensure all 5 modules are checked and click **Finish** to let Maven download dependencies.

### Startup Sequence 
To avoid registration timeouts, start the services via **Run As** , **Spring Boot App** in this exact order:

| Step | Microservice | Role |
| :--- | :--- | :--- |
| **1**  | **`discovery-server`** | Must be fully active first to register incoming services. |
| **2**  | **`api-gateway`** | Launch once the discovery server is healthy. |
| **3**  | **`user-service1`** | Can be launched concurrently with other feature services. |
| **3**  | **`parking-service`** | Can be launched concurrently with other feature services. |
| **3**  | **`booking-service`** | Can be launched concurrently with other feature services. |

---

##  Prerequisites
* **Java SDK 17+** configured in Eclipse.
* Local database instances running as configured in each service's `src/main/resources/application.properties`.