FROM adoptopenjdk/openjdk11:alpine-jre
EXPOSE 8080
COPY target/caloriecap.jar caloriecap.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","caloriecap.jar"]