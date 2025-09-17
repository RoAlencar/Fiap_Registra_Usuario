<br>
<h1 align="center">
Fiap Registro de Usuário API
</h1>
<br>

## 💬 Sobre o repositório

Este repositório faz parte do projeto de desenvolvimento de um sistema de gestão unificado para restaurantes, criado em parceria com estudantes como solução colaborativa para reduzir custos e otimizar processos.

Este módulo tem como objetivo fornecer a base para **autenticação e gerenciamento de usuários**, permitindo que clientes e administradores possam futuramente interagir de forma segura com as demais funcionalidades da plataforma.

A construção do projeto segue uma **abordagem por fases**, garantindo:

- Evolução gradual das funcionalidades;
- Flexibilidade para ajustes conforme feedback dos restaurantes e clientes;
- Escalabilidade para suportar novos módulos (pedidos online, avaliações, gestão de cardápio, etc.).

---

## 🏗️ Funcionalidades implementadas

- Camada de **Domain** (`Usuario` e `Endereco`) e **Entity** (`UsuarioEntity` com `Endereco` embutido);
- **GlobalExceptionHandler** com exceptions customizadas;
- **CRUD de Usuário**:
  - Criar usuário
  - Buscar usuário por Nome
  - Atualizar usuário
  - Deletar usuário
- Validação de e-mail único no cadastro/atualização;
- **Swagger/OpenAPI** configurado para documentação e testes interativos da API;
- **Docker Compose** com PostgreSQL totalmente funcional;
- Configuração de datasource via `application.yml` para integração com o banco de dados.

---

## 💻 Swagger / OpenAPI

A API está **documentada com Swagger**, permitindo que você:

- Consulte todos os endpoints disponíveis;
- Veja os parâmetros e tipos esperados;
- Teste requisições diretamente pelo navegador.

Acesse o Swagger UI em:  
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## ⚙️ Pré-requisitos

- Java 21
- Maven
- Docker & Docker Compose
- PostgreSQL (via Docker Compose)

---

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

## 📝 Observações importantes

- O método **findByName** do repositório JPA foi atualizado para **findByNome** para refletir corretamente o campo da entidade **UsuarioEntity**.
- Use case e controller foram ajustados para validação de duplicidade de e-mail
- Swagger atualizado com a versão mais recente do **springdoc-openapi**.