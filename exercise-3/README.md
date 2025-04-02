# Exercise 3 - Architectural Design

## High-Level Architecture

- UserService exposes gRPC endpoints.
- Communicates synchronously with OrderService and PaymentService via gRPC.
- Uses Kafka to send async notifications to NotificationService.

## Scalability

- Scaled horizontally via Kubernetes (multiple pods).
- Coroutines used for lightweight concurrency.
- Redis used for caching hot user profiles.

## Fault Tolerance

- Circuit breaker and retry strategies for downstream services.
- Logging and metrics integrated for observability.
- Graceful fallback responses if dependencies are down.

## Deployment

- Services are containerized with Docker.
- Kubernetes handles orchestration and auto-scaling.
- Environment variables managed via ConfigMaps and Secrets.
