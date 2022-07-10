package tn.esprit.asi.Utils;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

@Slf4j
public class PasswordUtils {

    private static final Random RANDOM = new SecureRandom();
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;
    private static final String SALT = "EqdmPh53c9x33Eyg#XpTpcoJvc4VXLK!";

    private static byte[] hash(char[] password) {
        PBEKeySpec spec = new PBEKeySpec(password, SALT.getBytes(), ITERATIONS, KEY_LENGTH);
        Arrays.fill(password, Character.MIN_VALUE);
        byte key[] = null;
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            key = skf.generateSecret(spec).getEncoded();
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            spec.clearPassword();
        }
        return key;
    }

    public static String generateSecurePassword(String password) {
        String returnValue = null;
        byte[] securePassword = hash(password.toCharArray());
        returnValue = Base64.getEncoder().encodeToString(securePassword);

        return returnValue;
    }

    public static boolean verifyUserPassword(String providedPassword, String securedPassword) {
        boolean returnValue = false;
        String newSecurePassword = generateSecurePassword(providedPassword);
        returnValue = newSecurePassword.equalsIgnoreCase(securedPassword);

        return returnValue;
    }
}
