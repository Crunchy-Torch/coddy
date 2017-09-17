#!/bin/bash

CODDY_PATH=$PWD

DOCKER_IMAGE_BASE_NAME=crunchytorch/coddy

build(){
    local_tag=$1
    # build front application
    if docker images | grep -q "coddy_front"; then
        echo "coddy_front images exists, enable to build the front application"
    else
        echo "coddy_front images not found ==> generate corresponding build images"
        cd docker/front && docker build -t coddy_front . && cd ${CODDY_PATH}
    fi

    echo "generate front application"
    docker run --rm -v ${CODDY_PATH}:/var/workspace/coddy coddy_front npm run-script build-prod

    # build back application
    if docker images | grep -q "coddy_back"; then
        echo "coddy_back images exists, enable to build the front application"
    else
        echo "coddy_back images not found ==> generate corresponding build images"
        cd docker/back && docker build -t coddy_back . && cd ${CODDY_PATH}
    fi

    echo "generate back application"
    docker run --rm -v ${CODDY_PATH}:/var/workspace/coddy coddy_back echo ok

    echo "generate application docker images"
    docker build -t ${DOCKER_IMAGE_BASE_NAME}:${local_tag} .
}

tag(){
    l_initial_tag=$1
    l_target_tag=$2

    docker tag ${DOCKER_IMAGE_BASE_NAME}:${l_initial_tag}  ${DOCKER_IMAGE_BASE_NAME}:${l_target_tag}
}

push(){
    l_initial_tag=$1

    docker push ${DOCKER_IMAGE_BASE_NAME}:${l_initial_tag}
}


function help {
    echo "-p or --push to push a docker image to the registry
-b or --build to build a docker image
-t or --tag to tag a docker image from another docker image"
}


case $1 in
    -p|--push)
    push $2
    shift # past argument=value
    ;;
    -b|--build)
    build $2
    shift # past argument=value
    ;;
    -t|--tag)
    tag $2 $3
    shift # past argument=value
    ;;
    *)
    help  # unknown option
    ;;
esac