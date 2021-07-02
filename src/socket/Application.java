package socket;

import java.net.ServerSocket;
import java.net.Socket;

public class Application {

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(8080)) {
            Socket client;
            while ((client = server.accept()) != null) {
                SocketHandler.handle(client);
                client.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
