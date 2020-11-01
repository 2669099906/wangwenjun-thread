package com.tuyrk.chapter17;

import java.util.Random;

/**
 * @author zhaoxiangrui
 * @date 2020/11/1 19:12
 */
public class WorkerThread extends Thread {

    private final Channel channel;

    public static final Random random = new Random(System.currentTimeMillis());

    public WorkerThread(Channel channel, String name) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        while (true){
            Request request = channel.take();
            request.execute();
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
