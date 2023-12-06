package coursExo8Chat;

import java.io.PrintWriter;
import java.util.List;

public class BroadcastMessage {
    private List<PrintWriter> clientWriters;

    public BroadcastMessage(List<PrintWriter> clientWriters) {
        this.clientWriters = clientWriters;
    }

    public void broadcast(String message) {
        synchronized (clientWriters) {
            for (PrintWriter writer : clientWriters) {
                writer.println(message);
            }
        }
    }
}
