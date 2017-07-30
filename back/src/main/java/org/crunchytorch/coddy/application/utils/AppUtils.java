package org.crunchytorch.coddy.application.utils;

public class AppUtils {

    private static final String PREFIX_FIELD_CONF = "org.crunchytorch.coddy";

    /**
     * JWT secret configuration. It allows the possibility to erase the default secret key.
     */
    public static final String JWT_SECRET = PREFIX_FIELD_CONF + ".jwt.secret";

    /**
     * JWT session in minute. It allows the possibility to erase the default time session.
     */
    public static final String JWT_SESSION_TIMEOUT_MINUTE = PREFIX_FIELD_CONF + ".jwt.session_timeout_minute";

    /**
     * The path parameter used in the user api when an user account is accessed.
     */
    public static final String API_USER_LOGIN_PATH_PARAM = "login";
}
