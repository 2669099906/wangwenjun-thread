package com.tuyrk.chapter17;

import java.util.Random;

/**
 * @author zhaoxiangrui
 * @date 2020/11/1 21:15
 */
public class TransportThread extends Thread {

    private final Channel channel;

    private static final Random random = new Random(System.currentTimeMillis());

    public TransportThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        for (int i=0 ; true; i++){
            Request request = new Request(getName(), i);
            this.channel.put(request);
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
