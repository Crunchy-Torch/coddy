#!/bin/bash

docker run --rm -v $PWD:/var/workspace/coddy coddy_front npm run-script build-prod

docker run --rm -v $PWD:/var/workspace/coddy coddy_back echo ok

docker build -t crunchytorch/coddy:latest .
docker tag crunchytorch/coddy:latest  crunchytorch/coddy:1.0.0-beta1