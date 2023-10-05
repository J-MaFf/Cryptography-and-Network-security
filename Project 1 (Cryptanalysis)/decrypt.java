import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class decrypt {
    final String key;
    final cipherText decryptThis;
    StringBuilder plainText;

    /**
     * Constructor for the decrypt class that takes a cipher text and a key as
     * input and decrypts the cipher text.
     * 
     * @param cipherText the cipher text to be decrypted
     * @param key        the key to decrypt the cipher text
     * @throws IOException
     */
    public decrypt(cipherText cipherText, String key) throws IOException {
        this.decryptThis = cipherText;
        this.key = key;
        this.plainText = decryptCipherText();
    }

    /**
     * Decrypts the monoalphebetic cipher text using the key and returns the plain
     * text.
     * 
     * @return the plain text
     * @throws IOException
     */
    public StringBuilder decryptCipherText() {
        letterPair[] letterPairs = new letterPair[this.key.length()];

        System.out.println("Letter pairs: ");

        for (int i = 0; i < this.key.length(); i++) {
            letterPairs[i] = new letterPair((char) (i + 'A'), this.key.charAt(i));
            System.out.println(letterPairs[i].letter1 + " " + letterPairs[i].letter2);
        }

        StringBuilder plainText = new StringBuilder();

        // Decrypt the cipher text

        // Formating is a top priority, so I'm going to do this the long way
        for (int i = 0; i < this.decryptThis.cipherText.length(); i++) {
            char cipherTextLetter = this.decryptThis.cipherText.charAt(i);
            if (cipherTextLetter < 'A' || cipherTextLetter > 'Z') {
                plainText.append(cipherTextLetter);
            } else {
                for (int j = 0; j < letterPairs.length; j++) {
                    // Letter 1 = cipher text letter, letter 2 = plain text letter
                    if (cipherTextLetter == letterPairs[j].letter1) {
                        plainText.append(letterPairs[j].letter2);
                    }
                }
            }

        }
        // Output the PLAIN text to uniquie file
        try {
            String outputFileName = "JoeyPlainTextTest.txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));
            writer.write(plainText.toString());// check this
            writer.close();
            System.out.println("Plain text written to " + outputFileName);
            return plainText;
        } catch (Exception e) {
            System.out.println("Error writing plain text to file.");
            e.printStackTrace();
            return plainText;
        }
    }
}
