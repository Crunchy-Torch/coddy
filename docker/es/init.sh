#!/bin/bash

bash /docker-entrypoint.sh elasticsearch & sleep 10s

cd / && curl -XPOST http://localhost:9200/_bulk --data-binary @user.bulk;

wait