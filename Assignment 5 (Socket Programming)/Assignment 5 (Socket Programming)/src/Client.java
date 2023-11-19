import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket socket = null;
        try {
            // Create a socket
            socket = new Socket("127.0.0.1", 51234);
            // Create print writer to send data over network
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            // Create a buffered reader to recive data over network
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Create a Scanner to read user input
            Scanner scanner = new Scanner(System.in);
            String request = "";
            while (!request.equalsIgnoreCase("q") && !request.equalsIgnoreCase("quit")) {
                System.out.println("Enter a request: ");
                request = scanner.nextLine();
                // Send a request
                writer.println(request);
                // Listen for response
                System.out.println("Waiting for response...");
                String response = reader.readLine();
                System.out.println("Response: " + response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Closing socket and terminating program.");
            // Close the socket
            socket.close();
        }

    }
}
