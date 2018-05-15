FROM alpine:edge

MAINTAINER Augustin Husson <husson.augustin@gmail.com>

ARG NGINX_VERSION=1.14.0
ARG JAVA_VERSION=8.161.12-r0

RUN apk update && \
    apk add --no-cache \
        bash \
        openjdk8-jre=${JAVA_VERSION} \
        git \
        tar \
        g++ \
        pcre \
        pcre-dev \
        zlib \
        zlib-dev \
        make \
        curl \
        python \
        py-pip && \
    # install envtpl
    pip install envtpl && \
    pip install supervisor supervisor-stdout && \
    # install and configure nginx
    curl "http://nginx.org/download/nginx-${NGINX_VERSION}.tar.gz" -o nginx-${NGINX_VERSION}.tar.gz && \
    tar zxvf nginx-${NGINX_VERSION}.tar.gz && \
    cd nginx-${NGINX_VERSION} && \
    git clone --recursive https://github.com/google/ngx_brotli.git && \
    ./configure \
        --with-cc-opt='-g -O2 -fPIE -fstack-protector-strong -Wformat -Werror=format-security -Wdate-time -D_FORTIFY_SOURCE=2' \
        --with-ld-opt='-Wl,-Bsymbolic-functions -fPIE -pie -Wl,-z,relro -Wl,-z,now' \
        --prefix=/usr/share/nginx \
        --conf-path=/etc/nginx/nginx.conf \
        --http-log-path=/var/log/nginx/access.log \
        --error-log-path=/var/log/nginx/error.log \
        --lock-path=/var/lock/nginx.lock \
        --pid-path=/run/nginx.pid \
        --http-client-body-temp-path=/var/lib/nginx/body \
        --http-proxy-temp-path=/var/lib/nginx/proxy \
        --with-debug \
        --with-http_gunzip_module \
        --with-http_gzip_static_module \
        --with-threads \
        --add-module=$(pwd)/ngx_brotli && \
    make && make install && \
    ln -s /usr/share/nginx/sbin/nginx /usr/sbin/nginx && \
    cp -r conf /etc/nginx && \
    mkdir -p /etc/nginx/sites-available /etc/nginx/sites-enabled && \
    mkdir -p /var/lib/nginx && \
    # clean image
    cd .. && rm -fr nginx-${NGINX_VERSION} && \
    apk del \
      git \
      tar \
      make \
      g++ \
      zlib-dev \
      pcre-dev \
      curl && \
    rm -rf /var/cache/apk/*

COPY docker/supervisord.conf /etc/supervisor/supervisord.conf
COPY docker/nginx.conf /etc/nginx/
COPY docker/nginx.vhost.conf /etc/nginx/sites-available/coddy.conf
RUN ln -s /etc/nginx/sites-available/coddy.conf /etc/nginx/sites-enabled/coddy.conf

ENV CODDY_CONF_DIRECTORY="/app/conf"

COPY docker/application.properties.tpl ${CODDY_CONF_DIRECTORY}/application.properties.tpl
COPY back/build/libs/coddy-back.jar /app/back/coddy-back.jar
COPY front/dist /app/front

WORKDIR /app

EXPOSE 80

COPY docker/entrypoint.sh /app/entrypoint.sh
CMD ["/bin/bash", "/app/entrypoint.sh"]