FROM openjdk:9-jre

MAINTAINER Nikola

USER root

RUN useradd -s /usr/sbin/nologin -M reacty

RUN apt-get update \
        && apt-get install -y --no-install-recommends mongodb \
        && apt-get autoremove -y \
        && apt-get clean \
        && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

# Copy the executable
COPY backend/build/distributions/backend.tar /app/
COPY backend/build/resources /app/build/
COPY backend/src/reacty.yml /app/

# Extract the archive
RUN tar -xf /app/backend.tar --strip-component=1 -C /app/ && rm /app/backend.tar
RUN chmod +x /app/bin/backend

# Mongo
RUN mkdir data && mkdir data/db
RUN mongod &

WORKDIR /app

USER reacty

EXPOSE 5000

CMD ["bin/backend", "server", "reacty.yml"]