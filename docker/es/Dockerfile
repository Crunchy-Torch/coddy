FROM elasticsearch:2.4.5

COPY user.bulk /user.bulk
COPY mapping/account.json /
COPY init.sh /init.sh

ENTRYPOINT []
CMD ["/init.sh"]