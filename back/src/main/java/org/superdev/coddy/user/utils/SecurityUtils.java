package org.superdev.coddy.user.utils;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Random;

/**
 * All method used to check and generate the user's password. This class cannot be instantiated
 */
public class SecurityUtils {

    private static final int SALT_SIZE = 32;
    private static final int KEY_LENGTH = 512;
    private static final int ITERATIONS = 1000;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA512";

    private SecurityUtils() {
        // this is an utility class. You do not need to instantiate it.
    }

    /**
     * This method will hash the password and salt combination. Be careful, this method will erase the password after hashed it.
     * You must be sure that you do not need it after using this method.
     *
     * @param password the password who needs to be hashed
     * @param salt     some list of <code>byte</code> which will be included in the password
     * @return a hash of the password and salting combination.
     */
    public static byte[] hash(char[] password, byte[] salt) {
        final PBEKeySpec spec = new PBEKeySpec(password, salt, SecurityUtils.ITERATIONS, SecurityUtils.KEY_LENGTH);

        try {
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(SecurityUtils.ALGORITHM);
            SecretKey key = secretKeyFactory.generateSecret(spec);
            return key.getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new SecurityException(e);
        } finally {
            // erase the password in the char array in order to not retrieve it in the java memory
            spec.clearPassword();
        }
    }

    /**
     * @return a random salt
     */
    public static byte[] generateSalt() {
        final Random r = new SecureRandom();
        byte[] salt = new byte[SecurityUtils.SALT_SIZE];
        r.nextBytes(salt);
        return salt;
    }

    /**
     * This method will check the given password and compare it with the one stored in the database.
     * Be careful, this method will erase the password passed in parameter, after tested it.
     * You must be sure that you do not need it after using this method.
     *
     * @param password     the password to be tested
     * @param salt         the salt previously retrieve in the database
     * @param expectedHash the password expected after applying the {@link SecurityUtils#hash(char[], byte[]) hashing method}
     * @return <code>true</code> if the password passed in parameter matched the hashing password stored in the database.
     * <code>false</code> if not.
     */
    public static boolean isExpectedPassword(char[] password, byte[] salt, byte[] expectedHash) {
        if (password == null) {
            return false;
        }
        final byte[] pwdHash = SecurityUtils.hash(password, salt);
        final int length = pwdHash.length;

        // erase the password in the char array in order to not retrieve it in the java memory
        Arrays.fill(password, Character.MIN_VALUE);

        if (length != expectedHash.length) {
            return false;
        }

        int i = 0;
        boolean result = true;

        while ((i < length) && result) {
            result = pwdHash[i] == expectedHash[i];
            i++;
        }

        return result;
    }

}
