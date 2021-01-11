package util;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * Utility class used to hash and verify passwords.
 */
public final class PasswordUtil {
    /**
     * Number of iterations for the hashing operation.
     */
    private static final int NB_ITERATIONS = 100000;

    /**
     * Hashing key length.
     */
    private static final int KEY_LENGTH = 256;

    /**
     * Encodes a given password.
     * @param password the password to hash.
     * @return a concatenation of the salt and the hashed password.
     */
    public static String encode(String password){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        char[] passwordCharacters = password.toCharArray();
        byte[] bytesHashed = hashPassword(passwordCharacters,salt);

        return toHex(salt) + ":" + toHex(bytesHashed);
    }

    /**
     * Hash a given password using a given salt.
     * @param password the password to hash.
     * @param salt the salt to use.
     * @return the hashed password.
     */
    private static byte[] hashPassword( final char[] password, final byte[] salt) {

        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );
            PBEKeySpec spec = new PBEKeySpec( password, salt, NB_ITERATIONS, KEY_LENGTH);
            SecretKey key = skf.generateSecret( spec );
            return key.getEncoded( );
        } catch ( NoSuchAlgorithmException | InvalidKeySpecException e ) {
            throw new RuntimeException( e );
        }
    }

    /**
     * Checks if a given password corresponds to hashed real password.
     * @param password the password to check.
     * @param storedPassword the good password.
     * @return boolean that indicates if the given password matches the good one.
     */
    public static boolean validatePassword(String password, String storedPassword) {

        String[] params = storedPassword.split(":");

        byte[] salt = fromHex(params[0]);
        byte[] storedHash = fromHex(params[1]);

        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, NB_ITERATIONS, KEY_LENGTH);
            byte[] hash = skf.generateSecret(spec).getEncoded();

            int diff = storedHash.length ^ hash.length;
            for(int i = 0; i < storedHash.length && i < hash.length; i++)
            {
                diff |= storedHash[i] ^ hash[i];
            }
            return diff == 0;

        } catch ( NoSuchAlgorithmException | InvalidKeySpecException e ) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Converts a byte array to String (hexadecimal).
     * @param array bytes to convert.
     * @return the converted hexadecimal String.
     */
    private static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0)
        {
            return String.format("%0"  +paddingLength + "d", 0) + hex;
        }else{
            return hex;
        }
    }

    /**
     * Converts a String (hexadecimal) into bytes array.
     * @param hex the hexadecimal String.
     * @return the converted bytes array.
     */
    private static byte[] fromHex(String hex) {
        byte[] bytes = new byte[hex.length() / 2];
        for(int i = 0; i<bytes.length ;i++)
        {
            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }

}
