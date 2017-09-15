# Coddy
[![Build Status](https://travis-ci.org/Crunchy-Torch/coddy.svg?branch=master)](https://travis-ci.org/Crunchy-Torch/coddy)

1. [Overview](#overview)  
2. [Development](#development)
3. [Contributions](#contributions)
4. [License](#license)

## Overview

**Project status**: *work in progress* :smirk:

Coddy is a community-driven platform that allow users to share code snippets. It provides an easy and interactive way to input code snippets and to share them with the rest of the community.

### Technologies

* Back-end:
  * Java 8
  * Spring-boot
  * Jersey
  * Jwt
  * ElasticSearch
* Front-end:
  * Angular 4 with Typescript
  * angular-cli
  * semantic-ui
* Integration:
  * Docker
  * Travis-CI

### API
Endpoints exposed by Coddy's API are described in [this swagger](./docs/api-swagger.yml). If you're not so familiar with the swagger syntax (or if you prefer a well-designed interface over raw text), just check out the data on [SwaggerHub](https://app.swaggerhub.com/apis/Nexucis/Coddy/1.0.0)!

### Docker
We provide an industrial image of this project, which can be pulled on [docker hub](https://hub.docker.com/r/crunchytorch/coddy/).

#### How to use the coddy image

A simple way to run the image is to do it through a docker-compose file configuration. You can see below a light one : 

```yaml
version: '3'

services:
  es:
    image: elasticsearch:2.4.5
    ports:
      - 9200:9200
      - 9300:9300
  coddy:
    image: crunchytorch/coddy
    ports:
      - 80:80
    depends_on:
      - es
```

#### Environment Variables

When you start the coddy image, you can adjust the configuration of the Coddy instance by passing one or more environment variables on the docker run command line or in the docker-compose file configuration.

##### SPRING_ES_HOST

This variable is optional. By default this variable is set to `es:9300`, where `es` is the service name in your docker-compose corresponding to the elasticsearch instance.

##### CODDY_JWT_SECRET

This variable is optional and allows you to specify the secret key which will be used to signe the user's token. If you don't set this variable, the secret will be generated randomly.
 
That's why you must set this variable if you run multi instance of this image. If you don't, all instance will have a different secret, and the token will not be valid from one instance to another.

##### CODDY_JWT_SESSION_TIMEOUT_MIN

This variable is optional and allows you to specify the user's session duration (through a JWT token). The session duration have to be set in minutes and by default the session is set to 24 hours (1440 min)

##### CODDY_ADMIN_LOGIN, CODDY_ADMIN_PASSWORD, CODDY_ADMIN_EMAIL

These variables are optional, used in conjunction to create a new admin and to set that admin's password and that admin's email. This admin will be granted with the admin permissions.

Be warned, if you don't have an admin, you cannot manage your users and cannot give admin permission using the coddy interface to others.

## Development
You want to try it out? Great! We provide a `docker-compose.dev.yml` file to help you getting started.

1. Rename it (or copy) to `docker-compose.yml` (same folder).  *This file is ignored, feel free to edit it directly.*
2. Create a new local environment variable named `CODDY_DIR`, which contains the absolute path of the project.
3. Use the command `docker-compose up`.

**Tip** : you may need to install docker-compose before using it. To do this, just follow the official documentation [here](https://docs.docker.com/compose/install/).

Once servers are up, you can access:

* the local back-end at this URL: http://localhost:8080/api/v1/
* the local front-end at this URL: http://localhost:4200/
* the local instance of elasticsearch at this URL: http://localhost:9200

## Contributions

Any contribution or suggestion would be really appreciated. Feel free to use the Issue section or to send a pull request.

## License

MIT