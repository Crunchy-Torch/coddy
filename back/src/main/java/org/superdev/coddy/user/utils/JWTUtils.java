package org.superdev.coddy.user.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.KeyGenerator;
import java.security.NoSuchAlgorithmException;

/**
 * All method and enum used inside the jwt package. This class cannot be instantiated
 */
public class JWTUtils {

    private static final String ALGORITHM = "AES";

    private static final Logger LOGGER = LoggerFactory.getLogger(JWTUtils.class);

    private JWTUtils() {

    }

    /**
     * define all fields in the jwt token payload :
     * <p>
     * {@code
     * {
     * "login": "jdoe",
     * "firstname": "John",
     * "nbf": 1479975670,
     * "permissions": ["write","read"],
     * "exp": 1479976870,
     * "lastname": "Doe"
     * }
     * }
     * </p>
     */
    public enum PayloadFields {
        LOGIN("login"),
        FIRST_NAME("firstname"),
        LAST_NAME("lastname"),
        PERMISSIONS("permissions"),
        NOT_BEFORE("nbf"),
        EXPIRATION("exp");

        private final String name;

        PayloadFields(final String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }

    /**
     * define the personal property which will set and get in the request context {@link javax.ws.rs.container.ContainerRequestContext}
     */
    public enum RequestProperty {
        PRINCIPAL("principal");

        private final String property;

        RequestProperty(String property) {
            this.property = property;
        }

        public String getProperty() {
            return property;
        }
    }

    /**
     * @return a random key with 256 characters
     */
    public static byte[] generateSecret() {
        KeyGenerator keyGenerator;
        try {
            keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.debug(e.getMessage(), e);
            LOGGER.error("Intern algorithm to create secret key doesn't exist");
            return new byte[]{};
        }
        keyGenerator.init(256);
        return keyGenerator.generateKey().getEncoded();
    }
}
