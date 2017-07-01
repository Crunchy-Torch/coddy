# Use an official openjdk runtime as a parent image
FROM openjdk:8

# Install maven
RUN apt-get update
RUN apt-get install -y maven
# Install nodejs
RUN curl -sL https://deb.nodesource.com/setup_8.x | bash -
RUN apt-get install -y nodejs

# Define default command.
CMD ["bash"]
