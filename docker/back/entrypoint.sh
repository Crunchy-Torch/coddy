#!/bin/bash

CONF_TEMPLATE_FILE=${PROJECT_DIRECTORY}/docker/application.properties.tpl
CONF_FILE=${CODDY_CONF_DIRECTORY}/application.properties

echo ":: Generating configuration file ${CONF_FILE} ::"
if [[ -f ${CONF_TEMPLATE_FILE} ]] ; then
    if [[ ! -d ${CODDY_CONF_DIRECTORY} ]]; then
        echo ":: ${CODDY_CONF_DIRECTORY} not exists, creating it ::"
        mkdir -p ${CODDY_CONF_DIRECTORY}
    fi
    envtpl < ${CONF_TEMPLATE_FILE} > ${CONF_FILE}
fi

if [[ ! -d "$PROJECT_DIRECTORY" ]]; then
    echo "directory $PROJECT_DIRECTORY doesn't exist, unable to create the war package"
else
    cd ${PROJECT_DIRECTORY}/back && GRADLE_USER_HOME=gradle_cache gradle build -x test --parallel
fi

# Execute every command from user or from CMD Docker directive
exec "$@"