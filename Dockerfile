FROM openjdk:11
COPY ./target/spring-boot-tc-1.0.0.jar /usr/app/
ENTRYPOINT ["java","-jar","/usr/app/spring-boot-tc-1.0.0.jar"]
