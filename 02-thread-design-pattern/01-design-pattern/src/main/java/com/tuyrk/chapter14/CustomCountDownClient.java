package com.tuyrk.chapter14;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author zhaoxiangrui
 * @date 2020/10/31 0:05
 */
public class CustomCountDownClient {

    public static void main(String[] args) throws InterruptedException {
        final Random random = new Random(System.currentTimeMillis());
        final CountDown latch = new CountDown(5);
        IntStream.rangeClosed(1,5).boxed().forEach(i ->
                new Thread(() ->{
                    System.out.println(Thread.currentThread().getName()+"正在执行");
                    try {
                        Thread.sleep(random.nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    latch.down();
                }, "Thread-"+i).start());

        latch.await();
        System.out.println("第一阶段已经全部完成");
        System.out.println("==================");
        System.out.println("FINISH");
    }
}
