package com.tuyrk.chapter16;

/**
 * @author zhaoxiangrui
 * @date 2020/10/31 12:13
 */
public class CounterTest {

    public static void main(String[] args) throws InterruptedException {
        CounterIncrement counterIncrement = new CounterIncrement();
        counterIncrement.start();

        Thread.sleep(10_000);
        counterIncrement.close();
    }
}
