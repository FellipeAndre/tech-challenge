#-------------------- ESTAGIO 1: Biuld
# Usar imagem completa com maven + jdk para compilar

FROM maven:3.9.6-eclipse-temurin-21 AS build

# Define o diretório de trabalho dentro do container build

WORKDIR /app

# Copia o pom.xml primeiro - Docker cacheia essa camada
# Se o pom não mudou, não baixa as dependencias de novo

COPY pom.xml .
RUN mvn dependency:go-offline -B

# Agora copia o código fonte
COPY src ./src

# Compila e gera o JAR, pulando os testes
RUN mvn clean package -DskipTests

# ----------------- ESTAGIO 2: runtime -------------------------------------------------
# Imagem minima com só o JRE - muito menor que o JDK
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copia apenas o JAR gerado no estagio anterior

COPY --from=build /app/target/*.jar app.jar

# Porta que a aplicacao expoe
EXPOSE 8080

# Comando que roda quando o container sobe
ENTRYPOINT ["java", "-jar", "app.jar"]