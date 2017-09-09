#!/bin/bash

if [[ ! -d "$PROJECT_DIRECTORY" ]]; then
    echo "directory $PROJECT_DIRECTORY doesn't exist, unable to create the war package"
else
    cd ${PROJECT_DIRECTORY}/back && GRADLE_USER_HOME=gradle_cache ./gradlew build -x test --parallel
fi

# Execute every command from user or from CMD Docker directive
exec "$@"