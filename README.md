# 📊 Processo Seletivo Equals - Homologação de Meio de Pagamento

Solução back-end para processamento de arquivos de extrato bancário em formato posicional, com armazenamento em banco de
dados e API REST.

## 🧩 Funcionalidades

- **Upload de Arquivos**
    - Processamento de arquivos .txt com estrutura posicional (Header/Detalhe/Trailer)
    - Validação de estrutura e formato dos registros

- **Armazenamento**
    - Persistência em banco de dados PostgreSQL
    - Modelagem de empresas, extratos e transações
    - Prevenção de duplicação de dados (idempotência)

- **API REST**
    - Consulta de empresas cadastradas
    - Listagem de extratos por empresa
    - Visualização de transações por extrato

- **Documentação**
    - Swagger UI para exploração interativa dos endpoints
    - Especificação OpenAPI 3.0

## 🚀 Tecnologias Utilizadas

**Backend:**

- Java 17
- Spring Boot 3
- Spring Data JPA
- Hibernate
- Lombok

**Banco de Dados:**

- PostgreSQL

**Infraestrutura:**

- Docker
- Docker Compose

**Documentação:**

- SpringDoc OpenAPI
- Swagger UI

## ✅ Pré-requisitos

**Ambiente Docker (Recomendado):**

- Docker 20.10+
- Docker Compose 2.0+
- Git

**Execução Manual:**

- JDK 17
- Maven 3.8+
- PostgreSQL 14+

## ⚡ Como Executar

### Método 1: Docker (Recomendado)

#### Passo 1: Clone o repositório

```bash
# Clone o repositório
git clone https://github.com/petterdouglas/Desafio_Equals.git
```

#### Passo 2: Navegue até a pasta raiz do projeto

``` bash
cd Desafio_Equals
```

#### Passo 3: Suba os contêineres

Execute o comando abaixo. Na primeira vez, ele pode demorar alguns minutos para baixar as imagens e dependências.

``` bash
docker-compose up --build
```

#### Passo 4: Verificação

Aguarde a inicialização completa.  
A aplicação estará pronta para uso quando os logs mostrarem a mensagem ``` Started HomologacaoApplication... ```.  
A API estará disponível em ``` http://localhost:8080 ```.

**Não esqueça de deixar a porta 8080 livre*

### Método 2: Execução Manual

#### 1. Clone o repositório e navegue até a pasta raiz.

#### 2. Configure o Banco de Dados:

Garanta que você tenha um servidor PostgreSQL rodando e crie um banco de dados para a aplicação (ex:
``` CREATE DATABASE extratos_equals;```).

#### 3. Configure a Conexão:

Verifique e ajuste, se necessário, as credenciais de acesso ao banco no arquivo
``` src/main/resources/application.properties ```.

#### 4. Execute a Aplicação:

Use o Maven para iniciar o projeto.

```bash
# Execute a aplicação
mvn spring-boot:run
```

## 📖 Uso da API

Após a inicialização, você pode interagir com a API.

### Documentação Interativa

Acesse a interface do Swagger para explorar todos os endpoints:
http://localhost:8080/swagger-ui.html

### Exemplos com cURL

#### 1. Importar arquivo de extrato:

Use o endpoint ``` POST /importar ``` para fazer o upload do arquivo ```Arquivo_Estagio_Desenvolvimento.txt```.

```bash
curl -X POST -F "arquivo=@/caminho/do/arquivo.txt" http://localhost:8080/importar
```

#### 2. Listar extratos de uma empresa

Após a importação, você pode buscar os extratos de uma empresa (neste caso, a empresa de ```id=1```).

```bash
curl -X GET http://localhost:8080/extrato/empresa/1
```

#### 3. Listar Transações de um Extrato

Para ver as transações de um extrato específico (neste caso, extrato de ```id=1```).

``` bash
curl -X GET http://localhost:8080/extrato/1/transacao/all
```

### 👨‍💻 Autor

Petter Douglas - *Projeto desenvolvido para o processo seletivo da Equals*