package server;


import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by дом on 15.03.2018.
 */
public class Listen extends Thread implements Runnable {
    @Override
    public void run() {
        Socket s = null;
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(8080);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            s = ss.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream is = null;
        try {
            is = s.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        String end;
        int symb = 0;
        try {
            while ((symb = is.read()) != -1) {
                System.out.print((char) symb);
                sb.append((char) symb);
                if (sb.length() > 4) {
                    end = sb.substring(sb.length()-4, sb.length());
                    if (end.equals("\r\n\r\n")) {
                        Thread thread = new Thread(new Write());
                        thread.start();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(end = sb.substring(sb.length()-4, sb.length()));

    }


}
