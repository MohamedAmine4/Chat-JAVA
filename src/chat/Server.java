package coursExo8Chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private List<PrintWriter> clientWriters = new ArrayList<>();

    public void startServer(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Serveur en attente de connexions...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                AcceptClient acceptClient = new AcceptClient(clientSocket, clientWriters);
                acceptClient.start();
            }

        } catch (IOException e) {
        	 System.out.println("server");
            //e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server serverMain = new Server();
        serverMain.startServer(12345);
    }
}




