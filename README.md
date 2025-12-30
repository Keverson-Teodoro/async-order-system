# async-order-system

> Sistema distribuído construído com **Spring Boot**, **RabbitMQ** e **bancos de dados independentes**, demonstrando uma arquitetura **orientada a eventos (Event-Driven)** entre múltiplos microserviços.

---

- 🚀 Visão Geral

Este projeto simula o fluxo de **pedidos de compra online**, utilizando comunicação síncrona e assíncrona entre diferentes microserviços.

Cada serviço é responsável por um contexto de negócio isolado e se comunica via **mensageria RabbitMQ** e HTTP/REST.  
A arquitetura reflete um cenário de sistemas desacoplados.

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

🧠 Arquitetura

A arquitetura do sistema combina comunicação síncrona (HTTP/REST) e arquitetura orientada a eventos (Event-Driven) para garantir consistência, escalabilidade e desacoplamento entre os microserviços.

O Order Service atua como API Gateway do fluxo de pedidos, orquestrando validações iniciais de forma síncrona e, posteriormente, publicando eventos para processamento assíncrono.

🔄 Fluxo de Comunicação

O Order Service recebe a requisição para criação de um novo pedido.

De forma síncrona, o Order Service:

Comunica-se com o User Service para validar a existência do usuário.

Comunica-se com o Product Service para verificar a disponibilidade dos itens e reservar o estoque.

Após validações bem-sucedidas, o Order Service publica o evento order.created no RabbitMQ.

O Payment Service consome o evento order.created e realiza a validação do pagamento:

Em caso de sucesso, publica o evento payment.success.

Em caso de falha, publica o evento payment.failed.

O Product Service consome o evento payment.failed para realizar o rollback da quantidade em estoque.

O Notification Service consome o evento payment.success e envia um e-mail de confirmação ao usuário.

🧩 Diagrama da Arquitetura
                  ┌────────────────────┐
                  │   Client / API     │
                  └─────────┬──────────┘
                            │
                            ▼
                  ┌────────────────────┐
                  │   Order Service     │
                  │ (API Gateway)       │
                  └───────┬─────┬───────┘
          HTTP Sync        │     │        HTTP Sync
                           ▼     ▼
              ┌────────────────┐ ┌─────────────────┐
              │  User Service  │ │ Product Service │
              │ (Validação)    │ │ (Estoque)        │
              └────────────────┘ └─────────────────┘
                            │
                     order.created
                            ▼
                  ┌────────────────────┐
                  │  Payment Service   │
                  │ (Processa Pagto)   │
                  └───────┬────────────┘
          payment.success  │  payment.failed
                ▼          ▼
     ┌─────────────────┐ ┌────────────────────┐
     │ Notification    │ │ Product Service     │
     │ Service         │ │ (Rollback Estoque)  │
     │ (Email)         │ └────────────────────┘
     └─────────────────┘

📌 Considerações Arquiteturais

Comunicação Síncrona
Utilizada apenas nas validações críticas (usuário e estoque), garantindo consistência imediata antes da criação do pedido.

Comunicação Assíncrona (RabbitMQ)
Utilizada para processamento de pagamento, notificações e rollback de estoque, reduzindo acoplamento e aumentando resiliência.

Bancos de Dados Independentes
Cada microserviço possui seu próprio banco de dados, respeitando o princípio de Database per Service.
