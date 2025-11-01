# 📝 To-Do List API

API REST de gerenciamento de tarefas desenvolvida em **Java 17+** com o **Spring Boot**.  
O projeto segue boas práticas de organização, utiliza **Spring Data JPA** para persistência, **Spring HATEOAS** para enriquecer as respostas com links, **PostgreSQL** como banco de dados e **Spring Validation** para validação dos dados.

---

## 🚀 Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot**
- **Spring Web**
- **Spring Data JPA**
- **Spring HATEOAS**
- **Spring Validation**
- **PostgreSQL**
- **Maven**

---

## 📁 Estrutura de Pastas

```bash
src/
└── main/
    ├── java/todolist/demo/
    │   ├── Controllers/
    │   │   └── TodoController.java        # Controlador principal da API
    │   ├── model/
    │   │   ├── dto/
    │   │   │   ├── TodoRequestDTO.java    # DTO para requisições (entrada de dados)
    │   │   │   └── TodoResponseDTO.java   # DTO para respostas (saída de dados)
    │   │   └── Todo.java                  # Entidade principal
    │   ├── repositories/
    │   │   └── TodoRepository.java        # Interface de repositório (JPA)
    │   ├── services/
    │   │   └── TodoService.java           # Camada de regra de negócio
    │   └── DemoApplication.java           # Classe principal (entry point)
    └── resources/
        ├── application.properties          # Configurações do banco e servidor
```

---

## ⚙️ Como Executar o Projeto

### 1️⃣ Pré-requisitos

- **Java 17+** instalado  
- **Maven** instalado  
- **PostgreSQL** rodando localmente


### 2️⃣ Clonar o projeto

No terminal, escolha uma pasta e execute o comando abaixo para clonar o repositório:

```bash
git clone https://github.com/seu-usuario/todolist.git
```

🔁 Substitua seu-usuario pelo seu nome de usuário do GitHub.
Exemplo: https://github.com/caiopelozzi/todolist.git

Depois, entre na pasta do projeto:

```bash
cd todolist
```

### 3️⃣ Configurar o banco de dados

No arquivo `src/main/resources/application.properties`, configure o acesso ao PostgreSQL:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/todolist
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Crie o banco manualmente no PostgreSQL com o comando:

```sql
CREATE DATABASE todolist;
```

### 4️⃣ Executar o projeto

No terminal, dentro da pasta do projeto, rode:

```bash
mvn spring-boot:run
```

A aplicação será iniciada em:
```
http://localhost:8080
```

---

## 🧠 Funcionalidades Atuais

- Criar, listar, atualizar e deletar tarefas (CRUD completo)
- Retorno de respostas padronizadas com HATEOAS
- Validação de dados com Spring Validation
- Integração com PostgreSQL

---

## 🧩 Próximos Passos

- ✅ Implementar **testes unitários** com **H2 Database**  
  (banco em memória para testes automatizados)
- ✅ Adicionar **documentação da API** com **Springdoc OpenAPI (Swagger UI)**

---

## 📚 Endpoints (exemplo)

| Método | Endpoint | Descrição |
|--------|-----------|------------|
| GET | `/todos` | Lista todas as tarefas |
| GET | `/todos/{id}` | Retorna uma tarefa específica |
| POST | `/todos` | Cria uma nova tarefa |
| PUT | `/todos/{id}` | Atualiza uma tarefa |
| DELETE | `/todos/{id}` | Remove uma tarefa |
