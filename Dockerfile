# Stage 1: Build the app with JDK 17
FROM maven:3-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests
FROM openjdk:17-jdk
WORKDIR /app
COPY --from=build /app/target/OrderCoffeeBE-0.0.1-SNAPSHOT.war OrderCoffee.war
EXPOSE 8080
RUN mkdir -p /app/access/products
ENTRYPOINT ["java", "-jar", "OrderCoffee.war"]
