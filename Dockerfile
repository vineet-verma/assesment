FROM openjdk:8

RUN apt-get update
RUN apt-get install net-tools
RUN mkdir -p /usr/app/
WORKDIR /usr/app
COPY runserver.sh target/assesment-0.0.1-SNAPSHOT.jar /usr/app/
RUN chmod 777 /usr/app
RUN chmod +x /usr/app/runserver.sh
EXPOSE 8080
CMD ["./runserver.sh"]

