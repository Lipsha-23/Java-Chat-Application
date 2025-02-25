import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    private static final String SERVER_ADDRESS = "127.0.0.1"; 
    private static final int SERVER_PORT = 5000; 

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.print("Enter your username: ");
            String username = scanner.nextLine();
            out.println(username + " has joined the chat!");

            System.out.println("Connected to the chat server. Type your messages:");

            new Thread(() -> {
                try {
                    String serverMessage;
                    while ((serverMessage = in.readLine()) != null) {
                        System.out.println("\n" + serverMessage);
                        System.out.print("You: "); 
                    }
                } catch (IOException e) {
                    System.out.println("Connection closed.");
                }
            }).start();

            // Sending messages to the server
            while (true) {
                System.out.print("You: ");
                String message = scanner.nextLine();
                out.println(username + ": " + message);
                
                if ("exit".equalsIgnoreCase(message)) {
                    System.out.println("Disconnecting...");
                    out.println(username + " has left the chat.");
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error connecting to server: " + e.getMessage());
        }
    }
}
