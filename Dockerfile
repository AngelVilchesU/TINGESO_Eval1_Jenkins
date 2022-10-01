FROM openjdk:11
ARG JAR_FILE=build/libs/MueblesStgo-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} MueblesStgo-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/MueblesStgo-0.0.1-SNAPSHOT.jar"]