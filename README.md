#  Retail Microservices Project

This repository is a **monorepo** containing multiple microservices that together form a retail e-commerce application.  
It is designed to demonstrate **enterprise-level architecture** with modular microservices.

---

##  Microservices in this Repository

- **cart-service** – Manages shopping cart operations (add/remove items, cart state).
- **order-service** – Handles order placement, tracking, and order history.
- **product-service** – Manages products, categories, pricing, and stock.
- **user-service** – Handles authentication, profiles, and user management.
- **retail-gateway** – API Gateway (entry point for all requests, routing to services, autherization and authentication).