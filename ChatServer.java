import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static final int PORT = 5000;
    private static Set<PrintWriter> clientWriters = new HashSet<>();

    public static void main(String[] args) {
        System.out.println("Chat Server started on port " + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private String userName;
    
        public ClientHandler(Socket socket) {
            this.socket = socket;
        }
    
        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
    
                userName = in.readLine();
    
                synchronized (clientWriters) {
                    clientWriters.add(out);
                    for (PrintWriter writer : clientWriters) {
                        writer.println(userName + " has joined the chat!"); 
                    }
                }
    
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println(userName + ": " + message);
                    synchronized (clientWriters) {
                        for (PrintWriter writer : clientWriters) {
                            writer.println(userName + ": " + message);
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Client disconnected: " + socket);
            } finally {
                synchronized (clientWriters) {
                    clientWriters.remove(out);
                    for (PrintWriter writer : clientWriters) {
                        writer.println(userName + " has left the chat.");
                    }
                }
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
}
