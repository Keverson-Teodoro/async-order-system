# async-order-system

> Sistema distribuído avançado construído com **Spring Boot**, **RabbitMQ** e **bancos de dados independentes**, demonstrando uma arquitetura **orientada a eventos (Event-Driven)** entre múltiplos microserviços.

---

- 🚀 Visão Geral

Este projeto simula o fluxo completo de **pedidos de compra online**, utilizando comunicação assíncrona entre diferentes microserviços.

Cada serviço é responsável por um contexto de negócio isolado e se comunica via **mensageria RabbitMQ**.  
A arquitetura reflete um cenário real de sistemas escaláveis e desacoplados.

---

## ⚙️ Tecnologias Utilizadas

- ☕ **Java 17+**
- 🧱 **Spring Boot 3+**
- 🐇 **RabbitMQ (mensageria)**
- 🐘 **PostgreSQL (bancos relacionais)**
- 🐳 **Docker & Docker Compose**
- 🧠 **Spring AMQP**
- 💬 **Jackson2JsonMessageConverter** (para serialização JSON)

---

## 🧠 Arquitetura

            ┌────────────────────┐
            │  Order Service     │
            │  (Criação Pedido)  │
            └─────────┬──────────┘
                      │
              order.created
                      ▼
            ┌────────────────────┐
            │ Inventory Service  │
            │ (Reserva Estoque)  │
            └─────────┬──────────┘
      inventory.reserved │  │ inventory.failed
                         ▼  ▼
            ┌────────────────────┐
            │  Payment Service   │
            │ (Processa Pagto)   │
            └─────────┬──────────┘
         payment.completed │ payment.failed
                          ▼
            ┌────────────────────┐
            │ Notification Svc   │
            │ (Emails / Alerts)  │
            └────────────────────┘



