import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class App {
    public static void main(String[] args) throws Exception {
        Socket socket = null;
        try {
            // Create a socket
            socket = new Socket("127.0.0.1", 51234);
            // Create print writer to send data over network
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            // Create a buffered reader to recive data over network
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Send a request
            String request = "My first socket request!";
            writer.println(request);
            // Listen for response
            String response = reader.readLine();
            System.out.println("Response: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close the socket
            socket.close();
        }

    }
}
