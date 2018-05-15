# Framework configuration
spring.data.elasticsearch.cluster-nodes={{SPRING_ES_HOST|default("es:9300")}}

# Token configuration
{% if CODDY_SECURITY_JWT_SECRET is defined %}org.crunchytorch.coddy.security.jwt.secret={{CODDY_SECURITY_JWT_SECRET}}{% endif %}
{% if CODDY_SECURITY_JWT_SESSION_TIMEOUT is defined %}org.crunchytorch.coddy.security.jwt.session_timeout_minute={{CODDY_SECURITY_JWT_SESSION_TIMEOUT}}{% endif %}

# Admin and bot configuration
{% if CODDY_USER_ADMIN_LOGIN is defined %}org.crunchytorch.coddy.user.admin.login={{CODDY_USER_ADMIN_LOGIN}}{% endif %}
{% if CODDY_USER_ADMIN_PASSWORD is defined %}org.crunchytorch.coddy.user.admin.password={{CODDY_USER_ADMIN_PASSWORD}}{% endif %}
{% if CODDY_USER_ADMIN_EMAIL is defined %}org.crunchytorch.coddy.user.admin.email={{CODDY_USER_ADMIN_EMAIL}}{% endif %}