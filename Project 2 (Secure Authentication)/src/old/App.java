import java.util.Scanner;

public class App {
    /**
     * Enter the key(s) which will be used for en/decryption (See the Key
     * Distribution section for more info. Whether the user enters one/two/three
     * keys will depend on the cryptosystem you choose)
     * Create a new account... (username must be unique, both must be at least 6
     * characters long) Or log in to an existing account (username and password must
     * be correct) and display a simple welcome message signifying a successful
     * log-in, End the program
     */

    /**
     * Main method
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // Enter the key(s) which will be used for en/decryption
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the key(s) which will be used for en/decryption");
        String key = scanner.nextLine();
        while (key.length() < 8) {
            System.out.println("Key must be at least 8 characters long, plesae try again");
            key = scanner.nextLine();
        }
        System.out.println("Here is the key you entered: " + key);

        System.out.println("Would you like to create a new account or log in to an existing account?");
        System.out.println("Enter 1 to create a new account");
        System.out.println("Enter 2 to log in to an existing account");
        String choice = scanner.nextLine();
        while (!choice.equals("1") && !choice.equals("2")) {
            System.out.println("Invalid choice, please try again");
            choice = scanner.nextLine();
        }
        if (choice == "1") {
            // Create a new account
            User newUser = new User();
            newUser.createAccount();
            String usernametext = newUser.getUsername();
            User username = new User();
        } else {
            // Log in to an existing account
        }

    }

}
