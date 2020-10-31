package com.tuyrk.chapter16;

import java.util.Random;

/**
 * @author zhaoxiangrui
 * @date 2020/10/31 12:07
 */
public class CounterIncrement extends Thread{

    private volatile boolean terminated = false;

    private int counter = 0;

    private Random random = new Random(System.currentTimeMillis());

    @Override
    public void run() {
        try {
            while (!terminated){
                System.out.println(Thread.currentThread().getName()+ ":" + counter++);
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.clean();
        }

    }

    private void clean(){
        System.out.println("do some cleaning work for "+Thread.currentThread().getName());
    }

    public void close(){
        this.terminated = true;
        this.interrupt();
        System.out.println(Thread.currentThread().getName()+"already stop ！");
    }
}
