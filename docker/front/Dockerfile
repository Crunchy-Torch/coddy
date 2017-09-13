FROM node:6.11

RUN yarn global add @angular/cli && yarn cache clean

# Expose angular port
EXPOSE 4200

ENV PROJECT_DIRECTORY=/var/workspace/coddy

COPY entrypoint.sh /
RUN chmod +x /entrypoint.sh

ENTRYPOINT ["/entrypoint.sh"]

# Define default command.
CMD cd $PROJECT_DIRECTORY/front/ && npm start
