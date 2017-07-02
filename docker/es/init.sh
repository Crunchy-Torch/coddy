#!/bin/bash

bash /docker-entrypoint.sh elasticsearch & sleep 10s

ACCOUNT_INDEX=account

cd / \
&& curl -XPUT http://localhost:9200/${ACCOUNT_INDEX} -d @${ACCOUNT_INDEX}.json \
&& curl -XPOST http://localhost:9200/_bulk --data-binary @user.bulk;

wait