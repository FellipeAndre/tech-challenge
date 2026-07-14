# Arquitetura FoodCity - Clean Architecture

## 📐 Visão Geral

O FoodCity foi desenvolvido seguindo os princípios da **Clean Architecture**, uma arquitetura de software que prioriza:

- ✅ Independência de frameworks
- ✅ Testabilidade
- ✅ Independência de UI
- ✅ Independência de banco de dados
- ✅ Independência de qualquer agente externo

```
┌─────────────────────────────────────────────────────────────┐
│                    PRESENTATION LAYER                       │
│              Controllers, DTOs de entrada/saída             │
└─────────────────────────────────────────────────────────────┘
                              ↓
┌─────────────────────────────────────────────────────────────┐
│                  APPLICATION LAYER                          │
│         Use Cases, DTOs, Mappers, Business Logic           │
└─────────────────────────────────────────────────────────────┘
                              ↓
┌─────────────────────────────────────────────────────────────┐
│                     DOMAIN LAYER                            │
│        Entities, Value Objects, Repository Interfaces      │
└─────────────────────────────────────────────────────────────┘
                              ↓
┌─────────────────────────────────────────────────────────────┐
│                 INFRASTRUCTURE LAYER                        │
│         Repository Implementations, Gateways, Mappers      │
└─────────────────────────────────────────────────────────────┘
```

---

## 🎯 Camadas Detalhadas

### 1. **PRESENTATION LAYER** (Apresentação)

**Responsabilidade**: Receber requisições HTTP e formatá-las para o sistema

**Localização**: `src/main/java/br/com/food_city/presentation/`

**Componentes**:

#### Controllers
- `CadastroController`: Gerencia cadastro de usuários (dono, cliente, funcionário)
- `RestauranteController`: Gerencia CRUD de restaurantes
- `TipoUsuarioController`: Gerencia tipos de usuários

#### DTOs (Data Transfer Objects)
- `CadastroRequest`: DTO de entrada para cadastro de usuários
- `RestauranteComercioRequest`: DTO para cadastro de restaurantes
- `TipoUsuarioRequest`: DTO para tipos de usuário

**Padrão**: Recebe requests, converte para DTOs, chama Use Cases, retorna responses

```java
@PostMapping("/v1/restaurantes/cadastrar")
public ResponseEntity<?> cadastrar(@Valid @RequestBody RestauranteComercioRequest request) {
    var input = toInput(request);  // Converte para DTO de aplicação
    var output = cadastrarUseCase.executar(input);
    return ResponseEntity.status(HttpStatus.CREATED).body(output);
}
```

---

### 2. **APPLICATION LAYER** (Aplicação)

**Responsabilidade**: Orquestrar a lógica de negócio através de Use Cases

**Localização**: `src/main/java/br/com/food_city/application/`

**Componentes**:

#### Use Cases (Casos de Uso)
Cada Use Case implementa uma ação específica do negócio:

```
├── cadastro/
│   ├── SalvarCadastroUseCase
│   ├── AtualizarCadastroUseCase
│   ├── BuscarCadastroUseCase
│   ├── ListarCadastroUseCase
│   └── RemoverCadastroUseCase
├── restaurante/
│   ├── CadastrarRestauranteUseCase
│   ├── AtualizarRestauranteUseCase
│   ├── BuscarRestauranteUseCase
│   ├── ListarRestauranteUseCase
│   └── RemoverRestauranteUseCase
├── tipoUsuario/
│   ├── CadastrarTipoUsuarioUseCase
│   ├── AtualizarTipoUsuarioUseCase
│   ├── BuscarTipoUsuarioUseCase
│   ├── ListarTipoUsuarioUseCase
│   └── RemoverTipoUsuarioUseCase
└── usuario/
    ├── AtualizarUsuarioUseCase
    ├── BuscarUsuarioUseCase
    ├── ListarUsuarioUseCase
    └── RemoverUsuarioUseCase
```

