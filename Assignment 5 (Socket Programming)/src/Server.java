import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        try {
            // Open sockets
            serverSocket = new ServerSocket(51234);
            clientSocket = serverSocket.accept();

            // Create buffered reader for input
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // Create Print writer for output
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            String request = "";
            while (!request.equalsIgnoreCase("q") && !request.equalsIgnoreCase("quit")) {
                // Listen for request
                System.out.println("Waiting for request...");
                request = reader.readLine();
                System.out.println("Request: " + request);

                // Send response
                String response = request.toUpperCase();
                System.out.println("Sending response...");
                writer.println(response);
                System.out.println("Response: " + response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Closing sockets and ending program.");
            // Close sockets
            serverSocket.close();
            clientSocket.close();

        }
    }
}
