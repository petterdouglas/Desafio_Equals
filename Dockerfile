# --- Estágio 1: Build (Construção) ---
# Usa uma imagem do Maven com o JDK 17 para compilar o projeto
FROM maven:3.8.5-openjdk-17 AS build

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o arquivo pom.xml para baixar as dependências primeiro (otimização de cache)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o resto do código-fonte
COPY src ./src

# Executa o build do Maven para gerar o arquivo .jar
# O -DskipTests pula a execução dos testes durante o build do Docker
RUN mvn clean install -DskipTests


# --- Estágio 2: Run (Execução) ---
# Usa uma imagem base muito menor, contendo apenas o Java Runtime
FROM eclipse-temurin:17-jre-jammy

# Define o diretório de trabalho
WORKDIR /app

# Copia apenas o arquivo .jar gerado no estágio anterior para a imagem final
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta 8080, que é a porta padrão do Spring Boot
EXPOSE 8080

# Define o comando que será executado quando o contêiner iniciar
ENTRYPOINT ["java", "-jar", "app.jar"]