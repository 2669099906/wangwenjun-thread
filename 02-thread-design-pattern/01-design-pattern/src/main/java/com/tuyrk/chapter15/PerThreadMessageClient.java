package com.tuyrk.chapter15;

import java.util.stream.IntStream;

/**
 * @author zhaoxiangrui
 * @date 2020/10/31 0:23
 */
public class PerThreadMessageClient {
    public static void main(String[] args) {
        MessageHandle handle = new MessageHandle();
        IntStream
                .rangeClosed(1, 10)
                .boxed()
                .forEach(i -> handle.request(new Message(Thread.currentThread().getName()+":"+i)));
        handle.shutdown();
    }
}
