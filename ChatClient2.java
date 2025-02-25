// import java.io.*;
// import java.net.*;
// import java.util.Scanner;

// public class ChatClient {
//     private static final String SERVER_ADDRESS = "127.0.0.1"; // Change this if server is on another machine
//     private static final int SERVER_PORT = 5000; // Must match the port used by ChatServer

//     public static void main(String[] args) {
//         try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
//              BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//              PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
//              Scanner scanner = new Scanner(System.in)) {

//             System.out.println("Connected to the chat server. Type your messages:");

//             // Thread to listen for incoming messages from the server
//             new Thread(() -> {
//                 try {
//                     String serverMessage;
//                     while ((serverMessage = in.readLine()) != null) {
//                         System.out.println("\nServer: " + serverMessage);
//                         System.out.print("You: "); // Keeps input prompt aligned
//                     }
//                 } catch (IOException e) {
//                     System.out.println("Connection closed.");
//                 }
//             }).start();

//             // Sending messages to the server
//             while (true) {
//                 System.out.print("You: ");
//                 String message = scanner.nextLine();
//                 out.println(message);
                
//                 if ("exit".equalsIgnoreCase(message)) {
//                     System.out.println("Disconnecting...");
//                     break;
//                 }
//             }
//         } catch (IOException e) {
//             System.out.println("Error connecting to server: " + e.getMessage());
//         }
//     }
// }
