# Stage 1: Build the app with JDK 17
FROM maven:3.9.9-amazoncorretto-17-debian AS build

WORKDIR /app


COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn package -DskipTests

# Stage 2: Run the app with JDK 17
FROM openjdk:17-jdk

WORKDIR /app

COPY --from=build /app/target/OrderCoffeeBE-0.0.1-SNAPSHOT.war OrderCoffee.war

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "OrderCoffee.war"]