#### DTOs de Aplicação
- `CadastroInput/Output`: Dados de entrada/saída para cadastro
- `RestauranteInput/Output`: Dados de entrada/saída para restaurantes
- `TipoUsuarioInput/Output`: Dados de entrada/saída para tipos

#### Mappers
Convertem entre camadas:
- `CadastroUsecaseMapper`: DTOs apresentação ↔ DTOs aplicação
- `RestauranteUsecaseMapper`: DTOs apresentação ↔ DTOs aplicação

**Padrão**: Use Cases são injetados com dependências, executam validações, orquestram operações

```java
@Service
public class CadastrarRestauranteUseCase {
    private final RestauranteRepository repository;
    private final UsuarioRepository usuarioRepository;
    
    public RestauranteOutput executar(RestauranteInput input) {
        validarDono(input.getDonoId());
        var restaurante = new Restaurante(...);
        var restauranteSalvo = repository.save(restaurante);
        return mapper.toOutput(restauranteSalvo);
    }
}
```

---

### 3. **DOMAIN LAYER** (Domínio)

**Responsabilidade**: Definir as entidades de negócio e regras de domínio

**Localização**: `src/main/java/br/com/food_city/domain/`

**Componentes**:

#### Entities (Entidades de Domínio)
Classes que representam conceitos de negócio:

- `Restaurante`: Restaurante com validações de horário e endereço
- `Usuario`: Usuário do sistema com login e senha
- `TipoUsuario`: Tipos de usuário (PROPRIETARIO, CLIENTE, FUNCIONARIO)
- `Cadastro`: Cadastro de pessoa física
- `EnderecoDomain`: Endereço do cliente/dono

**Características**:
- Contêm validações de negócio
- Não dependem de frameworks
- Usam apenas Java puro

```java
public class Restaurante {
    private void validar(String nome, String tipoCozinha, EnderecoDomain endereco, 
                         LocalTime abertura, LocalTime fechamento, UUID donoId) {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome obrigatório");
        if (!abertura.isBefore(fechamento))
            throw new IllegalArgumentException("Abertura antes de fechamento");
    }
}
```

#### Value Objects
Objetos que representam conceitos com valor imutável:

- `Email`: Valida formato de email
- `Documento`: Valida CPF/CNPJ
- `Telefone`: Formata e valida telefone
- `Senha`: Hash seguro com BCrypt

```java
public class Email {
    private final String valor;
    
    public Email(String valor) {
        if (!isValido(valor))
            throw new IllegalArgumentException("Email inválido");
        this.valor = valor;
    }
}
```

#### Repository Interfaces
Contratos para acesso a dados (implementados na Infrastructure):

```java
public interface RestauranteRepository {
    RestauranteEntity save(Restaurante restaurante);
    Optional<RestauranteEntity> findById(UUID id);
    List<RestauranteEntity> findAll();
    void delete(UUID id);
}
```

**Princípio**: Domain não depende de nada, apenas de si mesmo.

---

### 4. **INFRASTRUCTURE LAYER** (Infraestrutura)

**Responsabilidade**: Implementar detalhes técnicos de persistência

**Localização**: `src/main/java/br/com/food_city/infrastructure/`

**Componentes**:

#### Entities JPA
Mapeamento para banco de dados:

```java
@Entity
@Table(name = "tb_restaurante")
public class RestauranteEntity {
    @Id
    private UUID id;
    
    @Column(nullable = false)
    private String nome;
    
    @Embedded
    private EnderecoEmbeddable endereco;
}
```

#### Repositories (Implementações)
Implementam interfaces do Domain usando Spring Data JPA:

```java
@Repository
public class RestauranteRepositoryImpl extends JpaRepository<RestauranteEntity, UUID> 
    implements RestauranteRepository {
    
    @Override
    public RestauranteEntity save(Restaurante restaurante) {
        var entity = infraMapper.toEntity(restaurante);
        return jpaRepository.save(entity);
    }
}
```

#### Gateways
Implementações de acesso a recursos:

- `CadastrarGateway`: Acesso a operações de cadastro
- `RestauranteGateway`: Acesso a operações de restaurante
- `TipoUsuarioGateway`: Acesso a operações de tipo usuário

