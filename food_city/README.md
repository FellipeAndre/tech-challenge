# FoodCity - Sistema de Gestão de Restaurantes

## 📋 Sobre o Projeto

FoodCity é um sistema robusto de gestão para restaurantes, desenvolvido em **Spring Boot 3.5** com **Clean Architecture**. O sistema permite que múltiplos restaurantes gerenciem suas operações de forma centralizada, compartilhando uma infraestrutura única e escalável.

### Objetivos da Fase 02

Esta fase expande o sistema com:
- ✅ **Gestão de Tipos de Usuário** (Dono de Restaurante, Cliente, Funcionário)
- ✅ **Cadastro Completo de Restaurantes** (CRUD)
- ✅ **Cadastro de Itens de Cardápio** (CRUD)
- ✅ **Testes Automatizados** (80% cobertura)
- ✅ **Documentação da API**
- ✅ **Docker Compose** para execução integrada

---

## 🏗️ Arquitetura Clean Architecture

O projeto segue a arquitetura em camadas, garantindo separação de responsabilidades:

```
src/main/java/br/com/food_city/
├── domain/                    # Entidades de negócio
│   ├── entities/              # Classes de domínio
│   ├── repository/            # Interfaces dos repositórios
│   └── valueObject/           # Value Objects (Email, Senha, etc)
├── application/               # Lógica de aplicação
│   ├── usecase/               # Casos de uso
│   ├── dto/                   # Data Transfer Objects
│   └── mapper/                # Mapeadores DTOs
├── infrastructure/            # Implementações técnicas
│   ├── entity/                # Entidades JPA
│   ├── repository/            # Implementações dos repositórios
│   ├── gateway/               # Gateways de acesso a dados
│   └── mapper/                # Mapeadores de Entidades
├── presentation/              # Camada de apresentação
│   ├── controller/            # Controllers REST
│   └── dto/                   # DTOs de entrada/saída
├── config/                    # Configurações
├── exception/                 # Tratamento de exceções
└── FoodCityApplication.java   # Classe principal
```

---

## 🛠️ Stack Tecnológico

| Tecnologia | Versão | Propósito |
|-----------|--------|----------|
| Java | 21 | Linguagem principal |
| Spring Boot | 3.5.14 | Framework web |
| Spring Data JPA | 3.5.14 | ORM/Persistência |
| Spring Security | 3.5.14 | Segurança |
| PostgreSQL | 16 Alpine | Banco de dados |
| JUnit 5 | 5.10.2 | Testes unitários |
| Mockito | 5.11.0 | Mocks em testes |
| AssertJ | 3.25.3 | Assertions |
| Lombok | - | Reduz boilerplate |
| Maven | - | Gerenciador de dependências |

---

## 📦 Instalação e Setup

### Pré-requisitos

- Java 21+
- Maven 3.8+
- Docker & Docker Compose
- PostgreSQL 16 (ou use Docker Compose)

### 1. Clonar Repositório

```bash
git clone https://github.com/FellipeAndre/tech-challenge.git
cd tech-challenge/food_city
```

### 2. Configurar Banco de Dados

#### Opção A: Usar Docker Compose (Recomendado)

```bash
docker-compose up -d
```

Isso iniciará:
- **PostgreSQL**: `localhost:5432`
- **App Spring**: `http://localhost:8080`

#### Opção B: Banco Local

Crie o banco manualmente:

```sql
CREATE DATABASE food_city;
CREATE USER food_user WITH PASSWORD 'food_pass';
ALTER ROLE food_user WITH SUPERUSER;
```

Configure `application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/food_city
    username: food_user
    password: food_pass
  jpa:
    hibernate:
      ddl-auto: update
```

### 3. Build e Execução

```bash
# Compilar projeto
mvn clean install

# Executar localmente
mvn spring-boot:run

# Executar testes
mvn test

# Gerar relatório de cobertura
mvn test jacoco:report
```

### 4. Verificar Saúde da Aplicação

```bash
curl http://localhost:8080/actuator/health
```

---

## 📚 Endpoints da API

### Autenticação

A aplicação usa Spring Security. Configure conforme necessário para seu ambiente de teste.

### Tipos de Usuário

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/buscar-usuario` | Cadastrar novo tipo de usuário |
| `GET` | `/buscar-usuario` | Listar todos os tipos |
| `GET` | `/buscar-usuario/{id}` | Buscar tipo por ID |
| `PUT` | `/buscar-usuario/{id}` | Atualizar tipo de usuário |
| `DELETE` | `/buscar-usuario/{id}` | Remover tipo de usuário |

**Request de Cadastro:**

```json
{
  "idDono": "550e8400-e29b-41d4-a716-446655440000",
  "id": "550e8400-e29b-41d4-a716-446655440001",
  "role": "ADMIN"
}
```

### Restaurantes

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/v1/restaurantes/cadastrar` | Cadastrar novo restaurante |
| `GET` | `/v1/restaurantes` | Listar todos os restaurantes |
| `GET` | `/v1/restaurantes/{id}` | Buscar restaurante por ID |
| `PUT` | `/v1/restaurantes/{id}` | Atualizar restaurante |
| `DELETE` | `/v1/restaurantes/{id}` | Remover restaurante |

**Request de Cadastro:**

