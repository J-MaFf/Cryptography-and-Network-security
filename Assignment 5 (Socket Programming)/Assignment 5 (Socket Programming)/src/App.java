import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.xml.bind.PrintConversionEvent;

public class App {
    public static void main(String[] args) throws Exception {
        // Create a socket
        Socket socket = new Socket("127.0.0.1", 51234);
        // Create print writer to send data over network
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        // Create a buffered reader to recive data over network
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // Send a request
        writer.println("My first socket request!");
    }
}