#### Mappers de Infraestrutura
Convertem Domain ↔ Entities JPA:

```java
@Component
public class RestauranteInfraMapper {
    public RestauranteEntity toEntity(Restaurante restaurante) {
        return new RestauranteEntity(
            restaurante.getId(),
            restaurante.getNome(),
            ...
        );
    }
}
```

---

## 🔄 Fluxo de Dados

### Exemplo: Cadastrar Restaurante

```
1. POST /v1/restaurantes/cadastrar
   ↓
2. RestauranteController.cadastrar()
   - Recebe RestauranteComercioRequest
   - Converte para RestauranteInput
   ↓
3. CadastrarRestauranteUseCase.executar()
   - Valida entrada
   - Busca usuário dono no repositório
   - Cria entidade Restaurante (domain)
   - Chama repository.save()
   ↓
4. RestauranteRepositoryImpl.save()
   - Mapeia Restaurante (domain) → RestauranteEntity (JPA)
   - Persiste no banco via JpaRepository
   ↓
5. Mapeia RestauranteEntity → RestauranteOutput
   ↓
6. Retorna ResponseEntity com dados cadastrados
```

---

## 📊 Diagrama de Dependências

```
Presentation
    ↓ (depends on)
Application
    ↓ (depends on)
Domain
    ↑ (is implemented by)
Infrastructure
```

**Regra de Ouro**: Camadas internas NUNCA dependem de camadas externas.

---

## 🧪 Testes por Camada

### Application Layer (Unit Tests)
- Testes de Use Cases com mocks de repositório
- Localização: `src/test/java/br/com/food_city/application/usecase/`

```java
@ExtendWith(MockitoExtension.class)
class CadastrarRestauranteUseCaseTest {
    @Mock
    private RestauranteRepository repository;
    
    @InjectMocks
    private CadastrarRestauranteUseCase useCase;
    
    @Test
    void deveResponderErroQuandoDonoNaoExiste() {
        // Arrange
        var input = new RestauranteInput(...);
        when(usuarioRepository.findById(input.getDonoId()))
            .thenReturn(Optional.empty());
        
        // Act & Assert
        assertThrows(UsuarioNaoEncontradoException.class, 
            () -> useCase.executar(input));
    }
}
```

### Infrastructure Layer (Integration Tests)
- Testes de repositório com banco de dados
- Localização: `src/test/java/br/com/food_city/infrastructure/`

```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RestauranteRepositoryIntegrationTest {
    @Autowired
    private RestauranteRepository repository;
    
    @Test
    void deveSalvarERecuperarRestaurante() {
        // Arrange
        var restaurante = new Restaurante(...);
        
        // Act
        var salvo = repository.save(restaurante);
        var recuperado = repository.findById(salvo.getId());
        
        // Assert
        assertThat(recuperado).isNotEmpty();
    }
}
```

---

## 🔐 Segurança

- **Camada Presentation**: Spring Security com validação de entrada
- **Camada Application**: Lógica de autorização nos Use Cases
- **Camada Domain**: Value Objects para dados sensíveis (Senha, Email)

---

## 📈 Escalabilidade

A arquitetura permite:

1. **Adicionar novos Use Cases** sem afetar camadas externas
2. **Mudar de banco de dados** alterando apenas Infrastructure
3. **Testar independentemente** cada camada
4. **Reutilizar Domain** em diferentes interfaces (CLI, API, etc)

---

## 🛠️ Ferramentas e Padrões

| Aspecto | Padrão | Ferramenta |
|--------|--------|-----------|
| Injeção de dependências | Dependency Injection | Spring |
| Persistência | Repository Pattern | Spring Data JPA |
| Mapeamento | Mapper Pattern | Lombok |
| Validação | Domain Validation | Custom validators |
| Testes | AAA Pattern | JUnit 5 + Mockito |
| Cobertura | Code Coverage | JaCoCo |

---

**Desenvolvido com 💙 seguindo Clean Architecture principles**
