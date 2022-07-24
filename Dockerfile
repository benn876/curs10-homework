FROM maven:3 as building-app
WORKDIR app

ADD src src
ADD pom.xml pom.xml
RUN  mvn package -DskipTests=true

FROM eclipse-temurin:17-jre
WORKDIR app
COPY --from=building-app /app/target/curs10-homework.jar application.jar
CMD java -jar application.jar