package com.tuyrk.chapter16;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhaoxiangrui
 * @date 2020/10/31 12:18
 */
public class AppServer extends Thread{

    private int port;

    private static final int DEFAULT_PORT = 12722;

    private final ExecutorService executor = Executors.newFixedThreadPool(10);

    private volatile boolean start = true;

    private ServerSocket server = null;

    private List<ClientHandler> clientHandleList = new ArrayList<>();

    public AppServer(){
        this.port = DEFAULT_PORT;
    }

    public AppServer(int port){
        this.port = port;
    }

    @Override
    public void run() {
        try {
            this.server = new ServerSocket(port);
            System.out.println("服务端已启动");
            while (start){
                if(Thread.currentThread().isInterrupted()){
                    throw new InterruptedException();
                }
                Socket client = server.accept();
                ClientHandler clientHandler = new ClientHandler(client);
                executor.submit(clientHandler);
                clientHandleList.add(clientHandler);
            }
        } catch (IOException e) {
//            throw new RuntimeException(e);
            System.out.println("server stop");
        } catch (InterruptedException e){
            System.out.println("server 已经被打断");
            start = false;
        }
        finally {
            this.dispose();
        }

    }

    private void dispose() {
        clientHandleList.forEach(ClientHandler::stop);
        executor.shutdown();
    }


    public void shutdown() throws IOException {
        this.start = false;
        this.interrupt();
        server.close();
    }

}
