#!/bin/bash

l_coddy_path=$PWD
l_docker_image_base_name=crunchytorch/coddy

tag(){
    l_initial_tag=$1
    l_target_tag=$2

    docker tag ${l_docker_image_base_name}:${l_initial_tag}  ${l_docker_image_base_name}:${l_target_tag}
}

push(){
    l_initial_tag=$1

    docker push ${l_docker_image_base_name}:${l_initial_tag}
}

build(){
    l_initial_tag=$1
    # ckeck that all front files exist
    if [ ! -d "front/dist" ]; then
        echo "the directory front/dist doesn't exist"
        exit 1
    fi

    # check that jar exists
    if [ ! -d "back/build/libs" ]; then
        echo "the directory back/build/libs doesn't exist"
        exit 1
    fi

    echo "generate application docker images"
    docker build -t ${l_docker_image_base_name}:${l_initial_tag} .
    if [ $? != 0 ]; then
        echo "something wrong with the docker's command"
        exit 1
    fi
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