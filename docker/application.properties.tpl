# Framework configuration
spring.data.elasticsearch.cluster-nodes=es:9300{{SPRING_ES_HOST|default(es:9300)}}{% endif %}

# Application configuration
{% if CODDY_VERSION is defined %}org.crunchytorch.coddy.version={{CODDY_VERSION}}{% endif %}
{% if CODDY_JWT_SECRET is defined %}org.crunchytorch.coddy.jwt.secret={{CODDY_JWT_SECRET}}{% endif %}
{% if CODDY_JWT_SESSION_TIMEOUT_MIN %}is defined %}org.crunchytorch.coddy.jwt.session_timeout_minute={{CODDY_JWT_SESSION_TIMEOUT_MIN}}{% endif %}
