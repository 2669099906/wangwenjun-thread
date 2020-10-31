package com.tuyrk.chapter16;

import java.io.IOException;

/**
 * @author zhaoxiangrui
 * @date 2020/10/31 19:09
 */
public class AppServerClient {
    public static void main(String[] args) throws  InterruptedException {
        AppServer appServer = new AppServer(13345);
        appServer.start();

        Thread.sleep(24_000);
        System.out.println("=====shutdown====");
        try {
            appServer.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
