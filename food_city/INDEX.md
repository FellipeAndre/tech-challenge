# 📚 Índice de Documentação - FoodCity

Bem-vindo ao FoodCity! Este arquivo organiza toda a documentação do projeto.

---

## 🚀 Início Rápido

### Para usuários de Windows
```bash
.\start.bat
```

### Para usuários de Linux/Mac
```bash
./start.sh
```

### Manual
```bash
docker-compose up -d
mvnw clean install
```

---

## 📖 Documentação Principal

### 1. **README.md** - Guia Geral
- 👉 [Ler README.md](README.md)
- **Conteúdo**:
  - ✅ Sobre o projeto
  - ✅ Stack tecnológico
  - ✅ Instalação e setup
  - ✅ Endpoints da API
  - ✅ Como executar testes
  - ✅ Docker Compose
  - ✅ Troubleshooting

**Para**: Primeiro contato, setup do ambiente, referência rápida

---

### 2. **ARCHITECTURE.md** - Arquitetura Técnica
- 👉 [Ler ARCHITECTURE.md](ARCHITECTURE.md)
- **Conteúdo**:
  - ✅ Clean Architecture explicada
  - ✅ Camadas: Domain, Application, Infrastructure, Presentation
  - ✅ Fluxo de dados
  - ✅ Padrões de design
  - ✅ Estratégia de testes
  - ✅ Segurança e escalabilidade

**Para**: Entender a estrutura do projeto, decidir onde adicionar código

---

### 3. **CONTRIBUTING.md** - Guia do Desenvolvedor
- 👉 [Ler CONTRIBUTING.md](CONTRIBUTING.md)
- **Conteúdo**:
  - ✅ Setup do ambiente de desenvolvimento
  - ✅ Como criar nova funcionalidade (passo a passo)
  - ✅ Padrões de código
  - ✅ Fluxo de Git/GitHub
  - ✅ Exemplos de código
  - ✅ Recursos úteis

**Para**: Contribuir com novo código, seguir padrões, fazer pull requests

---

### 4. **ENTREGA.md** - Checklist de Entrega
- 👉 [Ler ENTREGA.md](ENTREGA.md)
- **Conteúdo**:
  - ✅ Status de conclusão
  - ✅ Detalhes de cada funcionalidade
  - ✅ Endpoints implementados
  - ✅ Arquivos entregues
  - ✅ Cobertura de testes
  - ✅ Como validar

**Para**: Verificar o que foi entregue, validar funcionalidades

---

## 🔧 Configuração e Execução

### Docker Compose
- 👉 [Ver docker-compose.yaml](docker-compose.yaml)
- Inicia PostgreSQL + App Spring Boot automaticamente
- Configuração pronta para produção

```bash
# Iniciar
docker-compose up -d

# Parar
docker-compose down

# Ver logs
docker-compose logs -f app
```

---

## 🧪 Testes e Qualidade

### Estrutura de Testes
```
src/test/java/br/com/food_city/
├── application/usecase/    # Unit Tests
├── infrastructure/        # Integration Tests
└── mock/                  # Test Helpers
```

### Executar Testes
```bash
# Todos
mvnw test

# Específico
mvnw test -Dtest=NomeTestClass

# Com cobertura (80%)
mvnw test jacoco:report
```

### Relatório de Cobertura
Após executar `mvnw test jacoco:report`:
```
target/site/jacoco/index.html
```

---

## 🌐 API - Endpoints

### Tipos de Usuário
```
POST   /buscar-usuario           # Cadastrar
GET    /buscar-usuario           # Listar
GET    /buscar-usuario/{id}      # Buscar
PUT    /buscar-usuario/{id}      # Atualizar
DELETE /buscar-usuario/{id}      # Remover
```

### Restaurantes
```
POST   /v1/restaurantes/cadastrar    # Cadastrar
GET    /v1/restaurantes              # Listar
GET    /v1/restaurantes/{id}         # Buscar
PUT    /v1/restaurantes/{id}         # Atualizar
DELETE /v1/restaurantes/{id}         # Remover
```

### Cadastro de Usuários
```
POST   /v1/cadastrar/dono         # Cadastrar dono
POST   /v1/cadastrar/cliente      # Cadastrar cliente
POST   /v1/cadastrar/funcionario  # Cadastrar funcionário
```

---

## 📮 Collections para Testes

### Postman/Insomnia
- 👉 [FoodCity.postman_collection.json](FoodCity.postman_collection.json)

**Como usar**:
1. Abra Postman/Insomnia
2. File → Import
3. Selecione `FoodCity.postman_collection.json`
4. Teste os endpoints

---

## 📊 Status do Projeto

