# 📊 Desafio Técnico - Processo Seletivo Equals

Este projeto foi desenvolvido como parte do processo seletivo da empresa **Equals**, com o objetivo de simular a homologação de um meio de pagamento.

A aplicação consiste em uma solução back-end escrita em **Java (Spring Boot)**, responsável por:

- ✅ Interpretar arquivos de extrato em layout posicional fixo, contendo registros de vendas.
- ✅ Persistir os dados extraídos em um banco de dados relacional (**PostgreSQL**).
- ✅ Exibir as vendas em um relatório acessível via página web ou API REST, com filtro por data da transação.

---

## 🧩 Funcionalidades

- 📂 Leitura e interpretação de arquivos `.txt` com estrutura de Header, Detalhe e Trailer.
- 🗄️ Armazenamento das transações de venda em banco de dados.
- 📈 Relatório com listagem das vendas registradas.
- 🔎 Filtro de vendas por data.
- 🧱 Projeto estruturado em camadas: `controller`, `service`, `repository`, `model`.

---

## 🚀 Tecnologias utilizadas

- ☕ Java 17
- 🌱 Spring Boot
- 🛢️ Spring Data JPA
- 🐘 PostgreSQL
- 💡 Lombok
- 🔧 Maven

---

## 📄 Observações

- O layout do arquivo de extrato segue rigorosamente o padrão descrito na documentação oficial fornecida pela Equals.
- A aplicação é executável via terminal com Maven, sem dependência de IDEs específicas.
- O projeto foi desenvolvido com foco em clareza, escalabilidade e organização, estando preparado para evoluir e incluir novas bandeiras de cartão ou tipos de registros.

---

## 👨‍💻 Autor

**Petter Douglas**  
Projeto desenvolvido para o processo seletivo da Equals.

