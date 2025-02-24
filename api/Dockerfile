FROM eclipse-temurin:21 AS build

RUN apt-get update && \
    apt-get install -y maven && \
    apt-get clean;
WORKDIR /app

VOLUME ["/root/.m2"]

COPY . .

RUN ls -la /app/core

RUN mvn clean install -DskipTests -pl core

RUN mvn clean package -DskipTests -pl api -am

FROM eclipse-temurin:21
WORKDIR /app
COPY --from=build /app/api/target/api-*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
