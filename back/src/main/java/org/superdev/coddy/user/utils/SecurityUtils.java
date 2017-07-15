package org.superdev.coddy.user.utils;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

public class SecurityUtils {

    private static final int SALT_SIZE = 32;
    private static final int KEY_LENGTH = 512;
    private static final int ITERATIONS = 1000;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA512";

    private SecurityUtils() {

    }

    public static byte[] hash(char[] password, byte[] salt) {
        try {
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(SecurityUtils.ALGORITHM);
            PBEKeySpec spec = new PBEKeySpec(password, salt, SecurityUtils.ITERATIONS, SecurityUtils.KEY_LENGTH);
            SecretKey key = secretKeyFactory.generateSecret(spec);
            return key.getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new SecurityException(e);
        }
    }

    public static byte[] generateSalt() {
        final Random r = new SecureRandom();
        byte[] salt = new byte[SecurityUtils.SALT_SIZE];
        r.nextBytes(salt);
        return salt;
    }

}
