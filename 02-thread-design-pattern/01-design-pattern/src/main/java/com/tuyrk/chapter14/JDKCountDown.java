package com.tuyrk.chapter14;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * @author zhaoxiangrui
 * @date 2020/10/30 23:46
 */
public class JDKCountDown {

    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(5);
        IntStream.rangeClosed(1,5).boxed().forEach(i ->
                new Thread(() ->{
                    System.out.println(Thread.currentThread().getName()+"正在执行");
                    try {
                        Thread.sleep(random.nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    latch.countDown();
                }, "Thread-"+i).start());

        latch.await();
        System.out.println("第一阶段已经全部完成");
        System.out.println("==================");
        System.out.println("FINISH");
    }
}
