package org.superdev.coddy.user.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.KeyGenerator;
import java.security.NoSuchAlgorithmException;

public class JWTUtils {

    private static final String ALGORITHM = "AES";

    private static final Logger LOGGER = LoggerFactory.getLogger(JWTUtils.class);

    private JWTUtils() {
        // this is an utility class. You do not need to instantiate it.
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
     * @return a random key with 512 characters
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
        keyGenerator.init(512);
        return keyGenerator.generateKey().getEncoded();
    }
}
