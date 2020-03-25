FROM node:10.19.0

# Expose angular port
EXPOSE 4200

ENV PROJECT_DIRECTORY=/var/workspace/coddy

COPY entrypoint.sh /
RUN chmod +x /entrypoint.sh

ENTRYPOINT ["/entrypoint.sh"]

# Define default command.
CMD cd $PROJECT_DIRECTORY/front/ && npm start