| Componente | Status | Documentação |
|-----------|--------|--------------|
| Funcionalidades | ✅ COMPLETO | [ENTREGA.md](ENTREGA.md) |
| Arquitetura | ✅ COMPLETO | [ARCHITECTURE.md](ARCHITECTURE.md) |
| Documentação | ✅ COMPLETO | Você está aqui |
| Testes Unitários | ⏳ 80%+ | [README.md](README.md) |
| Docker | ✅ COMPLETO | [docker-compose.yaml](docker-compose.yaml) |
| Collections | ✅ COMPLETO | [FoodCity.postman_collection.json](FoodCity.postman_collection.json) |

---

## 📁 Estrutura de Diretórios

```
food_city/
├── src/
│   ├── main/
│   │   ├── java/br/com/food_city/
│   │   │   ├── application/        # Use Cases, DTOs, Mappers
│   │   │   ├── domain/             # Entities, Value Objects, Repositories
│   │   │   ├── infrastructure/     # JPA Entities, Repository Implementations
│   │   │   ├── presentation/       # Controllers, DTOs
│   │   │   ├── config/             # Configurações
│   │   │   ├── exception/          # Exceções customizadas
│   │   │   └── FoodCityApplication.java
│   │   └── resources/
│   │       ├── application.yml
│   │       ├── application-test.yml
│   │       └── application-dev.yml
│   └── test/
│       └── java/br/com/food_city/
│           ├── application/usecase/   # Unit Tests
│           ├── infrastructure/        # Integration Tests
│           └── mock/                  # Test Helpers
├── Dockerfile
├── docker-compose.yaml
├── pom.xml
├── README.md                  # Guia geral
├── ARCHITECTURE.md            # Arquitetura técnica
├── CONTRIBUTING.md            # Guia do desenvolvedor
├── ENTREGA.md                # Checklist de entrega
├── INDEX.md                  # Este arquivo
├── FoodCity.postman_collection.json  # Endpoints para teste
├── start.sh                  # Script inicialização (Linux/Mac)
├── start.bat                 # Script inicialização (Windows)
└── target/                   # Build output (não commitado)
```

---

## 🎯 Quick Navigation

### Se você quer...

**Configurar o ambiente**
→ [README.md](README.md) - Seção "Instalação"

**Entender como o projeto é organizado**
→ [ARCHITECTURE.md](ARCHITECTURE.md)

**Adicionar uma nova funcionalidade**
→ [CONTRIBUTING.md](CONTRIBUTING.md) - Seção "Criar Nova Funcionalidade"

**Verificar o que foi entregue**
→ [ENTREGA.md](ENTREGA.md)

**Testar os endpoints**
→ Importe [FoodCity.postman_collection.json](FoodCity.postman_collection.json) no Postman

**Usar Docker Compose**
→ [docker-compose.yaml](docker-compose.yaml)

**Executar testes**
→ [README.md](README.md) - Seção "Testes"

**Entender as exceções e tratamento de erros**
→ [ARCHITECTURE.md](ARCHITECTURE.md) - Seção "Segurança"

---

## 🔗 Links Úteis

### Documentação Externa
- [Spring Boot 3.5](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Spring Security](https://spring.io/projects/spring-security)
- [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
- [JUnit 5](https://junit.org/junit5/)
- [Mockito](https://site.mockito.org/)

### Repositório GitHub
- [tech-challenge](https://github.com/FellipeAndre/tech-challenge)

---

## 📞 Dúvidas Frequentes

**P: Por onde começo?**
R: Leia o [README.md](README.md), execute `./start.bat` ou `./start.sh`, e teste com Postman.

**P: Como contribuir?**
R: Siga o [CONTRIBUTING.md](CONTRIBUTING.md).

**P: Qual é a arquitetura do projeto?**
R: Veja [ARCHITECTURE.md](ARCHITECTURE.md).

**P: Como executar testes?**
R: Veja [README.md](README.md) - Seção "Testes".

**P: O projeto está completo?**
R: Veja [ENTREGA.md](ENTREGA.md) - Seção "Status de Conclusão".

---

## 🚀 Próximos Passos

1. ✅ Execute o setup (`./start.bat` ou `./start.sh`)
2. ✅ Importe a collection Postman
3. ✅ Teste alguns endpoints
4. ✅ Leia ARCHITECTURE.md para entender o projeto
5. ✅ Explore o código em src/main/java
6. ✅ Execute os testes com `mvnw test`

---

## 👨‍💻 Informações do Projeto

- **Projeto**: FoodCity - Tech Challenge FIAP
- **Fase**: 02
- **Desenvolvedor**: Mauricio Borges Florencio
- **Tecnologia**: Spring Boot 3.5.14 + PostgreSQL
- **Arquitetura**: Clean Architecture
- **Testes**: 80%+ de cobertura

---

**Última atualização**: Julho 2026

> 💡 Dica: Use os search dos seus editores de código para encontrar rapidamente arquivos e classes!

---

[Voltar ao README](README.md)
