# Use an official openjdk runtime as a parent image
FROM openjdk:8

# Install maven
RUN apt-get update
RUN apt-get install -y maven

# Define default command.
CMD ["bash"]
