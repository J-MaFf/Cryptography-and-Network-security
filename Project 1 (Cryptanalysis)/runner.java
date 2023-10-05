import java.util.Scanner;

public class runner {
    public static void main(String[] args) {
        System.out.println("Running program...");

        cipherText cipherText = new cipherText("JoeyCipherText.txt");
        String key = getKey();
        decrypt messageDecryption = new decrypt(cipherText.getCipherText(), key);
        // Output to text file (Keep spaces and line breaks)
        messageDecryption.toTextFile("JoeyPlainText.txt");
        System.out.println("Plain text: " + messageDecryption.plainText);
    }

    /**
     * Prompts the user to enter a key to decrypt the cipher text and returns the
     * entered key.
     * 
     * @return the key entered by the user
     */
    public static String getKey() {
        System.out.println("Enter the key to decrypt the cipher text: ");
        Scanner scanner = new Scanner(System.in);
        String key = scanner.nextLine();
        scanner.close();
        return key;
    }
}
