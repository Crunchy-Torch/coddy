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