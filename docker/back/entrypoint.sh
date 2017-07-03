#!/bin/bash

if [[ ! -d "$PROJECT_DIRECTORY" ]]; then
    echo "directory $PROJECT_DIRECTORY doesn't exist, unable to create the war package"
else
    cd ${PROJECT_DIRECTORY}/back && mvn --batch-mode clean package -Dmaven.test.skip=true
fi

# Execute every command from user or from CMD Docker directive
exec "$@"