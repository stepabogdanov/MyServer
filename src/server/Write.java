package server;

import java.io.*;
import java.net.Socket;

/**
 * Created by дом on 15.03.2018.
 */
public class Write implements Runnable {

    @Override
    public void run() {
        Socket s = null;
        OutputStream os = null;
        try {
            os = s.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileReader fr = null;
        try {
            fr = new FileReader(new File("index.html"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        char[] data = new char[1024];
        try {
            fr.read(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 1024; i++) {
        }
        try {
            os.write("HTTP/1.1 200 OK\r\n".getBytes());
            os.write("Content-Type: text/html; charset=utf-8\r\n".getBytes());
            os.write("Connection: keep-alive\r\n".getBytes());
            os.write(readHtml().getBytes());
            os.write("\r\n\r\n".getBytes());
            os.flush();
            os.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }

    private String readHtml() {
        FileReader fr = null;
        try {
            fr = new FileReader(new File("index.html"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuilder s = new StringBuilder();
        int sybm = 0;

        try {
            while ((sybm = fr.read()) != -1) {
    //            System.out.print((char) sybm);
                s.append((char) sybm);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s.toString();
    }
}
