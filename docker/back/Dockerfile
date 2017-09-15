FROM gradle:4.0.1-jdk8

MAINTAINER Augustin Husson <husson.augustin@gmail.com>

USER root

# install pip to build the configuration files
RUN apt-get update && \
        apt-get install -y --no-install-recommends \
                python-setuptools \
                python-pip \
                python-wheel && \
         pip install --upgrade pip && \
         pip install envtpl && \
         apt-get autoremove -y && \
         apt-get clean && \
         rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

ENV PROJECT_DIRECTORY=/var/workspace/coddy

COPY entrypoint.sh /
RUN chmod +x /entrypoint.sh

# Expose jetty port
EXPOSE 8080

ENTRYPOINT ["/entrypoint.sh"]

# Define default command.
CMD java -jar ${PROJECT_DIRECTORY}/back/build/libs/coddy-back.jar