package Assignment3;
public class StreamCipher {
    
    public static void main(String[] args){
        test();
    }

    
    /**
     * Encrypts the given plaintext using the given key. The plaintext is in hex and the key is in binary.
     * 
     * @param keyInBinary the binary key string
     * @param plaintext the plaintext to encrypt
     * @return the encrypted ciphertext
     */

    public static String encrypt(String keyInBinary, String plaintext){
        String ciphertext = "";
        for(int i = 0; i < plaintext.length(); i++){
            if(plaintext.charAt(i) == ' '){
                ciphertext += " ";
                continue;
            }
            String fourBits = send4Bits(i * 4, keyInBinary);
            
            //System.out.println("PLAINTEXT FROM HEX TO BINARY:" + hexToBinary(plaintext.substring(i, i + 1)));

            String xorResult = XOR(hexToBinary(plaintext.substring(i, i + 1)), fourBits);
            int decimal = Integer.parseInt(xorResult, 2);
            String hexStr = Integer.toString(decimal, 16);
            ciphertext += hexStr;
        }
        return ciphertext.toUpperCase();
    }

    public static String decrypt(String keyInBinary, String ciphertext) {
    String plaintext = "";
    for (int i = 0; i < ciphertext.length(); i++) {
        if (ciphertext.charAt(i) == ' ') {
            plaintext += " ";
            continue;
        }
        String fourBits = send4Bits((i / 2) * 4, keyInBinary);
        String binary = hexToBinary(ciphertext.substring(i, i + 1));
        String xorResult = XOR(binary, fourBits);
        int decimal = Integer.parseInt(xorResult, 2);
        plaintext += Integer.toString(decimal, 16);
    }
    return plaintext.toUpperCase();
}

    /**
     * Generates a binary key from an integer array.
     * 
     * @param myArray the integer array to generate the key from
     * @return the binary key as a string
     */
    public static String generateKey(int[] myArray, int length){
        String keyInBinary = "";
        for(int i = 0; i < length ; i++){
            String binaryString = Integer.toBinaryString(myArray[i]);
            // Pad the binary string with leading zeros to ensure it has a length of 4
            while(binaryString.length() < 4) {
                binaryString = "0" + binaryString;
            }
            keyInBinary += binaryString;
        }
        return keyInBinary;
    }

    /**
     * Returns a 4-bit substring from the given binary key string starting at the specified index.
     * 
     * @param place the starting index of the 4-bit substring
     * @param keyInBinary the binary key string to extract the 4-bit substring from
     * @return the 4-bit substring from the binary key string
     */
    public static String send4Bits (int place, String keyInBinary){
        // get 4 bits from the key
       // place = place % 48;
        String fourBits = keyInBinary.substring(place, place + 4);
        return fourBits;

    }

    /**
     * Returns the XOR of two binary strings.
     * 
     * @param a the first binary string
     * @param b the second binary string
     * @return the XOR of the two binary strings
     */
    public static String XOR(String a, String b){
        String result = "";
        for(int i = 0; i < a.length(); i++){
            if(a.charAt(i) == b.charAt(i)){
                result += "0";
            } else {
                result += "1";
            }
        }
        return result;
    }

    public static String binaryToHex(String binary){
        String hex = "";
        for(int i = 0; i < binary.length(); i += 4){
            // check for a space
            if(binary.charAt(i) == ' '){
                hex += " ";
                i++;
            }
            String fourBits = binary.substring(i, i + 4);
            int decimal = Integer.parseInt(fourBits, 2);
            String hexStr = Integer.toString(decimal, 16);
            hex += hexStr;
        }
        hex = hex.toUpperCase();
        return hex;
    }

    public static String hexToBinary(String hex){
        String binary = "";
        for(int i = 0; i < hex.length(); i++){
            if(hex.charAt(i) == ' '){
                binary += " ";
                continue;
            }
            String fourBits = Integer.toBinaryString(Integer.parseInt(Character.toString(hex.charAt(i)), 16));
            while(fourBits.length() < 4){
                fourBits = "0" + fourBits;
            }
            binary += fourBits;
        }
        return binary;
    }

    public static void test(){
        lcg myLcg = new lcg(11, 0, 13, 1);
        String plaintext = "27 6F 65 7C 86 4A";
        int[] myArray = new int[plaintext.length()];
        for(int i = 0; i < myArray.length; i++){
            if(i == 0){
                myArray[i] = myLcg.getCurrentX();
                continue;
            }

            myArray[i] = myLcg.nextX();
        }
        System.out.println("LCG output: ");
        for(int i = 0; i < myArray.length; i++){
            System.out.println(myArray[i]);
        }

        String keyInBinary = generateKey(myArray, plaintext.length());
        System.out.println("Key in binary: " + keyInBinary + "\nSplit into 4-bit chunks: ");

        for(int i = 0; i < 12; i++){
            String fourBits = send4Bits(i * 4, keyInBinary);
            System.out.print(fourBits + " ");
        }
        System.out.println("\n");
        System.out.println("Encrypted value in binary: " + hexToBinary(encrypt(keyInBinary, plaintext)));
        System.out.println("Encrypted value in hex: " + encrypt(keyInBinary, plaintext));
        System.out.println("Decryption: " + decrypt(keyInBinary, "C9 08 26 29"));
        
    }
}
