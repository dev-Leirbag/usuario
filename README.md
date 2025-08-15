# ​ Cadastro de Usuario – Microserviço de Usuários

Este repositório contém **o microserviço responsável pelo cadastro e autenticação de usuários** no sistema distribuído de Agendador de Tarefas com Notificações.  
Ele cuida da criação de contas, login, emissão de tokens JWT e validação de credenciais.

> ℹ️ Este é apenas um dos microsserviços. Ele funciona em conjunto com:  
> - **Agendamento de Tarefas)**  
> - **Envio de E-mails)**  
> - **BFF (Orquestração e Integração)**

---

##  Visão Geral

O **Cadasto de Usuario** expõe endpoints para:

- Criar e gerenciar usuários
- Login com geração de token JWT
- Validação de tokens para proteger recursos

Esses endpoints são consumidos pelo **BFF**, que os integra com os demais serviços de forma segura e centralizada.

---

##  Tecnologias Utilizadas

- **Java 17**  
- **Spring Boot** (Spring Web, Spring Security, Spring Data JPA)  
- **JWT** para autenticação e autorização  
- **PostgreSQL** como banco de dados  
- **Docker** / Docker Compose para containerização  
- **Swagger** para documentação de API  
- **JUnit e Mockito** para testes automatizados  

---

##  Como executar este microserviço

### Pré-requisitos

- Docker e Docker Compose instalados  
- Java 17 instalado (se optar por rodar sem container)  

### Executando com Docker Compose

1. No diretório raiz do projeto:

    ```bash
    docker-compose up --build auth-service
    ```

2. O serviço estará disponível em:

    ```
    http://localhost:8082/swagger-ui.html
    ```

### Executando localmente com Gradle/Maven

1. Edite o arquivo `application.properties` com as configurações do banco:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/authdb
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   server.port=8080
   
Execute via Gradle ou Maven:

    ./gradlew bootRun #ou
    mvn spring-boot:run
    
Acesse a documentação da API:

    http://localhost:8082/swagger-ui.html
    
## Funcionalidades

Cadastro de usuários (com senha criptografada)

Login com emissão de token JWT

Validação e renovação de token JWT

Microsserviços Relacionados
Agendamento de Tarefas → [Repositório](https://github.com/dev-Leirbag/agendador-tarefas)

Envio de E-mails → [Repositório](https://github.com/dev-Leirbag/notificacao)

BFF (Orquestração e Integração) → [Repositório](https://github.com/dev-Leirbag/bff-agendador-tarefas)

## Autor

Gabriel Alves Ferreira
[LinkedIn](https://www.linkedin.com/in/gabriel-alves-profile/)
