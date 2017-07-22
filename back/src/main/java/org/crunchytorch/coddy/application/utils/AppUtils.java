package org.crunchytorch.coddy.application.utils;

public class AppUtils {

    private static final String PREFIX_FIELD_CONF = "org.crunchytorch.coddy";

    //jwt fields configuration
    public static final String JWT_SECRET = PREFIX_FIELD_CONF + ".jwt.secret";
    public static final String JWT_SESSION_TIMEOUT_MINUTE = PREFIX_FIELD_CONF + ".jwt.session_timeout_minute";
}
