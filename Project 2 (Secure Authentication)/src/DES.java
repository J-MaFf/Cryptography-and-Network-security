import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DES {

    public static String DESencode(String text, String key) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {

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

    public static String DESdecode(String text, String key) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {

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

    public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
        String encryptedText = DESencode("IsaacLytle", "12345678");
        //String decryptedText = DESdecode(encryptedText, "1234567");

        System.out.println("Encrypted: " + encryptedText);
        //System.out.println("Decrypted: " + decryptedText);
    }
}

