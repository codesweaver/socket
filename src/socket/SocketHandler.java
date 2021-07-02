package socket;

import java.io.*;
import java.net.Socket;

public class SocketHandler {

    public static void handle(Socket client){
        try (OutputStream outputStream = client.getOutputStream()){

            DataOutputStream dos = new DataOutputStream(outputStream);
            InputStream fileStream = new FileInputStream("D:\\codes\\socket\\webapp\\index.html");
            byte[] responseBody = fileStream.readAllBytes();

//            byte[] responseBody = "Hello World!".getBytes();
            setHttpHeader(dos, responseBody.length);
            setHttpBody(dos, responseBody);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setHttpBody(DataOutputStream dos, byte[] responseBody) {
        try {
            dos.write(responseBody,0, responseBody.length);
            dos.flush();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void setHttpHeader(DataOutputStream dos, int contentsLength) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/html; charset=utf-8 \r\n");
            dos.writeBytes(String.format("Content-Length:%d \r\n", contentsLength));
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
