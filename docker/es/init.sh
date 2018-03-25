#!/bin/bash

ES_HOST=localhost
ES_PORT=9200

ACCOUNT_INDEX=account
USER_TYPE=user
CONTENT_INDEX=content
SNIPPET_TYPE=snippet


function populateIndex(){
    local_index=$1
    local_type=$2

    if curl -sI http://${ES_HOST}:${ES_PORT}/${local_index} | grep 200 > /dev/null;  then
        echo "index ${local_index} exists, push corresponding data in this index"

        if command -v jq > /dev/null; then
            echo "command jq founded, populate the index ${local_index}"
            cd ./data/ && cat ${local_index}.json | jq -c ".[] | {\"index\": {\"_index\": \"${local_index}\", \"_type\": \"${local_type}\"}}, ." | curl -XPOST localhost:9200/_bulk --data-binary @-
            cd ..
        else
            echo "command jq doesn't exist, you need to install it first"
            exit 1;
        fi
    else
        echo  "index ${local_index} doesn't exist, failed to push corresponding data in this index. Maybe you should considerate to create it with the command ./init.sh -c or ./init.sh --create"
        exit 1;
    fi

}

function populate {
    populateIndex ${ACCOUNT_INDEX} ${USER_TYPE}
    populateIndex ${CONTENT_INDEX} ${SNIPPET_TYPE}
}

function createIndex {
    local_index=$1

    if curl -sI http://${ES_HOST}:${ES_PORT}/${local_index} | grep 404 > /dev/null;  then
        echo  "index ${local_index} doesn't exist, creating index in progress"
        cd ./mapping/ && curl -XPUT http://${ES_HOST}:${ES_PORT}/${local_index} -d @${local_index}.json
    else
         echo "index ${local_index} already exists, if you want to erase this index, maybe you should considerate to delete it"
    fi
}

function create {
    createIndex ${ACCOUNT_INDEX}
    createIndex ${CONTENT_INDEX}
}

function deleteIndex {
    local_index=$1

    if curl -sI http://${ES_HOST}:${ES_PORT}/${local_index} | grep 200 > /dev/null;  then
        echo "index ${local_index} exists, delete current index"

        curl -XDELETE http://${ES_HOST}:${ES_PORT}/${local_index}
    else
        echo  "index ${local_index} doesn't exist, failed to delete it"
        exit 1;
    fi
}

function delete {
    deleteIndex ${ACCOUNT_INDEX}
    deleteIndex ${CONTENT_INDEX}
}

function help {
    echo "-c or --create to create all necessary index
-p or --populate to populate all index
-d or --delete to delete all index
    "
}

case $1 in
    -p|--populate)
    populate
    shift # past argument=value
    ;;
    -c|--create)
    create
    shift # past argument=value
    ;;
    -d|--delete)
    delete
    shift # past argument=value
    ;;
    *)
    help  # unknown option
    ;;
esac