package socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Application {

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(8080)) {
            while (true) {
                Socket client = server.accept();
                handleSocket(client);
                client.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleSocket(Socket client) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        String buffer;
        while ((buffer = reader.readLine()) != null) {
            System.out.println(buffer);
        }
        reader.close();
    }
}
