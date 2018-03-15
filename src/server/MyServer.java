package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer  {
    Socket s;
    ServerSocket ss = new ServerSocket(8080);

    public MyServer() throws IOException {
    }

    public static void main(String[] args) throws Exception {
        MyServer server = new MyServer();
//        Thread thread = new Thread(new Listen());
//        thread.start();
        server.startListen();



    }

    private void startListen() throws Exception {
        s = ss.accept();
        InputStream is = s.getInputStream();
        StringBuilder sb = new StringBuilder();
        String end;
        int symb = 0;
        while (symb !=  -1) {

            if ( !is.equals(null)) {
                symb = is.read();
            }
            System.out.print((char) symb);
            sb.append((char) symb);
            if (sb.length() > 4) {
                end = sb.substring(sb.length() - 4, sb.length());
                if (end.equals("\r\n\r\n")) {
                    startWrite(s);
                    if (s.isClosed()) {
                        System.out.println("closed");
                        startListen();
                        //System.exit(1);
                    }
                }

            }
        }
    }

    private void startWrite(Socket socket) throws Exception {

        OutputStream os = socket.getOutputStream();

        FileReader fr = new FileReader(new File("index.html"));
        char[] data = new char[1024];
        fr.read(data);

        for (int i = 0; i < 1024; i++) {
        }
        os.write("HTTP/1.1 200 OK\r\n".getBytes());
        os.write("Content-Type: text/html; charset=utf-8\r\n".getBytes());
        os.write("Connection: keep-alive\r\n\n".getBytes());
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
