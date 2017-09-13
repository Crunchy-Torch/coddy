#!/bin/bash

if [[ ! -d "$PROJECT_DIRECTORY" ]]; then
    echo "directory $PROJECT_DIRECTORY doesn't exist, unable to build the front application"
else
    # build project at first start
    echo "running command 'npm install'"
    cd ${PROJECT_DIRECTORY}/front && yarn install
fi

# Execute every command from user or from CMD Docker directive
exec "$@"
