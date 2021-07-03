FROM openjdk:8

RUN apt-get update
RUN apt-get install net-tools
RUN mkdir -p /usr/app/
WORKDIR /usr/app
COPY target/assesment-0.0.1-SNAPSHOT.jar /usr/app/
RUN chmod 777 /usr/app
EXPOSE 8080
CMD ["java -XX:+HeapDumpOnOutOfMemoryError -Xms512m -jar assesment-0.0.1-SNAPSHOT.jar"]

