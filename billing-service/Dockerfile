FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline -B #this is for cache the dependency whenever we are running /stopping the file so it is faster


COPY src ./src

RUN mvn clean package

FROM eclipse-temurin:21-jdk AS runner

WORKDIR /app

COPY --from=builder ./app/target/billing-service-0.0.1-SNAPSHOT.jar ./app.jar
#the above going to copy the builder one

EXPOSE 4001
EXPOSE 9001

ENTRYPOINT ["java","-jar","app.jar"]
