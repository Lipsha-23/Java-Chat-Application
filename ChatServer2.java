// import java.io.*;
// import java.net.*;
// import java.util.*;

// public class ChatServer {
//     private static final int PORT = 5000;
//     private static Set<PrintWriter> clientWriters = new HashSet<>();

//     public static void main(String[] args) {
//         System.out.println("Chat Server started on port " + PORT);

//         try (ServerSocket serverSocket = new ServerSocket(PORT)) {
//             while (true) {
//                 Socket clientSocket = serverSocket.accept();
//                 System.out.println("New client connected: " + clientSocket);
//                 new ClientHandler(clientSocket).start();
//             }
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }

//     private static class ClientHandler extends Thread {
//         private Socket socket;

//         public ClientHandler(Socket socket) {
//             this.socket = socket;
//         }

//         @Override
//         public void run() {
//             try {
//                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

//                 synchronized (clientWriters) {
//                     clientWriters.add(out);
//                 }

//                 String message;
//                 while ((message = in.readLine()) != null) {
//                     System.out.println("Received: " + message);
//                     synchronized (clientWriters) {
//                         for (PrintWriter writer : clientWriters) {
//                             writer.println(message);
//                         }
//                     }
//                 }
//             } catch (IOException e) {
//                 System.out.println("Client disconnected: " + socket);
//             }
//         }
//     }
// }