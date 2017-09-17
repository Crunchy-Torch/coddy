# Framework configuration
spring.data.elasticsearch.cluster-nodes={{SPRING_ES_HOST|default("es:9300")}}

# Application configuration
{% if CODDY_VERSION is defined %}org.crunchytorch.coddy.version={{CODDY_VERSION}}{% endif %}

# Token configuration
{% if CODDY_JWT_SECRET is defined %}org.crunchytorch.coddy.jwt.secret={{CODDY_JWT_SECRET}}{% endif %}
{% if CODDY_JWT_SESSION_TIMEOUT_MIN is defined %}org.crunchytorch.coddy.jwt.session_timeout_minute={{CODDY_JWT_SESSION_TIMEOUT_MIN}}{% endif %}

# Admin and bot configuration
{% if CODDY_ADMIN_LOGIN is defined %}org.crunchytorch.coddy.admin.login={{CODDY_ADMIN_LOGIN}}{% endif %}
{% if CODDY_ADMIN_PASSWORD is defined %}org.crunchytorch.coddy.admin.password={{CODDY_ADMIN_PASSWORD}}{% endif %}
{% if CODDY_ADMIN_EMAIL is defined %}org.crunchytorch.coddy.admin.email={{CODDY_ADMIN_EMAIL}}{% endif %}