```json
{
  "nome": "Pizzaria Italia",
  "tipoCozinha": "Italiana",
  "horarioAbertura": "11:00",
  "horarioFechamento": "23:00",
  "donoId": "550e8400-e29b-41d4-a716-446655440000",
  "endereco": {
    "logradouro": "Rua das Flores",
    "numero": 123,
    "bairro": "Centro",
    "municipio": "São Paulo",
    "estado": "SP"
  }
}
```

### Cadastro de Usuários

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/v1/cadastrar/dono` | Cadastrar dono de restaurante |
| `POST` | `/v1/cadastrar/cliente` | Cadastrar cliente |
| `POST` | `/v1/cadastrar/funcionario` | Cadastrar funcionário |

**Request:**

```json
{
  "nome": "João Silva",
  "email": "joao@example.com",
  "numeroDocumento": "12345678900",
  "dataNascimento": "1990-01-15",
  "usuario": {
    "login": "joao_silva",
    "senha": "SenhaSegura@123"
  },
  "enderecoDTO": {
    "logradouro": "Rua A",
    "numero": 100,
    "bairro": "Bairro A",
    "municipio": "São Paulo",
    "estado": "SP"
  }
}
```

---

## 🧪 Testes

### Estrutura de Testes

```
src/test/java/br/com/food_city/
├── application/usecase/           # Testes unitários (Use Cases)
│   ├── cadastro/
│   ├── restaurante/
│   └── tipoUsuario/
├── infrastructure/                # Testes de integração
│   ├── repository/
│   └── gateway/
└── mock/                          # Helpers de mocks
```

### Executar Testes

```bash
# Todos os testes
mvn test

# Testes específicos
mvn test -Dtest=CadastrarRestauranteUseCaseTest

# Com cobertura
mvn test jacoco:report
# Relatório em: target/site/jacoco/index.html
```

### Cobertura de Testes

O projeto mantém **80% de cobertura** incluindo:

- ✅ Testes de Use Cases (application layer)
- ✅ Testes de Repositórios (infrastructure layer)
- ✅ Testes de casos de sucesso e erro
- ✅ Testes de validação

---

## 🐳 Docker Compose

### Arquivo: docker-compose.yaml

```yaml
services:
  postgres:
    image: postgres:16-alpine
    container_name: food_city_db
    environment:
      POSTGRES_DB: food_city
      POSTGRES_USER: food_user
      POSTGRES_PASSWORD: food_pass
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U food_user -d food_city"]
      interval: 10s
      timeout: 5s
      retries: 5

  app:
    build:
      context: .
      dockerfile: Dockerfile
    container_name: food_city_app
    ports:
      - "8080:8080"
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/food_city
      SPRING_DATASOURCE_USERNAME: food_user
      SPRING_DATASOURCE_PASSWORD: food_pass
      SPRING_JPA_HIBERNATE_DDL_AUTO: update
    depends_on:
      postgres:
        condition: service_healthy
    restart: on-failure

volumes:
  postgres_data:
```

### Usar Docker Compose

```bash
# Iniciar
docker-compose up -d

# Ver logs
docker-compose logs -f app

# Parar
docker-compose down

# Limpar dados
docker-compose down -v
```

---

## 📋 Collections Postman

Importe a collection `FoodCity.postman_collection.json` no Postman para testar todos os endpoints.

### Como usar:

1. Abra Postman
2. **File → Import → Upload Files**
3. Selecione `FoodCity.postman_collection.json`
4. Use as variáveis de ambiente
5. Teste os endpoints

---

## 🔐 Segurança

- ✅ Senhas criptografadas com BCrypt
- ✅ Validação de entrada em todos os endpoints
- ✅ Spring Security configurado
- ✅ Value Objects para proteção de dados sensíveis (Email, Documento, Telefone)

---

## 📝 Variáveis de Ambiente

Configure em `application.yml` ou via Docker:

```yaml
spring:
  datasource:
    url: ${SPRING_DATASOURCE_URL:jdbc:postgresql://localhost:5432/food_city}
    username: ${SPRING_DATASOURCE_USERNAME:food_user}
    password: ${SPRING_DATASOURCE_PASSWORD:food_pass}
  jpa:
    hibernate:
      ddl-auto: ${SPRING_JPA_HIBERNATE_DDL_AUTO:update}
```

---

## 🚀 Próximas Fases

- [ ] **Fase 03**: Pedidos e entregas
- [ ] **Fase 04**: Pagamentos e integrações
- [ ] **Fase 05**: Relatórios e analytics

---

## 👨‍💻 Desenvolvedor

**Felipe Tobias Andre**

## Repositório

**https://github.com/FellipeAndre/tech-challenge**

---

## 📄 Licença

Este projeto é parte do Tech Challenge - FIAP

---

## 🆘 Troubleshooting

### Problema: Conexão recusada ao banco
```bash
# Verifique se o PostgreSQL está rodando
docker-compose logs postgres

# Reinicie os containers
docker-compose restart
```

### Problema: Porta 8080 já em uso
```bash
# Mude a porta no docker-compose.yaml
ports:
  - "8081:8080"
```

### Problema: Build falha
```bash
# Limpe cache Maven
mvn clean

# Reinstale dependências
mvn install
```

---

**Última atualização**: Julho de 2026
