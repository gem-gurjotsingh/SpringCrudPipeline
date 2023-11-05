FROM maven:3.6.3-openjdk-11

WORKDIR /app

# Copy code
COPY . .

# Build
RUN mvn clean install

# final directory
RUN mkdir -p /finaljar

# Copy built jar
RUN cp target/SpringBootCrud-0.0.1-SNAPSHOT.jar /finaljar/application.jar

# Remove code
RUN rm -rf /app

ENTRYPOINT ["java", "-jar", "/finaljar/application.jar"]

EXPOSE 8080
