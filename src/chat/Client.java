package coursExo8Chat;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        Socket socket = null;
        PrintWriter writer = null;
        BufferedReader consoleReader = null;

        try {
            // Connexion au serveur
            socket = new Socket("localhost", 12345);

            // Thread pour recevoir les messages du serveur
            new Thread(new ReceiveMessage(socket)).start();

            // Envoi des messages au serveur
            writer = new PrintWriter(socket.getOutputStream(), true);
            consoleReader = new BufferedReader(new InputStreamReader(System.in));

            String message;
            while ((message = consoleReader.readLine()) != null) {
                writer.println(message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Fermeture des ressources dans le bloc finally
            try {
                if (writer != null) {
                    writer.close();
                }
                if (consoleReader != null) {
                    consoleReader.close();
                }
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
