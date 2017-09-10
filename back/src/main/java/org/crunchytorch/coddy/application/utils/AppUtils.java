package org.crunchytorch.coddy.application.utils;

public class AppUtils {

    private static final String PREFIX_FIELD_CONF = "org.crunchytorch.coddy";

    public static final String CONF_VERSION = PREFIX_FIELD_CONF + ".version";

    public static final String CONF_ADMIN_LOGIN = PREFIX_FIELD_CONF + ".admin.login";

    public static final String CONF_ADMIN_PASSWORD = PREFIX_FIELD_CONF + ".admin.password";

    public static final String CONF_ADMIN_EMAIL = PREFIX_FIELD_CONF + "admin.email";

    public static final String CONF_BOT_LOGIN = PREFIX_FIELD_CONF + ".bot.login";

    public static final String CONF_BOT_PASSWORD = PREFIX_FIELD_CONF + ".bot.password";

    /**
     * JWT secret configuration. It allows the possibility to erase the default secret key.
     */
    public static final String CONF_JWT_SECRET = PREFIX_FIELD_CONF + ".jwt.secret";

    /**
     * JWT session in minute. It allows the possibility to erase the default time session.
     */
    public static final String CONF_JWT_SESSION_TIMEOUT_MINUTE = PREFIX_FIELD_CONF + ".jwt.session_timeout_minute";

    /**
     * The path parameter used in the user api when an user account is accessed.
     */
    public static final String API_USER_LOGIN_PATH_PARAM = "login";

    private AppUtils(){
        // this is an utility class. You do not need to instantiate it.
    }
}
