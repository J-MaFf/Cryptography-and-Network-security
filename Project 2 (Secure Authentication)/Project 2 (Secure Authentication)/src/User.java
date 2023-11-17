import java.util.Scanner;

public class User {
    private String username;
    private String password;
    private String key;
    private Scanner scanner;

    public User() {
        this.username = "";
        this.password = "";
        this.key = "";
        this.scanner = new Scanner(System.in);
    }

    public User(String username, String password, String key) {
        this.username = username;
        this.password = password;
        this.key = key;
    }

    public void createAccount() {
        // Create a new account
        System.out.println("Enter a username: ");
        String username = getScanner().nextLine();
        while (username.length() < 6) {
            System.out.println("Username must be at least 6 characters long, please try again");
            username = getScanner().nextLine();
        }
        // Check if username is unique
        // If not, ask user to enter a different username
        // If yes, continue
        setUsername(username);

        System.out.println("Enter a password: ");
        String password = getScanner().nextLine();
        while (password.length() < 6) {
            System.out.println("Password must be at least 6 characters long, please try again");
            password = getScanner().nextLine();
        }
        setPassword(password);

    }

    public void login() {
        // Log in to an existing account
    }

    // Getters
    public String getUsername() {
        return this.username;
    }

    public String getKey() {
        return this.key;
    }

    public String getPassword() {
        return this.password;
    }

    public Scanner getScanner() {
        return this.scanner;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
        // Check if username is unique
        // If not, ask user to enter a different username
        // If yes, continue

    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
