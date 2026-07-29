# nomeia imagem do jdk 21 como build
FROM eclipse-temurin:21-jdk AS build

# define /app como diretório usado
WORKDIR /app

COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

# permite execução de maven wrapper e baixa dependências
RUN chmod +x mvnw && ./mvnw dependency:go-offline -B

COPY src ./src

# empacota o projeto e renomeia arquivo jar para app.jar
RUN ./mvnw clean package -DskipTests -B && mv target/*.jar target/app.jar

# reduz tamanho da imagem final, executando java
FROM eclipse-temurin:21-jre AS runtime

# redefine /app como diretorio
WORKDIR /app

# cria grupo e usuario spring
RUN groupadd --system spring && useradd --system --gid spring spring

COPY --from=build /app/target/app.jar app.jar

# altera proprietário para spring
RUN chown spring:spring app.jar

USER spring

EXPOSE 8080

# verifica se o serviço está funcionando
HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 CMD bash -c 'echo > /dev/tcp/127.0.0.1/8080' || exit 1

# executa app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]