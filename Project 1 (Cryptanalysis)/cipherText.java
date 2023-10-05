
/**
 * This Object will be used to store the cipher text from a txt file. The
 * developer will create a new object with the file name and the object will
 * store the cipher text in a string. The developer will then be able to call
 * the object to get the cipher text.
 **/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class cipherText {
    final StringBuilder cipherText;
    final letterFrequencyObject[] letterFrequency, sortedLetterFrequency;

    /**
     * Constructor for the cipherText class that takes a file name as input and
     * reads the cipher text from the file.
     * 
     * @param fileName the name of the file containing the cipher text.
     */
    public cipherText(String fileName) {
        this.cipherText = readCipherText(fileName);
        this.letterFrequency = frequencyAnalysis();
        this.sortedLetterFrequency = sortFrequency();
    }

    /**
     * Reads the cipher text from a file and returns it as a string,
     * while preserving spaces and new lines.
     * 
     * @param fileName the name of the file containing the cipher text
     * @return the cipher text as a string
     */
    private StringBuilder readCipherText(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            StringBuilder cipherText = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                cipherText.append(line).append("\n"); // This will preserve line breaks
            }
            reader.close();

            // Output the cipher text to uniquie file
            String outputFileName = "JoeyCipherTextTest.txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));
            writer.write(cipherText.toString());
            writer.close();
            System.out.println("Cipher text written to " + outputFileName);

            return cipherText;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Performs frequency analysis on the cipher text and returns the results as
     * an array of letterFrequencyObject objects, where index 0 represents the
     * frequency of 'A', and
     * the last index (25) represents the frequency of 'Z'. The frequencies are
     * also printed to the console.
     * 
     * @return an array of letterFrequencyObject objects representing the frequency
     *         of each letter
     **/
    public letterFrequencyObject[] frequencyAnalysis() {
        System.out.println("Performing frequency analysis...");
        letterFrequencyObject[] letterFrequency = new letterFrequencyObject[26];
        // Initialize array of letterFrequencyObjects with correct letters and frequency
        // 0
        for (int i = 0; i < letterFrequency.length; i++) {
            letterFrequency[i] = new letterFrequencyObject((char) (i + 'A'), 0);
        }
        // Gather frequency data
        for (int i = 0; i < this.cipherText.length(); i++) {
            char letter = this.cipherText.charAt(i);
            if (letter >= 'A' && letter <= 'Z') {
                letterFrequency[letter - 'A'].frequency++;
            } else {
                // skip non-alphabetical characters
            }
        }
        // Print frequency data
        for (int i = 0; i < letterFrequency.length; i++) {
            System.out.println(letterFrequency[i].letter + ": " + letterFrequency[i].frequency);
        }
        return letterFrequency;
    }

    /**
     * Sorts the frequency data from highest to lowest using an array of
     * letterFrequencyObject
     * objects and returns the sorted array of letterFrequencyObjects.
     * 
     * @return the sorted array of letter frequencies
     */

    public letterFrequencyObject[] sortFrequency() {
        System.out.println("Sorting frequency data...");
        letterFrequencyObject[] sortedLetterFrequency = new letterFrequencyObject[26];
        letterFrequencyObject[] letterFrequency = this.letterFrequency;

        // Sort the objects in the array while keeping the original array intact.
        for (int i = 0; i < letterFrequency.length; i++) {
            int maxIndex = 0;
            for (int j = 0; j < letterFrequency.length; j++) {
                if (letterFrequency[j].frequency > letterFrequency[maxIndex].frequency) {
                    maxIndex = j;
                }
            }
            sortedLetterFrequency[i] = letterFrequency[maxIndex];
            letterFrequency[maxIndex] = new letterFrequencyObject(sortedLetterFrequency[i].letter, -1);

        }

        // Print frequency data
        for (int i = 0; i < letterFrequency.length; i++) {
            System.out.println(sortedLetterFrequency[i].letter + ": " + sortedLetterFrequency[i].frequency);
        }
        return sortedLetterFrequency;

    }
}
