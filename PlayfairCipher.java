public class PlayfairCipher {
    public static void main(String[] args) {
        String plaintext = "HELLOWORLD";
        String key = "PASSWORD";
        String ciphertext = encrypt(plaintext, key);
        System.out.println(plaintext + " with key " + key + "encrypts to " + ciphertext + ".");
    }

    public static String encrypt(String plaintext, String key) {
        int[][] keyMatrix = generateKeyMatrix(key);
        String ciphertext = "";

    }

    public static int[][] generateKeyMatrix(String key) {
        int[][] keyMatrix = new int[5][5];
        int[] alphabet = new int[26];
        int alphabetIndex = 0;
        for (int i = 0; i < key.length(); i++) {
            int currentLetter = (int) key.charAt(i);
            if (currentLetter == 74) {
                currentLetter = 73;
            }
            if (alphabet[currentLetter - 65] == 0) {
                alphabet[currentLetter - 65] = 1;
                keyMatrix[alphabetIndex / 5][alphabetIndex % 5] = currentLetter;
                alphabetIndex++;
            }
        }
        for (int i = 0; i < 26; i++) {
            if (alphabet[i] == 0) {
                keyMatrix[alphabetIndex / 5][alphabetIndex % 5] = i + 65;
                alphabetIndex++;
            }
        }

        return keyMatrix;

    }
}
