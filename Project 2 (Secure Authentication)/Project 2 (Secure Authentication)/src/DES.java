import javax.crypto.Cipher;

public class DES {

    public DES() { // Constructor
        try {
            Cipher desCipher = Cipher.getInstance("DES");
        } catch (Exception e) { // Catching all exceptions is bad practice
            System.out.println(e);
        }
    }
}
