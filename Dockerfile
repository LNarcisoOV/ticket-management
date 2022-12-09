FROM openjdk:17-oracle
EXPOSE 8080
ADD /target/ticket-management-0.0.1-SNAPSHOT.jar ticket-management.jar
ENTRYPOINT ["java", "-jar", "ticket-management.jar"]