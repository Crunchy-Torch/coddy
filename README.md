# Coddy
[![CircleCI](https://circleci.com/gh/Crunchy-Torch/coddy.svg?style=shield)](https://circleci.com/gh/Crunchy-Torch/coddy) [![GitHub license](https://img.shields.io/badge/license-MIT-blue.svg)](./LICENSE) [![codecov](https://codecov.io/gh/Crunchy-Torch/coddy/branch/master/graph/badge.svg)](https://codecov.io/gh/Crunchy-Torch/coddy)

1. [Overview](#overview)  
2. [Run Coddy](#run-coddy)
3. [Development](#development)
4. [Contributions](#contributions)
5. [License](#license)

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
  * Angular 5 with Typescript
  * angular-cli
  * semantic-ui
* Integration:
  * Docker
  * Circle-CI

### API
Endpoints exposed by Coddy's API are described in [this swagger](./docs/api-swagger.yml). If you're not so familiar with the swagger syntax (or if you prefer a well-designed interface over raw text), just check out the data on [SwaggerHub](https://app.swaggerhub.com/apis/Nexucis/Coddy/1.0.0)!

## Run Coddy

### Docker
A production docker image of Coddy is available and can be pulled on [docker hub](https://hub.docker.com/r/crunchytorch/coddy/).

#### How to use the Coddy image

The easiest way to start Coddy is to use a docker-compose file configuration. For example: 

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

Once the image is started, wait until you see something like: 

```bash
2017-09-15 21:28:51.343  INFO 74 --- [           main] .s.b.c.e.j.JettyEmbeddedServletContainer : Jetty started on port(s) 8080 (http/1.1)
2017-09-15 21:28:51.355  INFO 74 --- [           main] org.crunchytorch.coddy.Main              : Started Main in 15.305 seconds (JVM running for 16.668)
```

Then you can access your Coddy instance at `http://localhost`

#### Environment variables

Environment variables can be pass through Coddy instance, either using `docker run` command arguments or configuring docker-compose file properly.

##### SPRING_ES_HOST

Default value: `es:9300` (with `es` the service's name of your ElasticSearch instance, as defined in your docker-compose file)

##### CODDY_SECURITY_JWT_SECRET

Allow you to specify the secret key which will be used to encrypt user's token. If this variable is unset, the secret will be randomly generated.

Default value: random 
 
Note: this variable must be set if you run multi instance of this image. Otherwise, generated tokens will not be valid from one instance to another.

##### CODDY_SECURITY_JWT_SESSION_TIMEOUT

Allow you to specify the user's session duration (i.e how much time a generated token will remain valid). The session duration have to be set in minutes.

Default value: 1440 (24 hours)

##### CODDY_USER_ADMIN_LOGIN, CODDY_USER_ADMIN_PASSWORD, CODDY_USER_ADMIN_EMAIL

Allow you to specify credentials (login ,apssword and email) that will be used to inflate an admin user (with admin permission).

Default value: none

Note: it is strongly advised to create an admin user. Otherwise, you may not manage users and others administrators.

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

[MIT](./LICENSE)
