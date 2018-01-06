#!/bin/bash

l_coddy_path=$PWD
l_docker_image_base_name=crunchytorch/coddy
l_default_tag="dev-master"

# Connect to docker hub
connect(){
    docker login -u ${DOCKER_USER} -p ${DOCKER_PASS}

    if [ $? != 0 ]; then
        echo "something wrong with the docker's command"
        exit 1
    fi
}

# Pull the given tag
getDockerImage(){
    l_initial_tag=$1
    if [ -z "${l_initial_tag}" ]; then
       echo "if you want to get an image, you need to provide the corresponding tag"
    fi

    docker pull ${l_docker_image_base_name}:${l_initial_tag}

    if [ $? != 0 ]; then
        echo "something wrong with the docker's command"
        exit 1
    fi
}

# Tag the image from an existing tag
tag(){
    l_initial_tag=$1
    l_target_tag=$2

    # check that the image exists, pull if not
    if [[ "$(docker images -q ${l_docker_image_base_name}:${l_initial_tag} 2> /dev/null)" == "" ]]; then
        echo "image ${l_docker_image_base_name}:${l_initial_tag} does not exist, I need to pull it"
        getDockerImage l_initial_tag
    fi

    docker tag ${l_docker_image_base_name}:${l_initial_tag}  ${l_docker_image_base_name}:${l_target_tag}

    if [ $? != 0 ]; then
        echo "something wrong with the docker's command"
        exit 1
    fi
}

# Push the given tag
push(){
    l_initial_tag=$1

    if [ -z "${l_initial_tag}" ]; then
        l_initial_tag=${l_default_tag}
    fi

    docker push ${l_docker_image_base_name}:${l_initial_tag}

    if [ $? != 0 ]; then
        echo "something wrong with the docker's command"
        exit 1
    fi
}

# CleverTag will analyze the given tag and extract some sub tag.
# And then, it will tag the latest image with all sub tag extracted
# For example, if the tag is 1.0.0, CleverTag will extract the tag 1.0 and 1.0.0.
# So the you'll have the same image with the tag 1.0, 1.0.0, latest and dev-master
cleverTagAndPush(){
    l_initial_tag=$1

    if [ -z "${l_initial_tag}" ]; then
       echo "if you want to tag the latest image with a tag, you need to provide the corresponding tag"
    fi

    # in this case, we just tag with the given tag, because the version will be something like this : 1.0.0-rc1.
    # So it is not a definitive release

    if [[ "${l_initial_tag}" == *"-"* ]]; then
        tag ${l_default_tag} ${l_initial_tag}
        tag ${l_default_tag} "latest"

        push ${l_initial_tag}
        push "latest"
        exit 0
    fi

    IFS='.' read -ra TAG <<< "$l_initial_tag"

    # In this case, the release don't follow the pattern X.Y.Z
    if [[ "${TAG[@]}" == "${l_initial_tag}" ]]; then
        tag ${l_default_tag} ${l_initial_tag}
        tag ${l_default_tag} "latest"

        push ${l_initial_tag}
        push "latest"
        exit 0
    fi

    l_number_major=${TAG[0]}
    l_number_middle=${TAG[1]}

    tag ${l_default_tag} ${l_initial_tag}
    tag ${l_default_tag} "latest"
    tag ${l_default_tag} ${l_number_major}
    tag ${l_default_tag} "${l_number_major}.${l_number_middle}"

    push ${l_initial_tag}
    push "latest"
    push ${l_number_major}
    push "${l_number_major}.${l_number_middle}"
}

# Build the docker images with the given tag
build(){
    l_initial_tag=$1

    if [ -z "${l_initial_tag}" ]; then
        l_initial_tag=${l_default_tag}
    fi

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
-c or --connect to connect to the registry
-ct or --clever-tag to push multi sub tag from a given tag
-t or --tag to tag a docker image from another docker image"
}


case $1 in
    -c|--connect)
    connect
    shift
    ;;
    -p|--push)
    push $2
    shift
    ;;
    -b|--build)
    build $2
    shift
    ;;
    -t|--tag)
    tag $2 $3
    shift
    ;;
    -ct|--clever-tag)
    cleverTagAndPush $2
    shift
    ;;
    *)
    help
    ;;
esac