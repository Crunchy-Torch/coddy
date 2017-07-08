FROM node:6.9

RUN npm install -g @angular/cli && npm cache clean

# Expose angular port
EXPOSE 4200

ENV PROJECT_DIRECTORY=/var/workspace/coddy

COPY entrypoint.sh /

ENTRYPOINT ["/entrypoint.sh"]

# Define default command.
CMD cd $PROJECT_DIRECTORY/front/src && npm start