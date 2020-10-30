package com.tuyrk.chapter15;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhaoxiangrui
 * @date 2020/10/31 0:19
 */
public class MessageHandle {

    private static final Random random = new Random(System.currentTimeMillis());

    private static final Executor executor = Executors.newFixedThreadPool(5);

    public void request(Message message){
        executor.execute(()->{
            String value = message.getValue();
            System.out.println(Thread.currentThread().getName() + "正在处理：" + value);
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public void shutdown(){
        ((ExecutorService)executor).shutdown();
    }

}
