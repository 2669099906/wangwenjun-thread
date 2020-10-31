package com.tuyrk.chapter16;

import java.io.*;
import java.net.Socket;

/**
 * @author zhaoxiangrui
 * @date 2020/10/31 17:41
 */
public class ClientHandler implements Runnable {

    private final Socket socket;

    private volatile boolean running = true;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try (InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
             PrintWriter printWriter = new PrintWriter(outputStream)
        ){
            while (running){
                String message = br.readLine();
                if(message == null){
                    break;
                }
                System.out.println("come from client >"+message);
                printWriter.write("echo "+ message +"\n");
                printWriter.flush();
            }
        } catch (IOException e) {
//            System.out.print("client:" + e.getMessage());
//            running = false;
        } finally {
            this.stop();
        }

    }

    public void stop() {
        if(!running){
            return;
        }
        running = false;
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
