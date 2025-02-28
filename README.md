# Teste Técnico - API

## 📌 Sobre o Projeto
Esta API foi desenvolvida para realizar diversas operações essenciais, incluindo:

✅ **CRUD de tarefas** (Criar, Ler, Atualizar e Deletar tarefas).  
✅ **Persistência de dados** utilizando **PostgreSQL**.  
✅ **Tratamento de exceções personalizadas** com **ExceptionHandlers**.  
✅ **Testes unitários** utilizando **JUnit + Mockito**.  
✅ **Monitoramento** com **Spring Boot Actuator**.

🔹 Todos os endpoints do Actuator estão expostos devido à ausência do **Spring Security** neste projeto.

---

## ⚙️ **Pré-requisitos**

Antes de iniciar, certifique-se de que sua máquina possui os seguintes requisitos:

- **JDK 21** (Java Development Kit 21).
- **PostgreSQL** instalado e configurado.

---

## 🚀 **Configuração e Execução**

### 🔹 **1. Criar o banco de dados**
Abra o **PostgreSQL** e crie um banco de dados com o nome **`waterfy`**:

```sql
CREATE DATABASE waterfy;
```

Se quiser utilizar outro nome, lembre-se de alterar a configuração no `application.properties`.

### 🔹 **2. Configurar o Banco de Dados**
Abra o arquivo **`application.properties`** localizado em:

```
src/main/resources/application.properties
```

Edite as seguintes propriedades com seu usuário e senha do banco:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/waterfy
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
```

Se estiver usando um banco com nome diferente de `waterfy`, altere também o valor de `spring.datasource.url`.

### 🔹 **3. Rodar a aplicação**
Após configurar o banco, basta iniciar a aplicação através da sua IDE favorita (**recomendado: IntelliJ IDEA**).

O **Hibernate** criará as tabelas automaticamente devido à configuração:

```properties
spring.jpa.hibernate.ddl-auto=update
```

### 🔹 **4. Acessar a API**
Após iniciar a aplicação, a API estará disponível em:

```
http://localhost:4000
```

---

## 🧪 **Testes Unitários**

Os testes unitários estão localizados em:

```
src/test/java/com/alves/waterfy/service/TarefaServiceTest
```

Eles cobrem todas as funcionalidades e exceções do `TarefaService`.

---

## 📊 **Monitoramento com Actuator**

Para acessar os endpoints do **Spring Boot Actuator**, basta acessar:

```
http://localhost:4000/actuator
```

Lá você poderá visualizar os endpoints disponíveis, como:

- `/health` → Verificar o status da aplicação.
- `/metrics` → Monitorar métricas da API.
- `/env` → Ver variáveis de ambiente.

---

## 🎨 **Interface Gráfica (Consumer)**

Para utilizar a API em uma interface gráfica, acesse o repositório do **Consumer**:

🔗  *https://github.com/idkdrew/WaterfyLeetCodeConsumer*

Siga as instruções do README de lá para configurar corretamente.

---

## 📌 **Conclusão**

Muito obrigado por testar este projeto! Qualquer dúvida ou sugestão, fique à vontade para entrar em contato. 🚀

