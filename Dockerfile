FROM khipu/openjdk17-alpine
COPY ./target/reservation-service-0.0.1-SNAPSHOT.jar reservation-service-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","reservation-service-0.0.1-SNAPSHOT.jar"]
ENV SPRING_PROFILES_ACTIVE=prod
