FROM ubuntu:16.04

MAINTAINER Augustin Husson <husson.augustin@gmail.com>

ARG NGINX_VERSION=1.13.5

# compilation of nginx to have the latest version and the brotli module
# install java too, to minimize the number of layers
RUN apt-get update && \
        apt-get install -y --no-install-recommends \
                python-setuptools \
                python-pip \
                python-wheel \
                git \
                curl \
                default-jre \
                build-essential \
                ca-certificates \
                tar \
                unzip \
                libpcre3 \
                zlib1g \
                libssl1.0.0 \
                libxml2 \
                libxslt1.1 \
                libgd3 \
                geoip-bin \
                libgeoip1 \
                libpcre3-dev \
                zlib1g-dev \
                libssl-dev \
                libxml2-dev \
                libxslt-dev \
                libgd-dev \
                libgeoip-dev && \
        pip install supervisor supervisor-stdout && \
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
                --http-fastcgi-temp-path=/var/lib/nginx/fastcgi \
                --http-proxy-temp-path=/var/lib/nginx/proxy \
                --http-scgi-temp-path=/var/lib/nginx/scgi \
                --http-uwsgi-temp-path=/var/lib/nginx/uwsgi \
                --with-debug \
                --with-pcre-jit \
                --with-http_ssl_module \
                --with-http_stub_status_module \
                --with-http_realip_module \
                --with-http_auth_request_module \
                --with-http_addition_module \
                --with-http_dav_module \
                --with-http_geoip_module \
                --with-http_gunzip_module \
                --with-http_gzip_static_module \
                --with-http_image_filter_module \
                --with-http_v2_module \
                --with-http_sub_module \
                --with-http_xslt_module \
                --with-stream \
                --with-stream_ssl_module \
                --with-mail \
                --with-mail_ssl_module \
                --with-threads \
                --add-module=$(pwd)/ngx_brotli && \
        make && make install && \
        ln -s /usr/share/nginx/sbin/nginx /usr/local/sbin/nginx && \
        cp -r conf /etc/nginx && \
        mkdir -p /etc/nginx/sites-available /etc/nginx/sites-enabled && \
        mkdir -p /var/lib/nginx && \
        cd .. && rm -fr nginx-${NGINX_VERSION} && \
        apt-get remove -y --purge \
                build-essential \
                gcc \
                gcc-5 \
                zlib1g-dev \
                libpcre3-dev \
                libssl-dev \
                libxml2-dev \
                libxslt1-dev \
                libgd-dev \
                libgeoip-dev && \
        apt-get autoremove -y && \
        apt-get clean && \
        rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

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