package com.tuyrk.chapter17;

/**
 * @author zhaoxiangrui
 * @date 2020/11/1 22:08
 */
public class WorkClient {
    public static void main(String[] args) {
        final Channel channel = new Channel(5);
        channel.startWorker();

        new TransportThread("A", channel).start();
        new TransportThread("B", channel).start();
        new TransportThread("C", channel).start();
        new TransportThread("D", channel).start();

    }
}
