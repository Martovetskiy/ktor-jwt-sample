# Ktor JWT Sample
[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.x-blue?logo=kotlin)](https://kotlinlang.org)
[![Ktor](https://img.shields.io/badge/Ktor-2.x-0A84FF?logo=ktor)](https://ktor.io)
[![Architecture: Clean](https://img.shields.io/badge/Architecture-Clean-success)](#Clean Architecture)
[![Issues](https://img.shields.io/github/issues/Martovetskiy/ktor-jwt-sample)](https://github.com/Martovetskiy/ktor-jwt-sample/issues)
[![Last Commit](https://img.shields.io/github/last-commit/Martovetskiy/ktor-jwt-sample)](https://github.com/Martovetskiy/ktor-jwt-sample/commits)
---
## Getting Started

### 1. Clone

```bash
git clone https://github.com/Martovetskiy/ktor-jwt-sample.git
cd ktor-jwt-sample
```
### 2. Create .env file (example: `.env.example`)

### 3. Run (Gradle)

```bash
./gradlew run
# or
gradle run
```
App default URL: http://localhost:8080

---

## Running with Docker
```bash
docker compose up -d
```
## Clean Architecture

Goal: Business rules (domain + use cases) stay independent of frameworks, transport, and storage.

Suggested structure (adapt to actual code):

```
kotlin/
  domain/          # Entities, value objects, repository interfaces
  application/     # Use cases / interactors
  infrastructure/  # Repository impls, JWT provider, adapters
  presentation/    # Ktor routes, DTOs, mappers
```
