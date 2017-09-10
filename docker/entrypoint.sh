#!/bin/bash

start_supervisor() {
    echo ":: Running services with supervisord..."
    /usr/local/bin/supervisord -n -c /etc/supervisor/supervisord.conf
}

start_supervisor