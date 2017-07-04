# coddy

## Développement

If you want to develop this project or just test it, a `docker-compose.dev.yml` is provided in order to start locally all necessary server.

1. You must copy this file and rename it to `docker-compose.yml` at the same place. 
2. Declare local environment variable `CODDY_PROJECT` which must value the absolute path to the project. *Because the file is ignored, you can edit the file directly by replacing the variable `CODDY_PROJECT` with the absolute path.*
3. And then just fire the command `docker-compose up`.

**Tips** : of course you need to install docker-compose before using it. To do this, follow the official documentation [here](https://docs.docker.com/compose/install/)

When all server are up, you can access to :

* the local back-end at this URL : http://localhost:8080/
* the local front-end at this URL : http://localhost:4200/
* the local instance of elasticsearch at this URL : http://localhost:9200