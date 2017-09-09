FROM gradle:4.0.1-jdk8

MAINTAINER Augustin Husson<husson.augustin@gmail.com>

USER root

ENV PROJECT_DIRECTORY=/var/workspace/coddy

COPY entrypoint.sh /
RUN chmod +x /entrypoint.sh

# Expose jetty port
EXPOSE 8080

ENTRYPOINT ["/entrypoint.sh"]

# Define default command.
CMD java -jar ${PROJECT_DIRECTORY}/back/build/libs/coddy-back-*.jar