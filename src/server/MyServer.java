package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
    public static void main(String[] args) throws Exception {
        MyServer server = new MyServer();
        server.start();
//        server.readHtml();

    }

    private void start() throws Exception {
        ServerSocket ss = new ServerSocket(8080);
        Socket s = ss.accept();
        OutputStream os = s.getOutputStream();
        FileReader fr = new FileReader(new File("index.html"));
        char[] data = new char[1024];
        fr.read(data);

        for (int i = 0; i < 1024; i++) {
        }
        os.write("HTTP/1.1 200 OK\r\n".getBytes());
        os.write("Content-Type: text/html; charset=utf-8\r\n".getBytes());
        os.write("Connection: close\r\n".getBytes());
        os.write(readHtml().getBytes());
        os.write("\r\n\r\n".getBytes());
        os.flush();
        os.close();
    }

    private String readHtml() throws Exception {
        FileReader fr = new FileReader(new File("index.html"));
        StringBuilder s = new StringBuilder();
        int sybm = 0;

        while ((sybm = fr.read()) != -1) {
//            System.out.print((char) sybm);
            s.append((char) sybm);
        }
        return s.toString();
    }

}
