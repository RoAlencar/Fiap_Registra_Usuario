<br>
<h1 align="center">
🍽️ Fiap Registro de Usuário API
</h1>
<br>

## 💬 Sobre o Projeto

Este repositório faz parte do **Tech Challenge - Fase 1** da pós-graduação em Arquitetura e Desenvolvimento Java (FIAP).

O projeto propõe o desenvolvimento de um **backend robusto** para o módulo de **autenticação e gerenciamento de usuários** de um sistema de gestão unificado para restaurantes — uma iniciativa colaborativa entre estabelecimentos para reduzir custos e otimizar processos operacionais.

Este módulo fornece as bases para que **clientes e administradores (donos de restaurante)** possam interagir de forma segura com a plataforma, garantindo controle, rastreabilidade e integridade dos dados.

A construção do projeto segue uma **abordagem por fases**, garantindo:

- Evolução gradual das funcionalidades;
- Flexibilidade para ajustes conforme feedback dos restaurantes e clientes;
- Escalabilidade para integração com novos módulos (pedidos online, avaliações, gestão de cardápio, etc.).

---

## 🏗️ Funcionalidades Implementadas

- **Camada de Domain e Entity:**
    - `Usuario` e `Endereco` como objetos de domínio;
    - `UsuarioEntity` com `Endereco` embutido;
- **GlobalExceptionHandler** com `ProblemDetail (RFC 7807)` e exceptions customizadas;
- **CRUD de Usuário:**
    - Criar usuário;
    - Buscar usuário por nome;
    - Atualizar usuário (endpoint distinto do de senha);
    - Deletar usuário;
- **Troca de senha** em endpoint separado;
- **Validação de login** com checagem de credenciais;
- **Validação de e-mail único** no cadastro/atualização;
- **Registro automático da data da última alteração**;
- **Versionamento de API** (`/api/v1/users`);
- **Swagger/OpenAPI** configurado para documentação e testes interativos da API;
- **Docker Compose** com PostgreSQL totalmente funcional.

---

## 💾 Estrutura do Banco de Dados

Banco de dados relacional **PostgreSQL**, executado via **Docker Compose**.

**Entidade principal:** `usuario`

| Campo | Tipo | Descrição |
|--------|------|------------|
| id | UUID | Identificador único |
| nome | String | Nome completo do usuário |
| email | String | E-mail único |
| login | String | Nome de usuário |
| senha | String | Senha criptografada |
| data_ultima_alteracao | Timestamp | Data da última atualização |
| endereco_rua | String | Rua |
| endereco_numero | String | Número |
| endereco_cidade | String | Cidade |
| endereco_cep | String | CEP |

---

## 🌐 Endpoints Principais

| Método | Endpoint              | Descrição |
|---------|-----------------------|-----------|
| `POST` | `/api/v1/users`       | Cadastrar novo usuário |
| `GET` | `/api/v1/users`       | Listar todos os usuários |
| `GET` | `/api/v1/users/{nome}` | Buscar usuário por nome |
| `PUT` | `/api/v1/users/{id}`  | Atualizar dados do usuário |
| `PATCH` | `/api/v1/users/senha` | Alterar senha |
| `DELETE` | `/api/v1/users/{id}`  | Deletar usuário |
| `POST` | `/api/v1/users/login` | Validar login e senha |

---

## 💻 Documentação Swagger

A API está **documentada com Swagger/OpenAPI**, permitindo visualizar e testar os endpoints diretamente no navegador.

Acesse:  
👉 [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## ⚙️ Pré-requisitos

- Java 21
- Maven
- Docker & Docker Compose
- PostgreSQL (via Docker Compose)

---

## 🚀 Como Rodar a Aplicação

1. **Suba o banco de dados via Docker Compose:**
```bash
   docker-compose up -d
````
## 🚀 Como rodar a aplicação

1. **Inicie o banco de dados** via Docker Compose:

```bash
docker-compose up -d
```

2. **Execute a aplicação**

```bash
mvn spring-boot:run
```
3. **Acesse a aplicação no navegador**

```arduino
http://localhost:8080/
```

---

## 🧪 Testes com Postman
O projeto inclui uma **coleção Postman** (postman_collection.json) cobrindo os principais cenários:

* Cadastro de usuário válido e inválido (e-mail duplicado, campos obrigatórios);
* Atualização de dados com sucesso e erro;
* Alteração de senha (endpoint exclusivo);
* Busca por nome;
* Validação de login.
 ---
## 🧱 Arquitetura da Aplicação
A arquitetura segue o padrão **Camadas (Domain, Application, Infrastructure)**, alinhada aos princípios de **SOLID**  e **Clean Architecture**.

```
src
├── main
│   ├── java
│   │   └── br.com.fiap.registro
│   │       ├── domain
│   │       ├── entity
│   │       ├── controller
│   │       ├── service
│   │       └── exception
│   └── resources
│       ├── application.yml
│       └── schema.sql
└── test
```
---
🧩 Tecnologias Utilizadas

* Java 21
* Spring Boot 3
* Spring Data JPA
* Swagger / OpenAPI
* PostgreSQL
* Docker & Docker Compose
* JUnit 5 (opcional)
* ProblemDetail RFC 7807