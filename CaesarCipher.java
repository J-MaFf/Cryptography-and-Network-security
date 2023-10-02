/**
 * Assignment 1: Caesar Cipher (Part 1)
 * By: Joey Maffiola
 * Date: 9/18/2023
 */
public class CaesarCipher {
    /**
     * Runs part 1 of the assignment
     * 
     * @param args
     */
    public static void main(String[] args) {

        // Part 1

        String cipherText1 = "TUZBKXEYKIAXK";
        String cipherText2 = "TSJLCPYZJCRMZPSRCDMPACYRRYAIQ";

        String plainText1[] = new String[25];
        System.out.println("\nKey 1 possibilities:\n");
        for (int i = 0; i < 25; i++) {
            plainText1[i] = decrypt(i, cipherText1);
            System.out.println("Key " + i + " results in " + plainText1[i]);
        }

        System.out.println("\nIt seems to me that the message is \"NOTVERYSECURE\", therefore the key is 20.");

        System.out.println("\nKey 2 possibilities:\n");
        String plainText2[] = new String[25];
        for (int i = 0; i < 25; i++) {
            plainText2[i] = decrypt(i, cipherText2);
            System.out.println("Key " + i + " results in " + plainText2[i]);
        }
        System.out.println(
                "\nIt seems to me that the message is \"VULNERABLETOBRUTEFORCEATTACKS\", therefore the key is 2.");

    }

    /**
     * Decrypts a given cipher text with a given key
     * 
     * @param key
     * @param cipherText
     * @return plainText
     */
    public static String decrypt(int key, String cipherText) {
        String plainText = "";
        for (int i = 0; i < cipherText.length(); i++) {
            char currentLetter = cipherText.charAt(i);
            plainText += shiftChar(key, currentLetter);
        }
        return plainText;
    }

    /**
     * Shifts a character by a given key
     * 
     * @param key
     * @param currentChar
     * @return shiftedChar
     */
    public static char shiftChar(int key, char currentChar) {
        int shiftedCharAscii = (int) currentChar + key;

        if (shiftedCharAscii > 90) {
            shiftedCharAscii = 65 + (shiftedCharAscii - 91) % 26;
        } else if (shiftedCharAscii < 65) {
            shiftedCharAscii = 91 - (65 - shiftedCharAscii) % 26;
        }

        return (char) shiftedCharAscii;
    }

}
