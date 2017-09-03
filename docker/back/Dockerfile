# Use an official openjdk runtime as a parent image
FROM maven:3.5.0-jdk-8

ENV PROJECT_DIRECTORY=/var/workspace/coddy

COPY entrypoint.sh /
RUN chmod +x /entrypoint.sh

# Expose tomcat port
EXPOSE 8080

ENTRYPOINT ["/entrypoint.sh"]

# Define default command.
CMD java -jar ${PROJECT_DIRECTORY}/back/target/coddy-back-*.war