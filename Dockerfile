FROM openjdk:11

ARG VERSION

RUN echo "Building version 0.1.0-SNAPSHOT"

COPY build/libs/WatchListBot-0.1.0-SNAPSHOT-all.jar /usr/src/watchlistbot/WatchListBot-0.1.0-SNAPSHOT-all.jar
CMD ["java", "-jar", "/usr/src/watchlistbot/WatchListBot-0.1.0-SNAPSHOT-all.jar"]