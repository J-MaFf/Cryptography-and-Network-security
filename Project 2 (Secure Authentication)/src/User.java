import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class User {
    private String username;
    private String password;
    private String key;
    private Scanner scanner;

    public User(String key) {
        this.username = "";
        this.password = "";
        this.key = key;
        this.scanner = new Scanner(System.in);
    }

    public void createAccount() throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException,
            InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        // Create a new account
        System.out.println("Enter a username: ");
        String username = getScanner().nextLine();
        String encryptedUser = desencode(username, key);
        while (username.length() < 6) {
            System.out.println("Username must be at least 6 characters long, please try again");
            username = getScanner().nextLine();
        }
        // Check if username is unique
        // If not, ask user to enter a different username
        while (isPresentInFile(encryptedUser) == true) {
            System.out.println("Username must be Unique");
            username = getScanner().nextLine();
            encryptedUser = desencode(username, key);
        }

        // If yes, continue
        setUsername(username);
        writeToTextFile(encryptedUser);

        System.out.println("Enter a password: ");
        String password = getScanner().nextLine();
        String encryptedPassword = desencode(password, key);
        while (password.length() < 6) {
            System.out.println("Password must be at least 6 characters long, please try again");
            password = getScanner().nextLine();
        }
        // do hashed
        writeToTextFile(getMD5Hash(encryptedPassword));
        setPassword(password);

    }

    public void login() throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException,
            InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        System.out.println("Enter a username: ");
        String username = getScanner().nextLine();
        String encryptedUser = desencode(username, key);
        System.out.println("Enter a password: ");
        String password = getScanner().nextLine();
        String encryptedPassword = desencode(password, key);
        if (isPresentInFile(encryptedUser) == true) {
            if (isPresentInFile(getMD5Hash(encryptedPassword)) == true) {
                System.out.println("Good Evening");
            } else {
                System.out.println("Failed To Login");
            }
        } else {
            System.out.println("Failed Username or password");
        }
    }

    // Getters
    public String getUsername() {
        return this.username;
    }

    public String getKey() {
        return this.key;
    }

    public String getPassword() {
        return this.password;
    }

    public Scanner getScanner() {
        return this.scanner;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
        // Check if username is unique
        // If not, ask user to enter a different username
        // If yes, continue

    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static boolean isPresentInFile(String targetString) {
        try (BufferedReader br = new BufferedReader(new FileReader("src\\Users.txt"))) {
            String line;

            // Read each line from the file
            while ((line = br.readLine()) != null) {
                if (line.contains(targetString)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void writeToTextFile(String content) {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("src\\Users.txt", true))) {
            // Write the content to the file
            writer.write(content);
            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String desencode(String text, String key)
            throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException,
            NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {

        byte[] Message = text.getBytes("UTF-8");

        SecretKeyFactory KeyFactory = SecretKeyFactory.getInstance("DES");
        byte[] Pasword_bytes = key.getBytes("UTF-8");
        DESKeySpec Password_DESkey = new DESKeySpec(Pasword_bytes);
        SecretKey DESKey = KeyFactory.generateSecret(Password_DESkey);

        Cipher myCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

        myCipher.init(Cipher.ENCRYPT_MODE, DESKey);
        byte[] myEncryptedBytes = myCipher.doFinal(Message);
        String encryptedData = Base64.getEncoder().encodeToString(myEncryptedBytes);

        return encryptedData;
    }

    public static String desdecode(String text, String key)
            throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException,
            NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {

        byte[] Message = Base64.getDecoder().decode(text);

        SecretKeyFactory KeyFactory = SecretKeyFactory.getInstance("DES");
        byte[] Pasword_bytes = key.getBytes("UTF-8");
        DESKeySpec Password_DESkey = new DESKeySpec(Pasword_bytes);
        SecretKey DESKey = KeyFactory.generateSecret(Password_DESkey);

        Cipher myCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

        myCipher.init(Cipher.DECRYPT_MODE, DESKey);
        byte[] myDecryptedBytes = myCipher.doFinal(Message);

        String decryptedData = new String(myDecryptedBytes, "UTF-8");
        System.out.println(decryptedData);
        return decryptedData;
    }

    public static String getMD5Hash(String input) {
        try {
            MessageDigest hashed = MessageDigest.getInstance("MD5");
            hashed.update(input.getBytes());
            byte[] hashBytes = hashed.digest();
            StringBuilder hexStringBuilder = new StringBuilder();
            for (byte b : hashBytes) {
                hexStringBuilder.append(String.format("%02x", b));
            }

            return hexStringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
