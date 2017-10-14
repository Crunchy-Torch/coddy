@echo off

SET CODDY_PATH=%~dp0

SET DOCKER_IMAGE_BASE_NAME=crunchytorch/coddy

SET option=%~1

IF "%option%" == "-b" (
    CALL :build %~2
    GOTO :EOF
)

IF "%option%" == "-t" (
    CALL :tag %~2 %~3
    GOTO :EOF
)

IF "%option%" == "-p" (
    CALL :push %~2
    GOTO :EOF
)

:build
    SET local_tag=%~1
    echo build image %DOCKER_IMAGE_BASE_NAME%:%local_tag%

    docker images | findstr /R /C:"coddy_front" > nul

    IF errorLevel 0 (
        echo coddy_front images exists, enable to build the front application
    ) else (
        echo "coddy_front images not found ==> generate corresponding build images"
        cd .\docker\front\ && docker build -t coddy_front . && cd %CODDY_PATH%
    )

    docker run --rm -v %CODDY_PATH%:/var/workspace/coddy coddy_front npm run-script build-prod

    docker images | findstr /R /C:"coddy_back" > nul

    IF errorLevel 0 (
        echo coddy_back images exists, enable to build the back application
    ) else (
        echo "coddy_back images not found ==> generate corresponding build images"
        cd .\docker\back\ && docker build -t coddy_back . && cd %CODDY_PATH%
    )

    docker run --rm -v %CODDY_PATH%:/var/workspace/coddy coddy_back echo ok

    echo generate application docker images
    docker build -t %DOCKER_IMAGE_BASE_NAME%:%local_tag% .

EXIT /B 0

:tag
    SET l_initial_tag=%~1
    SET l_target_tag=%~2
    echo generate tag from %DOCKER_IMAGE_BASE_NAME%:%l_initial_tag% to %DOCKER_IMAGE_BASE_NAME%:%l_target_tag%
    docker tag %DOCKER_IMAGE_BASE_NAME%:%l_initial_tag%  %DOCKER_IMAGE_BASE_NAME%:%l_target_tag%
EXIT /B 0

:push
    SET l_initial_tag=%~1
    echo "push images %DOCKER_IMAGE_BASE_NAME%:%l_initial_tag%"
    docker push %DOCKER_IMAGE_BASE_NAME%:%l_initial_tag%

EXIT /B 0

:EOF
