FROM openjdk:11

EXPOSE 8080
ARG VERSION

RUN echo "Building version 0.1.0"

COPY build/libs/WatchListBot-0.1.0-all.jar /usr/src/watchlistbot/WatchListBot-0.1.0-all.jar
CMD ["java", "-jar", "/usr/src/watchlistbot/WatchListBot-0.1.0-all.jar"]