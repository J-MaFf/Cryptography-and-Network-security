import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class representing a Rainbow Table used for password cracking.
 */
public class RainbowTable {
    private Map<String, String> table = new HashMap<>();
    private int maxLength;

    /**
     * The table is generated with a maximum length specified by the user.
     */
    public RainbowTable(int maxLength) {
        this.maxLength = maxLength;
        generateTable(maxLength);
    }

    /**
     * This constructor generates a table of possible passwords and can be used to
     * crack hashed passwords.
     */
    public RainbowTable(List<String> possiblePasswords) {
        generateTable(possiblePasswords);
    }

    /**
     * Generates a rainbow table for passwords up to a specified maximum length.
     * 
     * @param maxLength the maximum length of passwords to include in the table
     */
    private void generateTable(int maxLength) {
        for (int length = 1; length <= maxLength; length++) {
            generateTable("", length);
        }
    }

    /**
     * Generates a rainbow table of MD5 hashes for all possible combinations of
     * characters of a given length.
     * The characters are limited to digits, uppercase letters, and lowercase
     * letters (Alphanumeric).
     * 
     * @param current   the current combination of characters being generated
     * @param remaining the number of characters remaining to be added to the
     *                  current combination
     */
    private void generateTable(String current, int remaining) {
        if (remaining == 0) {
            table.put(current, md5(current));
        } else {
            // ASCII values for digits
            for (int i = 48; i <= 57; i++) {
                generateTable(current + (char) i, remaining - 1);
            }
            // ASCII values for uppercase letters
            for (int i = 65; i <= 90; i++) {
                generateTable(current + (char) i, remaining - 1);
            }
            // ASCII values for lowercase letters
            for (int i = 97; i <= 122; i++) {
                generateTable(current + (char) i, remaining - 1);
            }
        }
    }

    // Generate a rainbow table of MD5 hashes for every string in an array
    public void generateTable(List<String> possiblePasswords) {
        for (String password : possiblePasswords) {
            table.put(password, md5(password));
        }
    }

    /**
     * Calculates the MD5 hash of the given password.
     *
     * @param password the password to hash
     * @return the MD5 hash of the password
     */
    private String md5(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // print table
    public void print() {
        for (Map.Entry<String, String> entry : table.entrySet()) {
            System.out.printf("%-20s %s%n", entry.getKey(), entry.getValue());
        }
        System.out.println("\n");
    }

    public Map<String, String> getTable() {
        return table;
    }
}