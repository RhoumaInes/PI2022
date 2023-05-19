FROM maven:3.8.1-jdk-8

ADD target/TPSpringBoot-1.0.jar TPSpringBoot-1.0.jar
EXPOSE 6664
#COPY . .
ENTRYPOINT ["java", "-jar", "TPSpringBoot-1.0.jar"]
#CMD mvn clean install
#CMD mvn spring-boot:run
