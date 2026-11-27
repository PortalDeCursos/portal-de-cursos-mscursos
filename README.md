# Microsserviço de Cursos

Microsserviço do **Portal de Cursos** responsável pela gestão da estrutura acadêmica da instituição: **Campus**, **Áreas do Conhecimento**, **Cursos** e **Disciplinas**.

## Tecnologias

| Tecnologia | Versão | Para que serve |
|---|---|---|
| Java | 21 | Linguagem |
| Spring Boot | 4.0.8 | Framework principal |
| Spring Data JPA | - | Acesso ao banco |
| Spring Validation | - | Validação dos dados de entrada |
| Spring Web MVC | - | API REST |
| PostgreSQL | - | Banco de dados |
| Flyway | - | Migrations do banco |
| MapStruct | 1.6.3 | Conversão entre Entity e DTO |
| Lombok | - | Redução de código repetitivo |
| SpringDoc OpenAPI | 3.1.0 | Documentação Swagger |
| JUnit 5 | - | Testes unitários e de integração |
| Mockito | - | Mocks nos testes unitários |
| AssertJ | - | Asserções nos testes |
| MockMvc | - | Simulação de requisições HTTP nos testes de integração |
| JaCoCo | 0.8.14 | Relatório de cobertura de testes |

## Pré-requisitos

- Java 21
- PostgreSQL em execução
- Dois bancos **vazios** criados no PostgreSQL:
    - `db_cursos`: banco da aplicação
    - `db_cursos_test`: banco usado pelos testes de integração

As tabelas e os dados iniciais (seed) são criados automaticamente pelo Flyway.

## Como rodar

### 1. Configure o `.env`

Copie o arquivo `.env.example` para `.env` na **raiz do projeto** e preencha com as suas credenciais:

```env
DB_URL=jdbc:postgresql://localhost:5432/nome_do_banco
DB_USERNAME=usuario
DB_PASSWORD=senha
SERVER_PORT=8082
```

O `application.yaml` importa o `.env` automaticamente (`spring.config.import`), então **não é necessário** configurar plugin nem variáveis no IntelliJ. Basta rodar a aplicação a partir da raiz do projeto.

### 2. Suba a aplicação

Rode a classe `CursosApplication` pelo IntelliJ ou use:

```bash
./mvnw spring-boot:run
```

### 3. Acesse o Swagger

Com a aplicação rodando: http://localhost:8082/api/academico/swagger-ui/index.html

> Todas as rotas têm o prefixo `/api/academico` (definido em `server.servlet.context-path`).

## Testes

O projeto possui:

- **Testes unitários** dos services (`CampusServiceTest`, `AreaConhecimentoServiceTest`, `CursoServiceTest` e `DisciplinaServiceTest`), com Mockito. Não sobem o Spring nem usam banco.
- **Testes de integração** (`CursoCadastroIntegrationTest` e `CursoConsultaIntegrationTest`), que sobem a aplicação completa e testam as funcionalidades de cadastrar e consultar cursos com MockMvc e banco real.

### Pré-requisitos dos testes de integração

- PostgreSQL em execução e o banco `db_cursos_test` criado (vazio).
- O arquivo `.env` na raiz do projeto (o usuário e a senha são os mesmos da aplicação).
- O perfil `test` (`src/test/resources/application-test.yaml`) aponta automaticamente para o `db_cursos_test`. O Flyway cria as tabelas e o seed nesse banco na primeira execução, e cada teste desfaz suas alterações ao final (rollback).
- Se alguma migration for alterada, **apague e recrie** o `db_cursos_test` para o Flyway aplicar tudo do zero.
- Os testes de integração usam dados do seed (campus `IFNC` e curso `TADS`).

### Comandos

| O que rodar | Comando |
|---|---|
| Todos os testes (unitários + integração) e gerar o relatório | `./mvnw clean test` |
| Só os testes unitários | `./mvnw test -Dtest="*ServiceTest"` |
| Só os testes de integração | `./mvnw test -Dtest="*IntegrationTest"` |
| Uma classe específica | `./mvnw test -Dtest=CursoServiceTest` |

> No Windows (CMD), use `mvnw.cmd` no lugar de `./mvnw`. No PowerShell e no Git Bash, `./mvnw` funciona normalmente.

### `mvn` ou `./mvnw`?

| Comando | Qual Maven usa |
|---|---|
| `mvn` | O Maven **instalado na máquina** (precisa estar instalado e no PATH) |
| `./mvnw` | O **Maven Wrapper**, que vem junto com o projeto e baixa a versão correta do Maven automaticamente |

Recomendamos o `./mvnw`: funciona sem instalar o Maven (basta ter o Java) e garante que todos usem a mesma versão. Se você já tem o Maven instalado, `mvn clean test` também funciona.

### Relatório de cobertura (JaCoCo)

O relatório é gerado automaticamente ao rodar os testes. Abra no navegador:

```
target/site/jacoco/index.html
```

## Migrations (Flyway)

Os scripts SQL ficam em `src/main/resources/db/migration/`, no padrão:

```
V1__create_tables.sql
V2__add_nova_coluna.sql
```

- **Não editar** uma migration que já foi aplicada. Crie uma nova (`V2`, `V3`...).
- **Não criar tabelas pelo pgAdmin.** Toda alteração no banco passa por uma migration.
- O Hibernate está com `ddl-auto: validate`: ele apenas confere se as entidades batem com o banco.