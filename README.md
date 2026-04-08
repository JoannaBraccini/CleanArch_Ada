# Clean Architecture

Projeto didático desenvolvido nas aulas da Ada (Projeto Elas +Tech) para praticar evolução de arquitetura em uma API de
usuários com Java + Spring Boot.

## Objetivo

Mostrar, na prática, a transição de uma estrutura mais simples e acoplada para uma estrutura baseada em **Clean
Architecture**, com foco em separação de responsabilidades.

## Contexto do projeto

Este repositório representa a base inicial da API e sua evolução para uma organização em camadas:

- `domain`: regras de negócio, entidades de domínio e casos de uso
- `infrastructure`: persistência, entidades JPA, mapeadores e implementações técnicas
- `presentation`: controllers e exposição HTTP

## Antes e depois da estruturação do domain

### Antes (estrutura mais acoplada)

- Regras de negócio misturadas com detalhes de framework/persistência
- Dificuldade para testar sem subir contexto completo
- Maior impacto de mudanças técnicas no código de regra de negócio
- Menor clareza sobre "quem faz o que"

### Depois (com foco em domain)

- Casos de uso no `domain/usecases` (ex.: criação, busca e exclusão de usuários)
- Entidade de domínio (`domain/User`) concentrando comportamento e validações
- Contratos de repositório no domínio (`domain/repositories`) para inverter dependências
- Mapeamento entre domínio e persistência isolado em `infrastructure/mapper`
- Controllers na camada de apresentação apenas orquestrando entrada/saída

## Estrutura do projeto (antes e depois)

### Antes (estrutura inicial)

```text
src/
  main/
    java/
      ada/joanna/api_usuarios/
        controller/
          UserController.java
        model/
          User.java
        repositories/
          UserRepository.java
        services/
          UserService.java
```

### Depois (estrutura com foco em Clean Architecture)

```text
src/
  main/
    java/
      ada/joanna/api_usuarios/
        domain/
          User.java
          repositories/
            UserRepository.java
          usecases/
            CreateUserUseCase.java
            GetUsersUseCase.java
            GetUserByIdUseCase.java
            DeleteUserUseCase.java
        infrastructure/
          mapper/
            UserMapper.java
          repositories/
            JpaUserRepository.java
            UserRepositoryImpl.java
            entities/
              UserEntity.java
        presentation/
          controller/
            UserController.java
```

## Testes implementados

O projeto já possui testes automatizados com **JUnit 5** e **Mockito**, cobrindo diferentes camadas:

- **Domínio**
    - `src/test/java/ada/joanna/api_usuarios/domain/UserTest.java`
    - `src/test/java/ada/joanna/api_usuarios/domain/usecases/CreateUserUseCaseTest.java`
    - `src/test/java/ada/joanna/api_usuarios/domain/usecases/GetUsersUseCaseTest.java`
    - `src/test/java/ada/joanna/api_usuarios/domain/usecases/GetUserByIdUseCaseTest.java`
    - `src/test/java/ada/joanna/api_usuarios/domain/usecases/DeleteUserUseCaseTest.java`
- **Infraestrutura**
    - `src/test/java/ada/joanna/api_usuarios/infrastructure/UserMapperTest.java`
    - `src/test/java/ada/joanna/api_usuarios/infrastructure/repositories/UserRepositoryImplTest.java`
- **Apresentação**
    - `src/test/java/ada/joanna/api_usuarios/presentation/controller/UserControllerTest.java`
- **Bootstrapping da aplicação**
    - `src/test/java/ada/joanna/api_usuarios/ApiUsuariosAppliucationTests.java`

Esses testes validam regras de negócio, mapeamentos, comportamento dos casos de uso, fluxo do controller e inicialização
da aplicação.

## Benefícios percebidos

- Melhor legibilidade da arquitetura
- Redução de acoplamento entre regra de negócio e tecnologia
- Base mais preparada para testes unitários
- Evolução incremental mais segura (refatorar sem quebrar tudo)

---

Material de estudo - Ada (Projeto Elas +Tech)

