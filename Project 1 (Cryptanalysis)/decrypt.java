public class decrypt {
    final String cipherText, key;
    String plainText;

    /**
     * Constructor for the decrypt class that takes a cipher text and a key as
     * input and decrypts the cipher text.
     * 
     * @param cipherText the cipher text to be decrypted
     * @param key        the key to decrypt the cipher text
     */
    public decrypt(String cipherText, String key) {
        this.cipherText = cipherText;
        this.key = key;
        this.plainText = decryptCipherText();
    }

    /**
     * Decrypts the monoalphebetic cipher text using the key and returns the plain
     * text.
     * 
     * @return the plain text
     */
    public String decryptCipherText() {
        letterPair[] letterPairs = new letterPair[this.key.length()];

        System.out.println("Letter pairs: ");

        for (int i = 0; i < this.key.length(); i++) {
            letterPairs[i] = new letterPair((char) (i + 'A'), this.key.charAt(i));
            System.out.println(letterPairs[i].letter1 + " " + letterPairs[i].letter2);
        }

        String plainText = "";

        // Decrypt the cipher text
        for (int i = 0; i < this.cipherText.length(); i++) {
            char cipherTextLetter = this.cipherText.charAt(i);
            for (int j = 0; j < letterPairs.length; j++) {
                // Letter 1 = cipher text letter, letter 2 = plain text letter
                if (cipherTextLetter == letterPairs[j].letter1) {
                    plainText += letterPairs[j].letter2;
                }
            }
        }

        return plainText;
    }
}
