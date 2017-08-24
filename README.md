# coddy
[![Build Status](https://travis-ci.org/Crunchy-Torch/coddy.svg?branch=master)](https://travis-ci.org/Crunchy-Torch/coddy)

1. [Overview](#overview)  
2. [Development](#development)
3. [Contributions](#contributions)

## Overview

**Project Status** : *work in progress* :smirk:

This project aims to propose a light solution to display and search code snippet in order to share some personal knowledge in IT.

### Technologies used

* back-end developped with java 8 :
  * spring-boot
  * jersey
  * jwt
  * ElasticSearch
* front-end developped with angular 4 :
  * angular-cli
  * semantic-ui
* integration with Docker

### API
If you want to see all endpoint provide by our api, you can take a look at [this swagger](./docs/api-swagger.yml), 
or you can access through the [swaggerHub](https://app.swaggerhub.com/apis/Nexucis/Coddy/1.0.0)

## Development

If you want to develop this project or just test it, a `docker-compose.dev.yml` is provided in order to start locally all necessary server.

1. You must copy this file and rename it to `docker-compose.yml` at the same place. 
2. Declare local environment variable `CODDY_PROJECT` which must value the absolute path to the project. *Because the file is ignored, you can edit the file directly by replacing the variable `CODDY_PROJECT` with the absolute path.*
3. And then just fire the command `docker-compose up`.

**Tips** : of course you need to install docker-compose before using it. To do this, follow the official documentation [here](https://docs.docker.com/compose/install/)

When all server are up, you can access to :

* the local back-end at this URL : http://localhost:8080/
* the local front-end at this URL : http://localhost:4200/
* the local instance of elasticsearch at this URL : http://localhost:9200

## Contributions

Any contribution or suggestion would be really appreciated. Please feel free to use the Issue section or to send a pull request.
