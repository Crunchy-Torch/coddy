#!/bin/bash

build_app_conf(){
    CONF_TEMPLATE_FILE=${CODDY_CONF_DIRECTORY}/application.properties.tpl
    CONF_FILE=${CODDY_CONF_DIRECTORY}/application.properties

    echo -n ":: Generating configuration file ${CONF_FILE} ::"
     if [[ -f ${CONF_TEMPLATE_FILE} ]] ; then
        envtpl < ${CONF_TEMPLATE_FILE} > ${CONF_FILE}
    fi
}

start_supervisor() {
    echo ":: Running services with supervisord ::"
    supervisord -n -c /etc/supervisor/supervisord.conf
}

build_app_conf
start_supervisor